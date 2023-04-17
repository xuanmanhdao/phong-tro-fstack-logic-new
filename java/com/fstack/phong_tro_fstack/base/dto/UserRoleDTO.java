package com.fstack.phong_tro_fstack.base.dto;

import com.fstack.phong_tro_fstack.base.dto.compostitekeydto.UserRoleKeyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    private UserRoleKeyDTO userRoleKeyDTO;

    private Date createdAt;
}
