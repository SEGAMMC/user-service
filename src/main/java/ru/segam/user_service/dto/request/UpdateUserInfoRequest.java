package ru.segam.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdateUserInfoRequest {

    private String nickName;

    private String firstName;

    private String email;

    private int age;

    private String password;

}
