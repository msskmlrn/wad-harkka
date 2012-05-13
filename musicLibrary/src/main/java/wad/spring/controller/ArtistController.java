package wad.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.spring.domain.Album;
import wad.spring.service.AlbumService;

@Controller
public class ArtistController {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/app/artist/{artistName}", method = RequestMethod.GET)
    public String showArtistPage(Model model, @PathVariable String artistName) {
        List<Album> albums = albumService.getAlbumsFromArtist(artistName);

        if (albums.isEmpty()) {
            return "redirect:/app/album";
        }
        if (artistName.isEmpty()) {
            return "redirect:/app/album";
        }
        model.addAttribute("albums", albums);
        model.addAttribute("artist", artistName);
        return "/artist/artistpage";
    }

    @RequestMapping(value = "/app/artist/", method = RequestMethod.GET)
    public String showArtistPage(Model model) {
        return "redirect:/app/album";
    }
}    