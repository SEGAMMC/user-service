package ru.segam.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdatePasswordRequest {

    private String nickName;

    private String password;

    private String newPassword;

}
