package wad.spring.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import wad.spring.domain.User;
import wad.spring.repository.UserRepository;

public class OpenIdUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Autowired
    private UserRepository userRepository;
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_USER");

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findByOpenIdIdentifier(id);

        if (user == null) {
            throw new UsernameNotFoundException(id);
        }
        OpenIdUser openIdUser = new OpenIdUser(user.getOpenIdIdentifier(), DEFAULT_AUTHORITIES);
        openIdUser.setName(user.getName());

        return openIdUser;
    }

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) {
        String id = token.getIdentityUrl();

        User user = userRepository.findByOpenIdIdentifier(id);

        if (user != null) {
            OpenIdUser openIdUser = new OpenIdUser(user.getOpenIdIdentifier(), DEFAULT_AUTHORITIES);
            openIdUser.setName(user.getName());

            return openIdUser;
        }

        String firstName = null;
        String lastName = null;
        String fullName = null;

        List<OpenIDAttribute> attributes = token.getAttributes();

        for (OpenIDAttribute attribute : attributes) {
            String name = attribute.getName();

            if (name.equals("firstname")) {
                firstName = attribute.getValues().get(0);
            } else if (name.equals("lastname")) {
                lastName = attribute.getValues().get(0);
            } else if (name.equals("fullname")) {
                fullName = attribute.getValues().get(0);
            }
        }

        if (fullName == null) {
            StringBuilder fullNameBldr = new StringBuilder();

            if (firstName != null) {
                fullNameBldr.append(firstName);
            }
            if (lastName != null) {
                fullNameBldr.append(" ").append(lastName);
            }

            fullName = fullNameBldr.toString();
        }

        OpenIdUser openIdUser = new OpenIdUser(id, DEFAULT_AUTHORITIES);
        openIdUser.setName(fullName);
        openIdUser.setNewUser(true);
        
        User u = new User();
        u.setOpenIdIdentifier(openIdUser.getUsername());
        u.setName(openIdUser.getName());
        
        userRepository.save(u);

        return openIdUser;
    }
}