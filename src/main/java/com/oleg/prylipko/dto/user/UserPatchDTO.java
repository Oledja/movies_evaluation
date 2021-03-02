package com.oleg.prylipko.dto.user;

import com.oleg.prylipko.domain.Gender;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserPatchDTO {

    private String name;
    private String userName;
    private LocalDate birthday;
    private String country;
    private Gender sex;
    private int trustLevel;
    private double rating;
}
