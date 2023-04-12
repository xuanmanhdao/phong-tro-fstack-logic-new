package com.fstack.phong_tro_fstack.client.output.post;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedPostResponse {
    private List<PostResponse> postResponses;
    private int totalPage;
    private int pageNumber;
    private int pageSize;
}
