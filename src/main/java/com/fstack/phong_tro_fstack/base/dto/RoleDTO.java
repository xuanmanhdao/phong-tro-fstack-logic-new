package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends BaseDTO{
    private String name;

    private Date createdAt;
}
