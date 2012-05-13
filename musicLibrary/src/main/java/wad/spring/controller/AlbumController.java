package wad.spring.controller;

import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wad.spring.domain.Album;
import wad.spring.service.AlbumService;
import wad.spring.service.SecureService;
import wad.spring.service.UserService;

@Controller
public class AlbumController {

    @Autowired
    SecureService secureService;
    @Autowired
    AlbumService albumService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/app/album", method = RequestMethod.GET)
    public String showFrontPage(Model model) {
        model.addAttribute("albums", albumService.getAlbums());
        model.addAttribute("users", userService.getUsers());
        return "/index";
    }

    @RequestMapping(value = "/app/album", method = RequestMethod.POST)
    public String addAlbumToCollection(@RequestParam Long albumId, Principal principal) {
        albumService.addAlbumToCollection(albumId, principal.getName());
        return "redirect:/app/album";
    }

    @RequestMapping(value = "/app/album/{albumId}", method = RequestMethod.GET)
    public String showAlbumPage(Model model, @PathVariable Long albumId) {
        Album album = albumService.getAlbum(albumId);
        
        if (album == null) {
            return "redirect:/app/album";
        }
        
        model.addAttribute("album", album);
        model.addAttribute("owners", album.getOwners());

        return "/album/albumpage";
    }

    @RequestMapping(value = "/app/album/new", method = RequestMethod.GET)
    public String newAlbum(Model model) {
        model.addAttribute("album", new Album());
        return "/album/form";
    }

    @RequestMapping(value = "/app/album/new", method = RequestMethod.POST)
    public String newAlbumPost(@Valid @ModelAttribute("album") Album album, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "/album/form";
        }
        albumService.createNewAlbum(album);
        return "redirect:/app/album";
    }

    @RequestMapping(value = "/app/test", method = RequestMethod.GET)
    public String populateData() {
        secureService.executeFreely();
        return "redirect:/app/album";
    }
}
