package com.fstack.phong_tro_fstack.client.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse extends BaseResponse{
    private String name;
    private String description;
    private String image;
    private String video;
    private Float electricService;
    private Float waterService;
    private Float rentPrice;
    private Float acreage;


}
