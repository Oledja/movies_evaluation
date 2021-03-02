package com.oleg.prylipko.repository.basetestrepository;

import com.oleg.prylipko.domain.AbstractEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BaseTestRepository extends CrudRepository<AbstractEntity, UUID> {
}
