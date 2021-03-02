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
@Table(name = "users")
public class User extends AbstractEntity {

    private String name;
    private String userName;
    private LocalDate birthday;
    private String country;

    @Enumerated(EnumType.STRING)
    private Gender sex;
    private int trustLevel;
    private double rating;
    private boolean isBlocked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

}
