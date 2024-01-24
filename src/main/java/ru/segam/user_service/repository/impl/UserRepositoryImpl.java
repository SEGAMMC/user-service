package ru.segam.user_service.repository.impl;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.segam.user_service.entity.User;
import ru.segam.user_service.repository.UserRepository;


/**
 * Класс для работы с базой данных с пользователями
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    /**
     * Получение информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает список информации о пользователях
     */
    @Override
    public List<User> findAll(Pageable pageable) {
        Collection<User> values = users.values();
        return new ArrayList<>(values);
    }

    /**
     * Сохранение пользователя
     *
     * @param user информация о пользователе
     * @return возвращает ответ true если успешно сохранен в базу
     */
    @Override
    public boolean save(User user) {
        users.put(user.getNickName(), user);
        User userInData = users.get(user.getNickName());
        return userInData.equals(user);
    }

    /**
     * Получение информации о пользователе с nickname
     *
     * @param nickname ник пользователя
     * @return возвращает информацию о пользователе
     */
    @Override
    public User findByNickname(String nickname) {
        return users.get(nickname);
    }

    /**
     * Проверяет существует ли в базе пользователь стаким ником
     *
     * @param nickname ник пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    @Override
    public boolean existsByNickname(String nickname) {
        return users.containsKey(nickname);
    }

    /**
     * Обновляет информацию о пользователе
     *
     * @param user пользователь (entity)
     * @return возвращает ответ true если в базе успешно изменили пользователя
     */
    public boolean update(User user) {
        users.remove(user.getNickName());
        users.put(user.getNickName(), user);
        User userInData = users.get(user.getNickName());
        return userInData.equals(user);
    }


    //блок инициализации
    @SuppressWarnings("checkstyle:MagicNumber")
    @PostConstruct
    public void init() {
        User user1 = new User(
                "Bulochka", "Katya", "katya.bulochka@mail.com", 15, "qq123456789");
        User user2 = new User(
                "Killer777", "Nikolay", "kolya.killer@mail.com", 10, "xs9997777");
        User user3 = new User(
                "Joker", "Nick", "joker@mail.com", 20, "aaa112345655");
        User user4 = new User(
                "Nikoteen", "Aleksey", "aleksey@mail.com", 29, "zzz111zzz111");
        User user5 = new User(
                "Alcoholic", "Andrey", "andre.alco@mail.com", 13, "a7z89x5c6v2");
        User user6 = new User(
                "xXxSERGEYxXx", "Sergey", "sergey@mail.com", 23, "XX999XXX");
        User user7 = new User(
                "PoMiDoR", "Petr", "petya@mail.com", 26, "cc23664599");
        User user8 = new User(
                "mr.BROWN", "Alim", "alim.brown@mail.com", 33, "iq180");
        User user9 = new User(
                "Jimmy_Neyrone", "Jimmy", "jimm.king@mail.com", 12, "123456789");
        User user10 = new User(
                "Elya", "Elena", "elena.tomskaya@mail.com", 10, "555222333");

        users.put(user1.getNickName(), user1);
        users.put(user2.getNickName(), user2);
        users.put(user3.getNickName(), user3);
        users.put(user4.getNickName(), user4);
        users.put(user5.getNickName(), user5);
        users.put(user6.getNickName(), user6);
        users.put(user7.getNickName(), user7);
        users.put(user8.getNickName(), user8);
        users.put(user9.getNickName(), user9);
        users.put(user10.getNickName(), user10);
    }

}
