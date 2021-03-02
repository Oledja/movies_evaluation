package com.oleg.prylipko.repository.repositoryhelper;

import com.oleg.prylipko.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.UUID;

@Component
public class RepositoryHelper {

    @PersistenceContext
    private EntityManager entityManager;


    public <E> E getReference(Class<E> entityClass, UUID id) {
        return entityManager.getReference(entityClass, id);
    }

    public <E> void validateExist(Class<E> entityClass, UUID id) {
        Query query = entityManager.createQuery(
                "select count(e) from " + entityClass.getSimpleName() + " e where e.id = :id");
        query.setParameter("id", id);
        boolean exists = ((Number) query.getSingleResult()).intValue() > 0;
        if (!exists) {
            throw new EntityNotFoundException(entityClass, id);
        }
    }

    public <E> E getEntityRequired(Class<E> entityClass, UUID id) {
        validateExist(entityClass, id);
        return entityManager.find(entityClass, id);
    }
}
