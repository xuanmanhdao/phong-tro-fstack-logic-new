package com.fstack.phong_tro_fstack.client.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaResponse extends BaseResponse{
    private String name;

    private String longitude;

    private String latitude;

    private String exactAddress;

    private String provinceName;

    private String districtName;

    private String wardName;

    private List<RoomResponse> roomResponses;
}
