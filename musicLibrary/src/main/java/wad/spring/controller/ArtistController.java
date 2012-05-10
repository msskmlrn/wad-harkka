
package wad.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.spring.service.AlbumService;

@Controller
public class ArtistController {
    
    @Autowired
    AlbumService albumService;
    
    @RequestMapping(value = "app/artist/{artistName}", method = RequestMethod.GET)
    public String showArtistPage(Model model, @PathVariable String artistName) {
        model.addAttribute("albums", albumService.getAlbumsFromArtist(artistName));
        model.addAttribute("artist", artistName);
        return "/artist/artistpage";
    }
}
