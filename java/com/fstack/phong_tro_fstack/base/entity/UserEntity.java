package com.fstack.phong_tro_fstack.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserEntity extends BaseEntity{
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 12, nullable = true)
    private String phoneNumber;

    @Column(name = "id_card", length = 20, nullable = true)
    private String idCard;

    @Column(name = "balance", columnDefinition = "float default 0")
    private Float balance;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//    @ToString.Exclude // không sử dụng trong toString()
//    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities;

    @OneToMany(mappedBy = "userEntity")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // không sử dụng trong toString()
    @JsonIgnore
    private Set<PostEntity> postEntities;

    @OneToMany(mappedBy = "userEntity")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // không sử dụng trong toString()
    @JsonIgnore
    private Set<RateEntity> rateEntities;

    @OneToMany(mappedBy = "userEntity")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // không sử dụng trong toString()
    @JsonIgnore
    private Set<TransactionEntity> transactionEntities;
}
