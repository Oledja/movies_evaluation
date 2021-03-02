package com.oleg.prylipko.service;

import com.oleg.prylipko.domain.User;
import com.oleg.prylipko.dto.user.UserCreateDTO;
import com.oleg.prylipko.dto.user.UserPatchDTO;
import com.oleg.prylipko.dto.user.UserReadDTO;
import com.oleg.prylipko.exception.EntityNotFoundException;
import com.oleg.prylipko.repository.repositoryhelper.RepositoryHelper;
import com.oleg.prylipko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepositoryHelper repositoryHelper;

    public UserReadDTO getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(User.class, id);
        });
        return toRead(user);
    }

    public UserReadDTO createUser(UserCreateDTO create) {
        User user = new User();
        user.setName(create.getName());
        user.setUserName(create.getUserName());
        user.setSex(create.getSex());

        user = userRepository.save(user);
        return toRead(user);
    }

    public UserReadDTO patchUser(UUID id, UserPatchDTO patch) {
        User user = repositoryHelper.getEntityRequired(User.class, id);

        if (patch.getName() != null) {
            user.setName(patch.getName());
        }

        if (patch.getUserName() != null) {
            user.setUserName(patch.getUserName());
        }

        if (patch.getSex() != null) {
            user.setSex(patch.getSex());
        }

        user = userRepository.save(user);

        return toRead(user);
    }

    public void deleteUser(UUID id) {
        userRepository.delete(repositoryHelper.getEntityRequired(User.class, id));
    }

    private UserReadDTO toRead(User user) {
        UserReadDTO userDTO = new UserReadDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserName(user.getUserName());
        userDTO.setSex(user.getSex());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }
}
