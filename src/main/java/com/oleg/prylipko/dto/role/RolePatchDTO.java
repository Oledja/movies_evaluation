package com.oleg.prylipko.dto.role;

import com.oleg.prylipko.domain.RoleType;
import com.oleg.prylipko.dto.user.UserReadDTO;
import lombok.Data;
import java.util.List;

@Data
public class RolePatchDTO {

    private RoleType roleType;
    private List<UserReadDTO> users;
}
