package wad.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.Album;
import wad.spring.repository.AlbumRepository;

@Service
public class SecureServiceImpl implements SecureService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    @Transactional
    public void executeFreely() {

        // populate db if needed
        if (albumRepository.count() == 0) {
            Album album = new Album();
            album.setAlbumName("And the Glass Handed Kites");
            album.setAlbumArtist("Mew");
            album.setGenre("Indie");
            album.setDescription("Jee");
            album.setReleaseYear("2005");
            album = albumRepository.save(album);
            
            album = new Album();
            album.setAlbumName("The Resistance");
            album.setAlbumArtist("Muse");
            album.setGenre("Rock");
            album.setDescription("Jahuu");
            album.setReleaseYear("2009");
            album = albumRepository.save(album);

            album = new Album();
            album.setAlbumName("Frengers");
            album.setAlbumArtist("Mew");
            album.setGenre("Indie");
            album.setDescription("Jippii");
            album.setReleaseYear("2003");
            album = albumRepository.save(album);
        }
    }
}
