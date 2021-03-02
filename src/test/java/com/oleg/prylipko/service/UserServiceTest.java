package com.oleg.prylipko.service;

import com.oleg.prylipko.BaseTest;
import com.oleg.prylipko.domain.Gender;
import com.oleg.prylipko.domain.User;
import com.oleg.prylipko.dto.user.UserCreateDTO;
import com.oleg.prylipko.dto.user.UserPatchDTO;
import com.oleg.prylipko.dto.user.UserReadDTO;
import com.oleg.prylipko.exception.EntityNotFoundException;
import com.oleg.prylipko.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        User user = createUser();
        UserReadDTO userReadDTO = userService.getUser(user.getId());
        Assertions.assertThat(userReadDTO).isEqualToComparingFieldByField(user);
    }

    @Test
    public void testCreateUser() {
        UserCreateDTO create = new UserCreateDTO();
        create.setName("Joe");
        create.setUserName("Joe88");
        create.setSex(Gender.MALE);

        UserReadDTO read = userService.createUser(create);

        Assertions.assertThat(create).isEqualToIgnoringGivenFields(read, "createdAt", "updatedAt");
        Assert.assertNotNull(read.getId());

        User user = userRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(user);
    }

    @Test
    public void testPatchUser () {
        User user = createUser();
        UserPatchDTO patch = new UserPatchDTO();
        patch.setName("Emma");
        patch.setUserName("Emma77");
        patch.setSex(Gender.FEMALE);

        UserReadDTO read = userService.patchUser(user.getId(), patch);

        Assertions.assertThat(patch).isEqualToIgnoringGivenFields(read, "createdAt", "updatedAt");

        user = userRepository.findById(read.getId()).get();
        Assertions.assertThat(user).isEqualToIgnoringGivenFields(read, "roles");
    }

    @Test
    public void testPatchUserEmptyPatch() {
        User user = createUser();
        UserPatchDTO patch = new UserPatchDTO();
        UserReadDTO read = userService.patchUser(user.getId(), patch);

        Assert.assertNotNull(read.getName());
        Assert.assertNotNull(read.getUserName());
        Assert.assertNotNull(read.getId());

        User userAfterUpdate = userRepository.findById(read.getId()).get();


        Assert.assertNotNull(userAfterUpdate.getName());
        Assert.assertNotNull(userAfterUpdate.getUserName());
        Assert.assertNotNull(userAfterUpdate.getSex());

        Assertions.assertThat(user).isEqualToIgnoringGivenFields(userAfterUpdate, "createdAt", "updatedAt", "roles");
    }

    @Test
    public void testDeleteUser() {
        User user = createUser();

        userService.deleteUser(user.getId());
        Assert.assertFalse(userRepository.existsById(user.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteUserNotFound() {
        userService.deleteUser(UUID.randomUUID());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetUserWrongId() {
        userService.getUser(UUID.randomUUID());
    }

    private User createUser() {
        User user = new User();
        user.setName("Joe");
        user.setUserName("Joe88");
        user.setSex(Gender.MALE);
        return userRepository.save(user);
    }
}
