package wad.spring.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        model.addAttribute("user", user);
        model.addAttribute("ratings", user.getRatings());
        model.addAttribute("toBeRated", userService.getAlbumsToBeRatedForUser(userId));
        return "/user/userpage";
    }
    
    @RequestMapping(value = "/app/user/{userId}/rate", method = RequestMethod.GET)
    public String showPageForRatingAlbum(Model model, @PathVariable Long userId, Principal principal) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("toBeRated", userService.getAlbumsToBeRatedForUser(userId));
        model.addAttribute("openId", principal.getName());
        return "/user/userratingpage";
    }
    
    @RequestMapping(value = "/app/user/{userId}/album/{albumId}/rate/{rating}", method = RequestMethod.GET)
    public String showPageForRatingAlbum(@PathVariable Long albumId, @PathVariable Integer rating, @PathVariable Long userId, Principal principal) {
        albumService.addRating(albumId, rating, principal.getName());
        albumService.updateAverageRating(albumService.getAlbum(albumId).getAlbumName());
        return "redirect:/app/user/{userId}";
    }
    
    @RequestMapping(value = "/app/user/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "/user/login";
    }
    
}
