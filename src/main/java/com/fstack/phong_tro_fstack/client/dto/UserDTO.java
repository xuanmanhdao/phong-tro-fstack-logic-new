package com.fstack.phong_tro_fstack.client.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

  private Long id;
  private String email;
  private String fullName;
  private String password;
  private String idCard;
  private String phoneNumber;
  private Float balance;
  private Date createdAt;
}
