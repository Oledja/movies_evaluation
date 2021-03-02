package com.oleg.prylipko.dto.user;

import com.oleg.prylipko.domain.Gender;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserReadDTO {

    private UUID id;
    private String name;
    private String userName;
    private LocalDate birthday;
    private String country;
    private Gender sex;
    private int trustLevel;
    private double rating;
    private boolean isBlocked;
    private Instant createdAt;
    private Instant updatedAt;

}
