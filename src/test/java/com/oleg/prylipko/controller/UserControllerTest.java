package com.oleg.prylipko.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oleg.prylipko.domain.Gender;
import com.oleg.prylipko.domain.User;
import com.oleg.prylipko.dto.user.UserCreateDTO;
import com.oleg.prylipko.dto.user.UserPatchDTO;
import com.oleg.prylipko.dto.user.UserReadDTO;
import com.oleg.prylipko.exception.EntityNotFoundException;
import com.oleg.prylipko.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUser() throws Exception {

        UserReadDTO userReadDTO = createUserRead();

        Mockito.when(userService.getUser(userReadDTO.getId())).thenReturn(userReadDTO);

        String resultJson = mvc.perform(get("/api/v1/users/{id}", userReadDTO.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Mockito.verify(userService).getUser(userReadDTO.getId());

        UserReadDTO actualUser = objectMapper.readValue(resultJson, UserReadDTO.class);
        Assertions.assertThat(actualUser).isEqualToComparingFieldByField(userReadDTO);

        Mockito.verify(userService).getUser(userReadDTO.getId());
    }

    @Test
    public void testGetUserWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(User.class, wrongId);
        Mockito.when(userService.getUser(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/users/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(resultJson.contains(exception.getMessage()));
    }

    @Test
    public void testCreateUser() throws Exception {

        UserCreateDTO create = new UserCreateDTO();
        create.setName("Joe");
        create.setUserName("Joe88");
        create.setSex(Gender.MALE);

        UserReadDTO read = createUserRead();

        Mockito.when(userService.createUser(create)).thenReturn(read);

        String resultJson = mvc.perform(post("/api/v1/users")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        UserReadDTO actualUser = objectMapper.readValue(resultJson, UserReadDTO.class);
        Assertions.assertThat(actualUser).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchUser() throws Exception {

        UserPatchDTO patch = new UserPatchDTO();
        patch.setName("Joe");
        patch.setUserName("Joe88");
        patch.setSex(Gender.MALE);

        UserReadDTO read = createUserRead();

        Mockito.when(userService.patchUser(read.getId(), patch)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/users/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patch))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        UserReadDTO actualUser = objectMapper.readValue(resultJson, UserReadDTO.class);
        Assert.assertEquals(read, actualUser);
    }

    @Test
    public void testDeleteUser() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/users/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(userService).deleteUser(id);
    }

    private UserReadDTO createUserRead() {

        UserReadDTO read = new UserReadDTO();
        read.setId(UUID.randomUUID());
        read.setName("Joe");
        read.setUserName("Joe88");
        read.setSex(Gender.MALE);
        return read;
    }
}
