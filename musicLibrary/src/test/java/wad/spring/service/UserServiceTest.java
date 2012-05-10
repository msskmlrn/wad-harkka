package wad.spring.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.Album;
import wad.spring.domain.Rating;
import wad.spring.domain.User;
import wad.spring.repository.AlbumRepository;
import wad.spring.repository.RatingRepository;
import wad.spring.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-context-test.xml", "file:src/main/webapp/WEB-INF/spring-database.xml"})
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    RatingRepository ratingRepository;
    
    @Test
    @Transactional
    public void getUsers() {
        User user = new User();
        user.setName("Matti Mattila");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);

        user = new User();
        user.setName("Matti Matti");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLasfafasfasf");
        user = userRepository.save(user);

        long userRepositoryCount = userRepository.count();

        List<User> users = userService.getUsers();

        Assert.assertTrue(userRepositoryCount == users.size());
    }

    @Test
    @Transactional
    public void getUserById() {
        long i = 10;
        User user = new User();
        user.setName("Pekka Mattila");
        user.setId(i);
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);

        User u = userService.getUser(i);

        Assert.assertTrue(u.getName().compareTo("Pekka Mattila") == 0);
    }

    @Test
    @Transactional
    public void getAlbumsToBeRatedForUser() {
        long i = 25;
        
        User user = new User();
        user.setName("Sami Mattila");
        user.setId(i);
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdsioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);
        
        Album album = new Album();
        album.setAlbumName("The Resistance");
        album.setAlbumArtist("Muse");
        album.setGenre("Rock");
        album.setDescription("Jahuu");
        album.setReleaseYear("2009");
        album = albumRepository.save(album);
        
        user.addAlbum(album);
        user = userRepository.save(user);
        
        Album album2 = new Album();
        album2.setAlbumName("And the Glass Handed Kites");
        album2.setAlbumArtist("Mew");
        album2.setGenre("Indie");
        album2.setDescription("Jee");
        album2.setReleaseYear("2005");
        album2 = albumRepository.save(album2);
        
        user.addAlbum(album2);
        user = userRepository.save(user);
        
        Album album3 = new Album();
        album3.setAlbumName("Frengers");
        album3.setAlbumArtist("Mew");
        album3.setGenre("Indie");
        album3.setDescription("Jippii");
        album3.setReleaseYear("2003");
        album3 = albumRepository.save(album3);
        
        user.addAlbum(album3);
        user = userRepository.save(user);
        
        Rating rating = user.addRating(album, 3);
        album.addAlbumRating(rating);

        ratingRepository.save(rating);
        album = albumRepository.save(album);  
        
        List<Album> albums = userService.getAlbumsToBeRatedForUser(i);
        
        Assert.assertTrue(albums.size() == 2);
    }
}
