package com.fstack.phong_tro_fstack.client.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends BaseResponse{
    private String fullName;

    public UserResponse(Long id, String fullName) {
        super(id);
        this.fullName = fullName;
    }
}
