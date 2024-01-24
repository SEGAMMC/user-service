package ru.segam.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthUserResponse {

    private String secret;

    private String jwt;

}
