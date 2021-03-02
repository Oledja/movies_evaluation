package com.oleg.prylipko.dto.genre;

import com.oleg.prylipko.domain.GenreType;
import com.oleg.prylipko.dto.movie.MovieReadDTO;
import lombok.Data;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class GenreReadDTO {

    private UUID id;
    private GenreType genreType;
    private List<MovieReadDTO> movies;
    private Instant createdAt;
    private Instant updatedAt;
}
