package com.oleg.prylipko.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Company extends AbstractEntity {
    private String name;
    private String country;

    @ManyToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Movie> movies;
}
