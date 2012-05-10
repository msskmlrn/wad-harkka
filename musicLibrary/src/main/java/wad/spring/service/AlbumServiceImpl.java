package wad.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.Album;
import wad.spring.domain.Rating;
import wad.spring.domain.User;
import wad.spring.repository.AlbumRepository;
import wad.spring.repository.RatingRepository;
import wad.spring.repository.UserRepository;

@Service
public class AlbumServiceImpl implements AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    
    @Override
    @Transactional
    public Album getAlbum(Long albumId) {
        return albumRepository.findOne(albumId);
    }
    
    @Override
    @Transactional
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }
    
    @Override
    @Transactional
    public List<Album> getAlbumsFromArtist(String artistName) {
        return albumRepository.findByAlbumArtist(artistName);
    }
    
    
    @Override
    @Transactional
    public void addRating(long albumId, int rating, String openid) {
        User user = userRepository.findByOpenIdIdentifier(openid);
        Album album = albumRepository.findOne(albumId);
        Rating r = user.addRating(album, rating);
        album.addAlbumRating(r);
        
        ratingRepository.save(r);
        albumRepository.save(album);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void createNewAlbum(Album album) {
        Album a = albumRepository.findByAlbumName(album.getAlbumName());
        
        if (a == null) {
            albumRepository.save(album);
        }
    }

    @Override
    @Transactional
    public void addAlbumToCollection(long albumId, String name) {
        User user = userRepository.findByOpenIdIdentifier(name);
        Album a = albumRepository.findOne(albumId);
        a.addOwner(user);
        albumRepository.save(a);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<Rating> getRatingsForAlbum(String albumName) {
        Album album = albumRepository.findByAlbumName(albumName);
        return ratingRepository.findByAlbum(album);
    }
    
    @Override
    @Transactional
    public void updateAverageRating(String albumName) {
        double stars = 0;
        Album album = albumRepository.findByAlbumName(albumName);
        List <Rating> ratings = album.getAlbumRatings();
        
        for (Rating r : ratings) {
            if (r.getStars() >= 0) {
                stars = stars + r.getStars();
            }
        }
        
        album.setAverageRating(stars/ratings.size());
        albumRepository.save(album);
    }
}
