package ru.segam.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdateUserInfoResponse {

    private String nickName;

    private String firstName;

    private String email;

    private int age;

}
