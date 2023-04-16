package com.fstack.phong_tro_fstack.client.dto;

import java.io.Serializable;
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
}
