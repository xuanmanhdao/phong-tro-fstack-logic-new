package com.fstack.phong_tro_fstack.client.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String id;
    private String name;
    private String thumbnailLink;
    private String webViewLink;
    private String webContentLink;
}
