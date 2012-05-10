package wad.spring.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    private String name;
    
    private String openIdIdentifier;
       
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Album> albums;
    
    @OneToMany(cascade= CascadeType.ALL)
    private List<Rating> ratings;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenIdIdentifier() {
        return openIdIdentifier;
    }

    public void setOpenIdIdentifier(String openIdIdentifier) {
        this.openIdIdentifier = openIdIdentifier;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public void addAlbum(Album album) {
        if (!this.albums.contains(album)) {
            this.albums.add(album);
        }
    }
    
    public Rating addRating(Album album, int rating) {
        Rating r = new Rating();
        r.setAlbum(album);
        r.setRater(this);
        r.setStars(rating);
        
        this.ratings.add(r);
        
        return r;
    }
}