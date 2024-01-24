package ru.segam.user_service.mapper;

import org.springframework.stereotype.Component;
import ru.segam.user_service.dto.request.RegisterUserRequest;
import ru.segam.user_service.dto.request.UpdateUserInfoRequest;
import ru.segam.user_service.dto.response.RegisterUserResponse;
import ru.segam.user_service.dto.response.UpdateUserInfoResponse;
import ru.segam.user_service.dto.response.UserResponse;
import ru.segam.user_service.entity.User;

@Component
public class UserMapper {

    /**
     * Мапинг запроса с информацией о новом пользователе
     * в пользователя (entity)
     *
     * @param userRequest информация о новом пользователе
     * @return возвращает пользователя (entity)
     */
    public User userRequestToUser(RegisterUserRequest userRequest) {
        return User.builder()
                .nickName(userRequest.getNickName())
                .firstName(userRequest.getFirstName())
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .password(userRequest.getPassword())
                .build();
    }

    /**
     * Мапинг информации о пользователе (entity)
     * в ответ о новом пользователе
     *
     * @param user информация о новом пользователе (entity)
     * @return возвращает ответ о новом пользователе
     */
    public RegisterUserResponse userToRegisterUserResponse(User user) {
        return RegisterUserResponse.builder()
                .nickName(user.getNickName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    /**
     * Мапинг информации о пользователе (entity)
     * в ответ о пользователе
     *
     * @param user информация о новом пользователе (entity)
     * @return возвращает ответ о пользователе
     */
    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .nickName(user.getNickName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    /**
     * Мапинг обновленной информации о пользователе
     * в пользователя (entity)
     *
     * @param updateUserRequest обновленная информация о пользователе
     * @return возвращает пользователя с обновленной информацией (entity)
     */
    public User updateUserRequestToUser(
            UpdateUserInfoRequest updateUserRequest) {
        return User.builder()
                .firstName(updateUserRequest.getFirstName())
                .email(updateUserRequest.getEmail())
                .age(updateUserRequest.getAge())
                .build();
    }

    /**
     * Мапинг пользователя (entity)
     * в ответ об обновленнии информации пользователя
     *
     * @param user информация о пользователе (entity)
     * @return возвращает пользователя с обновленной информацией
     */
    public UpdateUserInfoResponse userToUpdateUserResponse(User user) {
        return UpdateUserInfoResponse.builder()
                .nickName(user.getNickName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }


}
