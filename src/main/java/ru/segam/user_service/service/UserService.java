package ru.segam.user_service.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.segam.user_service.dto.request.AuthUserRequest;
import ru.segam.user_service.dto.request.RegisterUserRequest;
import ru.segam.user_service.dto.request.UpdatePasswordRequest;
import ru.segam.user_service.dto.request.UpdateUserInfoRequest;
import ru.segam.user_service.dto.response.*;

public interface UserService {
    /**
     * Получение списка информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает информацию о пользователях постранично
     */
    Page<UserResponse> getUsers(Pageable pageable);

    /**
     * Регистрация нового пользователя в системе
     *
     * @param userRequest      информация о новом пользователе
     * @return возвращает ответ с информацией о новом пользователе
     */
    RegisterUserResponse registerNewUser(RegisterUserRequest userRequest);

    /**
     * Изменение информации о пользователе в системе
     *
     * @param updateInfoRequest новая информация о пользователе
     * @return возвращает ответ с новой информацией о пользователе
     */
    UpdateUserInfoResponse updateUser(UpdateUserInfoRequest updateInfoRequest);


    /**
     * Авторизация пользователя
     *
     * @param authUserRequest информация о пользователе для авторизации
     * @return возвращает ответ для авторизации пользователя
     */
    AuthUserResponse authUser(AuthUserRequest authUserRequest);

    /**
     * Изменение пароля конкретного пользователя
     *
     * @param updatePasswordRequest новая информация о пользователе
     * @return возвращает ответ что пароль изменен
     */
    UpdatePasswordResponse updateUser(UpdatePasswordRequest updatePasswordRequest);
}
