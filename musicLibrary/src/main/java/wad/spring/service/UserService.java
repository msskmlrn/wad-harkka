package wad.spring.service;

import java.util.List;
import wad.spring.domain.Album;
import wad.spring.domain.User;

public interface UserService {
    
    public List<User> getUsers();
    
    public User getUser(Long userId);
    
    public List<Album> getAlbumsToBeRatedForUser(Long userId);
    
    
}
