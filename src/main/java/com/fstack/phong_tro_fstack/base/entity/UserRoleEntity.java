package com.fstack.phong_tro_fstack.base.entity;

import com.fstack.phong_tro_fstack.base.entity.compositekey.UserRoleKey;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    @EmbeddedId
    private UserRoleKey userRoleKey;

    @ManyToOne
    @MapsId("idUser")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // không sử dụng trong toString()
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("idRole")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // không sử dụng trong toString()
    @JoinColumn(name = "id_role")
    private RoleEntity roleEntity;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

//    @ManyToOne
//    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//    @ToString.Exclude // không sử dụng trong toString()
//    @JoinColumn(name="created_by")
//    private UserEntity createdBy;
}
