package wad.spring.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-context-test.xml", "file:src/main/webapp/WEB-INF/spring-database.xml"})
@Transactional
public class UserRepositoryTest {
    
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    UserRepository userRepository;
    
    @Test
    @Transactional
    public void saveUserToDatabase() {
        long countAtStart = userRepository.count();       
        
        User user = new User();
        user.setName("Matti Mattila");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);
        
        long countAtEnd = userRepository.count();

        Assert.assertTrue(countAtStart + 1 == countAtEnd);
    }
    
    @Test
    @Transactional
    public void findUserByOpenIdIdentifier() {
        User user = new User();
        user.setName("Matti Matti");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLasfafasfasf");
        user = userRepository.save(user);
        
        User u = userRepository.findByOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLasfafasfasf");
        
        Assert.assertTrue(u.getName().equals("Matti Matti")); 
    }
}
