package wad.spring.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/*
 * @author Luke Taylor (https://fisheye.springsource.org/browse/spring-security/samples/openid/src/main/java/org/springframework/security/samples/openid/CustomUserDetails.java?hb=true)
 */ 
public class OpenIdUser extends User {
   private String name;
   private boolean newUser;
 
   public OpenIdUser(String username, Collection<GrantedAuthority> authorities) {
       super(username, "unused", authorities);
   }
   
   public boolean isNewUser() {
       return newUser;
   }
 
   public void setNewUser(boolean newUser) {
       this.newUser = newUser;
   }
 
   public String getName() {
       return name;
   }
 
   public void setName(String name) {
       this.name = name;
   }
}