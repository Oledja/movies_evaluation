package com.oleg.prylipko.dto.user;

import com.oleg.prylipko.domain.Gender;
import java.time.LocalDate;

public class UserUpdateDTO {

    private String name;
    private String userName;
    private LocalDate birthday;
    private String country;
    private Gender sex;
    private int trustLevel;
    private double rating;
}
