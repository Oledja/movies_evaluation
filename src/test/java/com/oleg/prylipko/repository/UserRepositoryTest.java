package com.oleg.prylipko.repository;

import com.oleg.prylipko.domain.Gender;
import com.oleg.prylipko.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from users", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User();
        user = userRepository.save(user);
        assertNotNull(user.getId());
        assertTrue(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        User user = new User();
        user.setName("Joe");
        user.setUserName("Joe88");
        user.setSex(Gender.MALE);
        user = userRepository.save(user);

        Instant createdAtBeforeReload = user.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        user = userRepository.findById(user.getId()).get();

        Instant createdAtAfterReload = user.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }
}
