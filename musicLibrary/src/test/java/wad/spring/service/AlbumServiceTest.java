package wad.spring.service;

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
import wad.spring.repository.AlbumRepository;
import wad.spring.repository.RatingRepository;
import wad.spring.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-context-test.xml", "file:src/main/webapp/WEB-INF/spring-database.xml"})
@Transactional
public class AlbumServiceTest {

    @Autowired
    AlbumService albumService;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RatingRepository ratingRepository;

    @Before
    public void setUp() {
        long i = 10;
        long j = 5;
        
        User user = new User();
        user.setName("Sami Mattila");
        user.setId(i);
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdsioJSDLFJLjxcksdfjOpAASDFosSSoJ0E");
        user = userRepository.save(user);
        
        User user2 = new User();
        user2.setName("Pasi Pasi");
        user2.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdsisfAsWRarpAASDFosSSoJ0E");
        user2 = userRepository.save(user2);
        
        Album album = new Album();
        album.setAlbumName("And the Glass Handed Kites");
        album.setAlbumArtist("Mew");
        album.setGenre("Indie");
        album.setDescription("Jee");
        album.setReleaseYear("2005");
        album.setId(i);
        album = albumRepository.save(album);
        
        user.addAlbum(album);
        user = userRepository.save(user);
        Rating rating = user.addRating(album, 3);
        album.addAlbumRating(rating);
        ratingRepository.save(rating);
        album = albumRepository.save(album);
        
        rating = user2.addRating(album, 1);
        album.addAlbumRating(rating);
        ratingRepository.save(rating);
        album = albumRepository.save(album);
        
        Album album2 = new Album();
        album2.setAlbumName("The Resistance");
        album2.setAlbumArtist("Muse");
        album2.setGenre("Rock");
        album2.setDescription("Jahuu");
        album2.setReleaseYear("2009");
        album2.setId(j);
        album2 = albumRepository.save(album2);
        
        Album album3 = new Album();
        album3.setAlbumName("Frengers");
        album3.setAlbumArtist("Mew");
        album3.setGenre("Indie");
        album3.setDescription("Jippii");
        album3.setReleaseYear("2003");
        album3 = albumRepository.save(album3);

        user.addAlbum(album3);
        user = userRepository.save(user);
    }

    @Test
    @Transactional
    public void getAlbum() {
        long j = 10;
        Album a = albumService.getAlbum(j);

        Assert.assertTrue(a.getAlbumName().compareTo("And the Glass Handed Kites") == 0);
    }
    
    @Test
    @Transactional
    public void getAlbumDoesNotReturnMissingAlbum() {
        long j = 100;
        Album a = albumService.getAlbum(j);

        Assert.assertTrue(a == null);
    }

    @Test
    @Transactional
    public void getAlbums() {
        List<Album> albums = albumService.getAlbums();
        Assert.assertTrue(albums.size() == 3);
    }
    
    @Test
    @Transactional
    public void getAlbumsForArtist() {
        List<Album> albums = albumService.getAlbumsFromArtist("Mew");

        Assert.assertTrue(albums.size() == 2);
    }
    
    @Test
    @Transactional
    public void getAlbumsForArtistDoesNotReturnMissingAlbums() {
        List<Album> albums = albumService.getAlbumsFromArtist("The");

        Assert.assertTrue(albums.isEmpty());
    }

    @Test
    @Transactional
    public void getRatingsForAlbum() {
        List<Rating> ratings = albumService.getRatingsForAlbum("And the Glass Handed Kites");

        Assert.assertTrue(ratings.size() == 2);
    }
    
    @Test
    @Transactional
    public void getRatingsForAlbumDoesNotReturnRatingsForMissingAlbum() {
        List<Rating> ratings = albumService.getRatingsForAlbum("The");

        Assert.assertTrue(ratings.isEmpty());
    }
    
    @Test
    @Transactional
    public void updateAverage() {
        long i = 10;
        albumService.updateAverageRating("And the Glass Handed Kites");
        Album album = albumService.getAlbum(i);
        Assert.assertTrue(album.getAverageRating() == 2);
    }
    
    @Test
    @Transactional
    public void addRating() {
        long j = 5;
        User user = new User();
        user.setName("Sami Sami");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdassffsdefasfasfSoJ0E");
        user = userRepository.save(user);
        
        albumService.addRating(j, 5, user.getOpenIdIdentifier());
        
        Album album = albumService.getAlbum(j);
        
        Assert.assertTrue(album.getAlbumRatings().size() == 1);
    }
    
    @Test
    @Transactional
    public void doNotAddRatingSeveralRatingsForOneUser() {
        long j = 5;
        User user = new User();
        user.setName("Sami Sami");
        user.setOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdassffsdefasfasfSoJ0E");
        user = userRepository.save(user);
        
        albumService.addRating(j, 5, user.getOpenIdIdentifier());
               
        albumService.addRating(j, 1, user.getOpenIdIdentifier());
        
        Album album = albumService.getAlbum(j);
        
        Assert.assertTrue(album.getAlbumRatings().size() == 1);
        
        user = userRepository.findOne(j);
        Assert.assertTrue(user.getRatings().size() == 1);
        
        List<Rating> ratings = user.getRatings();
        
        Assert.assertTrue(ratings.get(0).getStars() == 5);
    }
    
    @Test
    @Transactional
    public void createNewAlbum() {
        long i = 15;
        
        Album album = new Album();
        album.setAlbumName("Jotakin");
        album.setAlbumArtist("Joku");
        album.setGenre("Indie");
        album.setDescription("Jeep");
        album.setReleaseYear("2005");
        album.setId(i);
        
        albumService.createNewAlbum(album);
        
        Album a = albumService.getAlbum(i);
        
        Assert.assertTrue(a.getAlbumName().compareTo("Jotakin") == 0);
    }
    
    @Test
    @Transactional
    public void doNotCreateDublicateAlbum() { 
        long i = 15;
        Album album = new Album();
        album.setAlbumName("And the Glass Handed Kites");
        album.setAlbumArtist("Mew");
        album.setGenre("Indie");
        album.setDescription("Jee");
        album.setReleaseYear("2005");
        album.setId(i);
        
        albumService.createNewAlbum(album);
        
        Album a = albumService.getAlbum(i);
        
        Assert.assertTrue(a == null);
    }
    
    @Test
    @Transactional
    public void addAlbumToCollection() {
        long i = 10;
        Album a = albumService.getAlbum(i);
        User user = userRepository.findByOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdsisfAsWRarpAASDFosSSoJ0E");
        albumService.addAlbumToCollection(i, user.getOpenIdIdentifier());
        user = userRepository.findByOpenIdIdentifier("https://www.google.com/accounts/o8/id?id=AIdsisfAsWRarpAASDFosSSoJ0E");
        
        Assert.assertTrue(user.getAlbums().size() == 1);
    }
}
