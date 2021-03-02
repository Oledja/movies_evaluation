package com.oleg.prylipko.dto.role;

import com.oleg.prylipko.domain.RoleType;
import lombok.Data;

@Data
public class RoleCreateDTO {

    private RoleType roleType;
}
