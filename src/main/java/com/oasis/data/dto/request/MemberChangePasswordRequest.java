package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberChangePasswordRequest {
    
    private Long sid;
    private String oldPassword;
    private String newPassword;
    
}
