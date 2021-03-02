package com.oleg.prylipko.dto.genre;

import com.oleg.prylipko.domain.GenreType;
import lombok.Data;

@Data
public class GenreCreateDTO {

    private GenreType genreType;
}
