package com.oleg.prylipko.repository;

import com.oleg.prylipko.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends CrudRepository<Movie, UUID> {
}
