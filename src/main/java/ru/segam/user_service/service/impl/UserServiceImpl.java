package ru.segam.user_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.segam.user_service.dto.request.AuthUserRequest;
import ru.segam.user_service.dto.request.RegisterUserRequest;
import ru.segam.user_service.dto.request.UpdatePasswordRequest;
import ru.segam.user_service.dto.request.UpdateUserInfoRequest;
import ru.segam.user_service.dto.response.*;
import ru.segam.user_service.entity.User;
import ru.segam.user_service.exception.IncorrectPasswordException;
import ru.segam.user_service.exception.InvalidRequestException;
import ru.segam.user_service.exception.NickNameBusyException;
import ru.segam.user_service.exception.UserNotFoundException;
import ru.segam.user_service.mapper.UserMapper;
import ru.segam.user_service.repository.UserRepository;
import ru.segam.user_service.service.UserService;
import ru.segam.user_service.util.UserValidator;


/**
 * Сервис для работы с пользователями
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final UserValidator validator;

    @Autowired
    public UserServiceImpl(
            @Qualifier(value = "userRepositoryImplWithHibernate")
            UserRepository userRepository,
            UserMapper mapper, UserValidator validator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    /**
     * Получение списка информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает информацию о пользователях постранично
     */
    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable);
        List<UserResponse> usersResponse = new ArrayList<>();
        for (User u : users) {
            usersResponse.add(mapper.userToUserResponse(u));
        }
        return new PageImpl(usersResponse);
    }

    /**
     * Регистрация нового пользователя в системе
     *
     * @param userRequest информация о новом пользователе
     * @return возвращает ответ с информацией о новом пользователе
     */
    @Override
    public RegisterUserResponse registerNewUser(RegisterUserRequest userRequest) {
        String nickName = userRequest.getNickName();
        String email = userRequest.getEmail();
        User user;
        if (validator.isValid(userRequest)) {
            user = mapper.userRequestToUser(userRequest);
            if (userRepository.existsByNickname(nickName)
                    || userRepository.existsByEmail(email)
            ) {
                throw new NickNameBusyException(nickName + " " + email);
            } else {
                userRepository.save(user);
            }
        } else {
            throw new InvalidRequestException("");
        }
        return mapper.userToRegisterUserResponse(user);
    }

    /**
     * Изменение пароля конкретного пользователя
     *
     * @param updatePasswordRequest новая информация о пользователе
     * @return возвращает ответ что пароль изменен
     */
    @Override
    public UpdatePasswordResponse updateUser(
            UpdatePasswordRequest updatePasswordRequest) {
        //TODO проверить работу метода
        String nickName = updatePasswordRequest.getNickName();
        String pass = updatePasswordRequest.getPassword();
        String newPass = updatePasswordRequest.getNewPassword();
        if (validator.isValid(updatePasswordRequest)) {
            if (userRepository.existsByNickname(nickName)) {
                User user = userRepository.findByNickname(nickName);
                if (user.getPassword().equals(pass)) {
                    user.setPassword(newPass);
                    userRepository.update(user);
                    return new UpdatePasswordResponse("Password updated");
                } else {
                    throw new IncorrectPasswordException(nickName);
                }
            } else {
                throw new UserNotFoundException(nickName);
            }
        } else {
            throw new InvalidRequestException("");
        }
    }

    /**
     * Изменение информации о пользователе в системе
     *
     * @param updateInfoRequest новая информация о пользователе
     * @return возвращает ответ с новой информацией о пользователе
     */
    @Override
    public UpdateUserInfoResponse updateUser(UpdateUserInfoRequest updateInfoRequest) {
        String nickName = updateInfoRequest.getNickName();
        String email = updateInfoRequest.getEmail();
        String pass = updateInfoRequest.getPassword();

        if (validator.isValid(updateInfoRequest)) {
            if (userRepository.existsByNickname(nickName)
                    || userRepository.existsByEmail(email)) {
                User user = userRepository.findByNickname(nickName);
                if (user.getPassword().equals(pass)) {
                    user.setFirstName(updateInfoRequest.getFirstName());
                    user.setEmail(updateInfoRequest.getEmail());
                    user.setAge(updateInfoRequest.getAge());
                    userRepository.update(user);
                    return UpdateUserInfoResponse.builder()
                            .nickName(updateInfoRequest.getNickName())
                            .firstName(updateInfoRequest.getFirstName())
                            .email(updateInfoRequest.getEmail())
                            .age(updateInfoRequest.getAge())
                            .build();
                } else {
                    throw new IncorrectPasswordException(nickName);
                }
            } else {
                throw new UserNotFoundException(nickName);
            }
        } else {
            throw new InvalidRequestException("");
        }
    }

    /**
     * Авторизация пользователя
     *
     * @param authUserRequest информация о пользователе для авторизации
     * @return возвращает ответ для авторизации пользователя
     */
    @Override
    public AuthUserResponse authUser(AuthUserRequest authUserRequest) {
        String nickName = authUserRequest.getNickName();
        String pass = authUserRequest.getPassword();
        if (validator.isValid(authUserRequest)) {
            if (userRepository.existsByNickname(nickName)) {
                User user = userRepository.findByNickname(nickName);
                if (user.getPassword().equals(pass)) {
                    return AuthUserResponse.builder()
                            .secret("user")
                            .jwt(UUID.randomUUID().toString())
                            .build();
                } else {
                    throw new IncorrectPasswordException(nickName);
                }
            } else {
                throw new UserNotFoundException(nickName);
            }
        } else {
            throw new InvalidRequestException("");
        }
    }
}
