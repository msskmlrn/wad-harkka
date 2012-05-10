package wad.spring.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "ALBUMS")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @NotNull
    @Pattern(regexp="[\\w+[ åÅäÄöÖ]]+", message="Name can only contain normal characters and it must be at least 1 character long")
    private String albumName;
    
    @NotNull
    @Pattern(regexp="[\\w+[ åÅäÄöÖ]]+", message="Name can only contain normal characters and it must be at least 1 character long")
    private String albumArtist;
    
    @NotNull
    @Pattern(regexp="[\\w+[ åÅäÄöÖ]]+", message="Name can only contain normal characters and it must be at least 1 character long")
    private String genre;
    
    @NotNull  
    @Size(min = 4, max = 4, message = "Year consists of four numbers.")
    @Pattern(regexp="[\\d]+", message="Year can only contain numbers")
    private String releaseYear;
    
    @NotNull
    @Pattern(regexp="[\\w+[ åÅäÄöÖ]]+", message="Name can only contain normal characters and it must be at least 1 character long")
    private String description;
    
    @ManyToMany(mappedBy="albums")
    private List<User> owners = new ArrayList<User>();
    
    @OneToMany(mappedBy = "album", fetch= FetchType.EAGER)
    private List<Rating> albumRatings = new ArrayList<Rating>();
    
    private double averageRating;
    
    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    public List<Rating> getAlbumRatings() {
        return albumRatings;
    }

    public void setAlbumRatings(List<Rating> albumRatings) {
        this.albumRatings = albumRatings;
    }

    public void addOwner(User owner) {
        if (!this.owners.contains(owner)) {
            this.owners.add(owner);
        }
        owner.addAlbum(this);
    }
    
    public void addAlbumRating(Rating rating) {
        this.albumRatings.add(rating);
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}