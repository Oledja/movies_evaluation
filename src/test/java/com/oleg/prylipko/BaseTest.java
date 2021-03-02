package com.oleg.prylipko;

import com.oleg.prylipko.domain.AbstractEntity;
import com.oleg.prylipko.repository.basetestrepository.BaseTestRepository;
import org.bitbucket.brunneng.br.Configuration;
import org.bitbucket.brunneng.br.RandomObjectGenerator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {
        "delete from users",
        "delete from movie",
        "delete from company",
        "delete from genre",
        "delete from movie_genres",
        "delete from movie_companies",
        "delete from role",
        "delete from user_roles",

}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class BaseTest {

    @Autowired
    private BaseTestRepository baseTestRepository;
    private RandomObjectGenerator generator = new RandomObjectGenerator();

    protected <T extends AbstractEntity> T generateEntityWithoutId(Class<T> entityClass) {
        T entity = generator.generateRandomObject(entityClass);
        entity.setId(null);
        return entity;
    }

    protected <T extends AbstractEntity> T generateFlatEntityAndSave(Class<T> entityClass) {
        T entity = generator.generateRandomObject(entityClass);
        entity.setId(null);
        return baseTestRepository.save(entity);
    }

    protected <T extends AbstractEntity> T generateEntity(Class<T> entityClass) {
        T entity = generator.generateRandomObject(entityClass);
        return entity;
    }

    protected <T extends AbstractEntity> T generateFlatEntityWithoutId(Class<T> entityClass) {
        T entity = flatGenerator.generateRandomObject(entityClass);
        entity.setId(null);
        return entity;
    }

    private RandomObjectGenerator flatGenerator;
    {
        Configuration c = new Configuration();
        c.setFlatMode(true);
        flatGenerator = new RandomObjectGenerator(c);
    }
 }
