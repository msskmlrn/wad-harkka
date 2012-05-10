package wad.spring.security;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.User;
import wad.spring.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-context-test.xml", "file:src/main/webapp/WEB-INF/spring-database.xml"})
@Transactional
public class OpenIdUserDetailsServiceTest {
    
    private OpenIdUserDetailsService openIdUserDetailsService;
    
    @Autowired
    UserRepository userRepository;
    
    @Before
    public void setUp() {
        User user = new User();
        user.setName("Sami Sami");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLasfafasfasf");
        userRepository.save(user);
    }
    
    @Test
    public void test() {
        
    }
    
}
