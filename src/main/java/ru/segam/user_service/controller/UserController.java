package ru.segam.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.segam.user_service.dto.request.AuthUserRequest;
import ru.segam.user_service.dto.request.RegisterUserRequest;
import ru.segam.user_service.dto.request.UpdatePasswordRequest;
import ru.segam.user_service.dto.request.UpdateUserInfoRequest;
import ru.segam.user_service.dto.response.AuthUserResponse;
import ru.segam.user_service.dto.response.RegisterUserResponse;
import ru.segam.user_service.dto.response.UpdatePasswordResponse;
import ru.segam.user_service.dto.response.UpdateUserInfoResponse;
import ru.segam.user_service.dto.response.UserResponse;
import ru.segam.user_service.service.UserService;

/**
 * Контроллер для работы с пользователями
 */

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Получение списка информации о пользователях постранично
     *
     * @param page информация для пагинации
     * @param size информация для пагинации
     * @return возвращает информацию о пользователях постранично
     */
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getUsers(
//            @PageableDefault(page = 0, size = 20) @RequestBody Pageable pageable
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUsers(paging));
    }

    /**
     * Регистрация нового пользователя в системе
     *
     * @param userRequest информация о новом пользователе
     * @return возвращает ответ с информацией о новом пользователе
     */
    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerNewUser(
            @RequestBody RegisterUserRequest userRequest) {
        return ResponseEntity.ok(userService.registerNewUser(userRequest));
    }

    /**
     * Изменение информации о пользователе в системе
     *
     * @param updateInfoRequest новая информация о пользователе
     * @return возвращает ответ с новой информацией о пользователе
     */
    @PutMapping("/info")
    public ResponseEntity<UpdateUserInfoResponse> updateUser(
            @RequestBody UpdateUserInfoRequest updateInfoRequest) {
        return ResponseEntity.ok(userService.updateUser(updateInfoRequest));
    }

    /**
     * Изменение пароля на учетную запись в системе
     *
     * @param updatePassRequest изменение пароля пользователе
     * @return возвращает ответ с измененным паролем о пользователе
     */
    @PutMapping()
    public ResponseEntity<UpdatePasswordResponse> updatePasswordUser(
            @RequestBody UpdatePasswordRequest updatePassRequest) {
        return ResponseEntity.ok(userService.updateUser(updatePassRequest));
    }

    /**
     * Авторизация пользователя
     *
     * @param authUserRequest информация о пользователе для авторизации
     * @return возвращает ответ для авторизации пользователя
     */
    @PostMapping("/auth")
    public ResponseEntity<AuthUserResponse> authUser(
            @RequestBody AuthUserRequest authUserRequest) {
        return ResponseEntity.ok(userService.authUser(authUserRequest));
    }
}
