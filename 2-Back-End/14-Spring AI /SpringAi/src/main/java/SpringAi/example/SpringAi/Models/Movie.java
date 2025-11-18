package SpringAi.example.SpringAi.Models;

import java.time.LocalDate;

public class Movie {
    private String movieName;
    private Integer rating;
    private String director;
    private LocalDate releaseDate;

    // Constructors
    public Movie() {}

    public Movie(String movieName, Integer rating, String director, LocalDate releaseDate) {
        this.movieName = movieName;
        this.rating = rating;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    // Getters and setters
    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}