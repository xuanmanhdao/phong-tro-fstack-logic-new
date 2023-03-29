package com.fstack.phong_tro_fstack.base.entity.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserRoleKey implements Serializable {
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_role")
    private Long idRole;
}
