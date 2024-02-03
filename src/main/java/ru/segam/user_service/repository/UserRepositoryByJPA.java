package ru.segam.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.segam.user_service.entity.User;

public interface UserRepositoryByJPA extends JpaRepository<User, Long> {
    boolean existsByNickName(String nickName);

    boolean existsByEmail(String email);

    User findByNickName(String nickName);

}
