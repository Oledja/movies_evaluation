package com.oleg.prylipko.dto.company;

import com.oleg.prylipko.dto.movie.MovieReadDTO;
import lombok.Data;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class CompanyReadDTO {

    private UUID id;
    private String name;
    private String country;
    private List<MovieReadDTO> movies;
    private Instant createdAt;
    private Instant updatedAt;
}
