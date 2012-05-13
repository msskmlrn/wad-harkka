package wad.spring.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.spring.domain.Album;
import wad.spring.domain.User;
import wad.spring.service.AlbumService;
import wad.spring.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;
    @Autowired
    AlbumService albumService;
    
    @RequestMapping(value = "/app/user/{userId}", method = RequestMethod.GET)
    public String showUserPage(Model model, @PathVariable Long userId) {
        User user = userService.getUser(userId);
        
        if (user == null) {
            return "redirect:/app/album";
        }
        
        model.addAttribute("user", user);
        model.addAttribute("ratings", user.getRatings());
        model.addAttribute("toBeRated", userService.getAlbumsToBeRatedForUser(userId));
        return "/user/userpage";
    }
    
    @RequestMapping(value = "/app/user/{userId}/rate", method = RequestMethod.GET)
    public String showPageForRatingAlbum(Model model, @PathVariable Long userId, Principal principal) {
        User user = userService.getUser(userId);
        
        if (user == null) {
            return "redirect:/app/album";
        }
        
        model.addAttribute("user", user);
        model.addAttribute("toBeRated", userService.getAlbumsToBeRatedForUser(userId));
        model.addAttribute("openId", principal.getName());
        return "/user/userratingpage";
    }
    
    @RequestMapping(value = "/app/user/{userId}/album/{albumId}/rate/{rating}", method = RequestMethod.GET)
    public String rateAlbum(@PathVariable Long albumId, @PathVariable Integer rating, @PathVariable Long userId, Principal principal) {
        Album album = albumService.getAlbum(albumId);
        
        if (album == null) {
            return "redirect:/app/album";
        }
        
        albumService.addRating(album.getId(), rating, principal.getName());
        albumService.updateAverageRating(album.getAlbumName());
        return "redirect:/app/user/{userId}";
    }
    
    @RequestMapping(value = "/app/user/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "/user/login";
    }
    
}
