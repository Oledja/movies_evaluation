package com.oleg.prylipko.dto.role;

import com.oleg.prylipko.domain.RoleType;
import com.oleg.prylipko.dto.user.UserReadDTO;
import lombok.Data;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class RoleReadDTO {

    private UUID id;
    private RoleType roleType;
    private List<UserReadDTO> users;
    private Instant createdAt;
    private Instant updatedAt;
}
