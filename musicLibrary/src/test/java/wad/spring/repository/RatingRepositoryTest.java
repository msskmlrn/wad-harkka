package wad.spring.repository;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.Album;
import wad.spring.domain.Rating;
import wad.spring.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-context-test.xml", "file:src/main/webapp/WEB-INF/spring-database.xml"})
@Transactional
public class RatingRepositoryTest {
    
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    UserRepository userRepository;
    
    @Before
    public void setUp() {
        Album album = new Album();
        album.setAlbumName("And the Glass Handed Kites");
        album.setAlbumArtist("Mew");
        album.setGenre("Indie");
        album.setDescription("Jee");
        album.setReleaseYear("2005");
        album = albumRepository.save(album);
        
        User user = new User();
        user.setName("Matti Mattila");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);
    }
    
    @Test
    @Transactional
    public void saveRatingToDatabase() {
        long countAtStart = ratingRepository.count();
        
        Album album = albumRepository.findByAlbumName("And the Glass Handed Kites");
        
        Rating r = new Rating();
        r.setAlbum(album);
        r.setStars(2);
        
        ratingRepository.save(r);
        albumRepository.save(album);
        
        long countAtEnd = ratingRepository.count();

        Assert.assertTrue(countAtStart + 1 == countAtEnd);
    }
    
    @Test
    @Transactional
    public void findByAlbum() {
        Album album = albumRepository.findByAlbumName("And the Glass Handed Kites");
        User user = userRepository.findByOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        
        Rating rating = user.addRating(album, 3);
        album.addAlbumRating(rating);
        
        ratingRepository.save(rating);
        album = albumRepository.save(album);
        
        ratingRepository.findByAlbum(album);
        
        Assert.assertTrue(album.getAlbumName().compareTo("And the Glass Handed Kites")==0);
    }
    
    @Test
    @Transactional
    public void findByAlbumDoesNotReturnMissingAlbum() {
        Album album = new Album();
        
        List<Rating> ratings = ratingRepository.findByAlbum(album);
                
        Assert.assertTrue(ratings.isEmpty());
    }
}
