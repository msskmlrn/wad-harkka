package wad.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.spring.domain.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    public Album findByAlbumName(String albumName);
    
    public List<Album> findByAlbumArtist(String albumArtist);
}