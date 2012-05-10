package wad.spring.repository;

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
    
    @Test
    @Transactional
    public void saveRatingToDatabase() {
        long countAtStart = ratingRepository.count();
        
        Album album = new Album();
        album.setAlbumName("And the Glass Handed Kites");
        album.setAlbumArtist("Mew");
        album.setGenre("Indie");
        album.setDescription("Jee");
        album.setReleaseYear("2005");
        album = albumRepository.save(album);
        
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
        Album album = new Album();
        album.setAlbumName("The Resistance");
        album.setAlbumArtist("Muse");
        album.setGenre("Rock");
        album.setDescription("Jahuu");
        album.setReleaseYear("2009");
        album = albumRepository.save(album);
        
        User user = new User();
        user.setName("Matti Mattila");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AItxxioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);
        
        Rating rating = user.addRating(album, 3);
        album.addAlbumRating(rating);
        
        ratingRepository.save(rating);
        album = albumRepository.save(album);
        
        
        ratingRepository.findByAlbum(album);
        
        Assert.assertTrue(album.getAlbumName().compareTo("The Resistance")==0);
    }
}
