package wad.spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.Album;
import wad.spring.domain.Rating;
import wad.spring.domain.User;
import wad.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    @Transactional
    public List<Album> getAlbumsToBeRatedForUser(Long userId) {
        User user = userRepository.findOne(userId);
        List<Album> albums = user.getAlbums();
        List<Album> results = new ArrayList<Album>();

        boolean rated = false;

        for (Album a : albums) {
            for (Rating r : a.getAlbumRatings()) {
                if (r.getRater().getId().equals(userId)) {
                    rated = true;
                }
            }
            if (rated == false) {
                results.add(a);
            }
            rated = false;
        }
        return results;
    }
}