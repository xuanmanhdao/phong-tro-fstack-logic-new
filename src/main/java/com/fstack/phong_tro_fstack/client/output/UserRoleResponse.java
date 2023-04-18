package com.fstack.phong_tro_fstack.client.output;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse {
    private Long idUser;
    private Long idRole;
    private String email;
    private String fullName;
    private String roleName;
    private Date createdAt;
}
