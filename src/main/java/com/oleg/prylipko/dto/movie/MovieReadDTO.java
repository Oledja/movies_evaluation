package com.oleg.prylipko.dto.movie;

import com.oleg.prylipko.dto.company.CompanyReadDTO;
import com.oleg.prylipko.dto.genre.GenreReadDTO;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MovieReadDTO {

    private UUID id;
    private String name;
    private String made;
    private String language;
    private List<GenreReadDTO> genres;
    private List<CompanyReadDTO> companies;
    private int runningTime;
    private LocalDate release;
    private String title;
    private String posterPath;
    private int budget;
    private int revenue;
    private double averageRating;
    private int voteCount;
    private Instant createdAt;
    private Instant updatedAt;
}
