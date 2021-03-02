package com.oleg.prylipko.controller;

import com.oleg.prylipko.dto.user.UserCreateDTO;
import com.oleg.prylipko.dto.user.UserPatchDTO;
import com.oleg.prylipko.dto.user.UserReadDTO;
import com.oleg.prylipko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserReadDTO getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserReadDTO createUser(@RequestBody UserCreateDTO createDTO) {
        return userService.createUser(createDTO);
    }

    @PatchMapping("/{id}")
    public UserReadDTO patchUser(@PathVariable UUID id, @RequestBody UserPatchDTO patch) {
        return userService.patchUser(id, patch);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
