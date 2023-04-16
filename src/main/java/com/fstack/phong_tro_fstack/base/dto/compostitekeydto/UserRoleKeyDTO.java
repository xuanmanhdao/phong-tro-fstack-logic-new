package com.fstack.phong_tro_fstack.base.dto.compostitekeydto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleKeyDTO implements Serializable {
    private Long idUser;
    private Long idRole;
}
