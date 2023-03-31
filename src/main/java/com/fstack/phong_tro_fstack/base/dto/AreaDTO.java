package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO extends BaseDTO{
    private String name;

    private String longitude;

    private String latitude;

    private String ward;

    private String district;

    private String city;
}
