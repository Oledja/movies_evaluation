package com.oleg.prylipko.repository;

import com.oleg.prylipko.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}
