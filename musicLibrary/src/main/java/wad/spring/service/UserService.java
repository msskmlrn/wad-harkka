package wad.spring.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import wad.spring.domain.Album;
import wad.spring.domain.User;

public interface UserService {
    
    public List<User> getUsers();
    
    public User getUser(Long userId);
    
    @PreAuthorize("isAuthenticated()")
    public List<Album> getAlbumsToBeRatedForUser(Long userId);

}
