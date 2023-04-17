package com.fstack.phong_tro_fstack.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "account_verification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVerificationEntity extends BaseEntity{
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "token", length = 255, nullable = false)
    private String token;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
}
