package wad.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.spring.domain.Album;
import wad.spring.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    public List<Rating> findByAlbum(Album album);
}
