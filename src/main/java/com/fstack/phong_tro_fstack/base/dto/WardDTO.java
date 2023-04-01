package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardDTO{
    private char id;

    private String name;

    private String type;

    private char idDistrict;
}
