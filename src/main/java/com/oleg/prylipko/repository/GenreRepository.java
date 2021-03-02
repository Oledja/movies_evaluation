package com.oleg.prylipko.repository;

import com.oleg.prylipko.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends CrudRepository<Genre, UUID> {
}
