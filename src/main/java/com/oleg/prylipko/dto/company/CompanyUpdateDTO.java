package com.oleg.prylipko.dto.company;

import com.oleg.prylipko.dto.movie.MovieReadDTO;
import lombok.Data;
import java.util.List;

@Data
public class CompanyUpdateDTO {

    private String name;
    private String country;
    private List<MovieReadDTO> movies;
}
