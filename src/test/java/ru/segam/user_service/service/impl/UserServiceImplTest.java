package ru.segam.user_service.service.impl;

import org.junit.jupiter.api.Test;
import ru.segam.user_service.dto.request.RegisterUserRequest;
import ru.segam.user_service.dto.response.RegisterUserResponse;
import ru.segam.user_service.exception.InvalidRequestException;
import ru.segam.user_service.mapper.UserMapper;
import ru.segam.user_service.repository.impl.UserRepositoryImpl;
import ru.segam.user_service.service.UserService;
import ru.segam.user_service.util.UserValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {

    private UserService userService;

    public UserServiceImplTest() {
        this.userService = new UserServiceImpl(new UserRepositoryImpl(),
                new UserMapper(), new UserValidator());
    }

    @Test
    void testRegisterNewUserThenAllOK1() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("Criss")
                .firstName("Carrl")
                .email("carl.coral@mail.com")
                .age(16)
                .password("scZsc")
                .build();
        RegisterUserResponse userInDataActual = userService.registerNewUser(userRequest);

        RegisterUserResponse userExpected = RegisterUserResponse.builder()
                .nickName("Criss")
                .firstName("Carrl")
                .email("carl.coral@mail.com")
                .age(16)
                .build();

        assertEquals(userExpected, userInDataActual);
    }

    @Test
    void testRegisterNewUserThenAllOK2() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("Hirokko")
                .firstName("Sema_li")
                .email("semail.li@mail.com")
                .age(35)
                .password("qwerty")
                .build();
        RegisterUserResponse userInData = userService.registerNewUser(userRequest);

        RegisterUserResponse user = RegisterUserResponse.builder()
                .nickName("Hirokko")
                .firstName("Sema_li")
                .email("semail.li@mail.com")
                .age(35)
                .build();

        assertEquals(user, userInData);
    }

    @Test
    void testRegisterNewUserThenInvalidNickName() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("H")
                .firstName("Sema_li")
                .email("semail.li@mail.com")
                .age(35)
                .password("qwerty")
                .build();
        assertThrows(InvalidRequestException.class,
                () -> userService.registerNewUser(userRequest));
    }

    @Test
    void testRegisterNewUserThenInvalidFirstName() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("Hirokko")
                .firstName("S")
                .email("semail.li@mail.com")
                .age(35)
                .password("qwerty")
                .build();
        assertThrows(InvalidRequestException.class,
                () -> userService.registerNewUser(userRequest));
    }

    @Test
    void testRegisterNewUserThenInvalidEmail() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("Hirokko")
                .firstName("Serjo")
                .email("se")
                .age(35)
                .password("qwerty")
                .build();
        assertThrows(InvalidRequestException.class,
                () -> userService.registerNewUser(userRequest));
    }

    @Test
    void testRegisterNewUserThenInvalidAge() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("Hirokko")
                .firstName("Serjo")
                .email("serjo@mail.com")
                .age(-10)
                .password("qwerty")
                .build();
        assertThrows(InvalidRequestException.class,
                () -> userService.registerNewUser(userRequest));
    }

    @Test
    void testRegisterNewUserThenInvalidPassword() {
        RegisterUserRequest userRequest = RegisterUserRequest.builder()
                .nickName("Hirokko")
                .firstName("Serjo")
                .email("serjo@mail.com")
                .age(30)
                .password("qw")
                .build();
        assertThrows(InvalidRequestException.class,
                () -> userService.registerNewUser(userRequest));
    }

}