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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-context-test.xml", "file:src/main/webapp/WEB-INF/spring-database.xml"})
@Transactional
public class AlbumRepositoryTest {

    @Autowired
    AlbumRepository albumRepository;
    
    @Before
    public void setUp() {
        Album album = new Album();
        album.setAlbumName("The Resistance");
        album.setAlbumArtist("Muse");
        album.setGenre("Rock");
        album.setDescription("Jahuu");
        album.setReleaseYear("2009");
        album = albumRepository.save(album);
    }
    
    @Test
    @Transactional
    public void saveAlbumToDatabase() {
        long countAtStart = albumRepository.count();

        Album album = new Album();
        album.setAlbumName("And the Glass Handed Kites");
        album.setAlbumArtist("Mew");
        album.setGenre("Indie");
        album.setDescription("Jee");
        album.setReleaseYear("2005");
        album = albumRepository.save(album);

        long countAtEnd = albumRepository.count();

        Assert.assertTrue(countAtStart + 1 == countAtEnd);
    }

    @Test
    @Transactional
    public void findAlbumByName() {
        Album a = albumRepository.findByAlbumName("The Resistance");
        String name = a.getAlbumName();

        Assert.assertTrue(name.compareTo("The Resistance") == 0);
    }
    
    @Test
    @Transactional
    public void findAlbumByNameDoesNotReturnMissingAlbum() {
        Album a = albumRepository.findByAlbumName("The The");

        Assert.assertTrue(a == null);
    }
    
    @Test
    @Transactional
    public void findByAlbumArtist() {
        List<Album> albums = albumRepository.findByAlbumArtist("Muse");
        String name = albums.get(0).getAlbumArtist();

        Assert.assertTrue(name.compareTo("Muse") == 0);
    }
    
    @Test
    @Transactional
    public void findByAlbumArtistDoesNotReturnMissingArtists() {
        List<Album> albums = albumRepository.findByAlbumArtist("The");

        Assert.assertTrue(albums.isEmpty());
    }
}
