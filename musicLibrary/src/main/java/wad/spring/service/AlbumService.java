package wad.spring.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import wad.spring.domain.Album;
import wad.spring.domain.Rating;

public interface AlbumService {
    public Album getAlbum(Long albumId);
       
    public List<Album> getAlbums();
    
    public List<Album> getAlbumsFromArtist(String artistName);
           
    public List<Rating> getRatingsForAlbum(String albumName);
    
    @PreAuthorize("isAuthenticated()")
    public void updateAverageRating(String albumName);
    
    @PreAuthorize("isAuthenticated()")
    public void addRating(long albumId, int rating, String username);
    
    @PreAuthorize("isAuthenticated()")
    public void createNewAlbum(Album album);
    
    @PreAuthorize("isAuthenticated()")
    public void addAlbumToCollection(long albumId, String username);
}
