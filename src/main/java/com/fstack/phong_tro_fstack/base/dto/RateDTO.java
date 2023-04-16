package com.fstack.phong_tro_fstack.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO extends BaseDTO{
    private Integer ratingStarts;

    private String comment;

    private Date createdAt;

    private Long idUser;

    private Long idArea;
}
