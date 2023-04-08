package com.fstack.phong_tro_fstack.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileItemDTO implements Serializable {
    private String id;
    private String name;
    private String thumbnailLink;

}
