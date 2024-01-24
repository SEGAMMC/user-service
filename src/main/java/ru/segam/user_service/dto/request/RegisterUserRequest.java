package ru.segam.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String nickName;

    private String firstName;

    private String email;

    private int age;

    private String password;

}
