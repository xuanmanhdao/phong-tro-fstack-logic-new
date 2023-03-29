package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO{
    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private String idCard;

    private Float balance;

    private Date createdAt;
}
