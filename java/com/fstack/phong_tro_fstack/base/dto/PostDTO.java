package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO extends BaseDTO{
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

    private Long idUser;

    private Long idArea;
}
