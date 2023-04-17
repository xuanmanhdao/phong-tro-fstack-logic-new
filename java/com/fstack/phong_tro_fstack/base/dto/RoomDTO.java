package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO extends BaseDTO{
    private String name;

    private String image;

    private String video;

    private Float electricService;

    private Float waterService;

    private Float rentPrice;

    private Float acreage;

    private String description;

    private Long idArea;
}
