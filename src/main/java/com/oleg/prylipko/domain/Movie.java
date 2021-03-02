package com.oleg.prylipko.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Movie extends AbstractEntity {

    private String name;
    private String made;
    private String language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genres",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private List<Genre> genres;
    
    @ManyToMany
    @JoinTable(name = "movie_companies",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")})
    private List<Company> company;

    private int runningTime;
    private LocalDate release;
    private String title;
    private String posterPath;
    private int budget;
    private int revenue;
    private double averageRating;
    private int voteCount;
}
