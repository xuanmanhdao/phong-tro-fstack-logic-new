package com.fstack.phong_tro_fstack.client.output.post;

import com.fstack.phong_tro_fstack.client.output.AreaResponse;
import com.fstack.phong_tro_fstack.client.output.BaseResponse;
import com.fstack.phong_tro_fstack.client.output.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse extends BaseResponse {
    private String thumbnail;
    private String title;
    private String content;
    private String phoneNumber;
    private String phoneZalo;
    private Integer status;
    private Date createdTime;
    private Date createdAt;
    private Date updatedAt;
    private Integer numberDate;
    private UserResponse userResponse;
    private AreaResponse areaResponse;
}
