package com.fstack.phong_tro_fstack.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RoleEntity extends BaseEntity{
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//    @ToString.Exclude // không sử dụng trong toString()
//    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities;

//	public RoleEntity(Long id, String name, Set<UserRoleEntity> userRoleEntities) {
//		super(id);
//		this.name = name;
//		this.userRoleEntities = userRoleEntities;
//	}
//    
    
}
