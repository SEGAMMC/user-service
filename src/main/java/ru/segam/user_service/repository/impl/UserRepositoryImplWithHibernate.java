package ru.segam.user_service.repository.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.segam.user_service.entity.User;
import ru.segam.user_service.repository.UserRepository;


/**
 * Класс для работы с базой данных с пользователями
 */
@Repository("userRepositoryImplWithHibernate")
@RequiredArgsConstructor
public class UserRepositoryImplWithHibernate implements UserRepository {

    private final SessionFactory sessionFactory;

    /**
     * Получение информации о пользователях постранично
     *
     * @param pageable информация для пагинации
     * @return возвращает список информации о пользователях
     */
    @Override
    public List<User> findAll(Pageable pageable) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("select u from User u", User.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    /**
     * Сохранение пользователя
     *
     * @param user информация о пользователе
     * @return возвращает ответ true если успешно сохранен в базу
     */
    @Override
    public boolean save(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    /**
     * Получение информации о пользователе с nickname
     *
     * @param nickname ник пользователя
     * @return возвращает информацию о пользователе
     */
    @Override
    public User findByNickname(String nickname) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.createQuery(
                        "select u from User u where nickName = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    /**
     * Проверяет существует ли в базе пользователь стаким ником
     *
     * @param nickname ник пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    @Override
    public boolean existsByNickname(String nickname) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery(
                        "SELECT COUNT(*) FROM User WHERE nickName =:nick", Long.class)
                .setParameter("nick", nickname);
        long count = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return count != 0;
    }

    /**
     * Проверяет существует ли в базе пользователь стаким email
     *
     * @param email email пользователя
     * @return возвращает ответ true если в базе присутствует
     */
    @Override
    public boolean existsByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery(
                        "SELECT COUNT(*) FROM User WHERE email =:email", Long.class)
                .setParameter("email", email);
        long count = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return count != 0;
    }


    /**
     * Обновляет информацию о пользователе
     *
     * @param user пользователь (entity)
     * @return возвращает ответ true если в базе успешно изменили пользователя
     */
    @Override
    public boolean update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String nick = user.getNickName();
        User userDB = session.createQuery(
                        "SELECT u FROM User u WHERE nickName =:nickname", User.class)
                .setParameter("nickname", nick).getSingleResult();
        //TODO додумать еще с изменением пароля
        userDB.setFirstName(user.getFirstName());
        userDB.setAge(user.getAge());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        session.getTransaction().commit();
        session.close();
        return true;
    }

}

//
//@Component
//public class PersonDAO {
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person personToBeUpdated = session.get(Person.class, id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, id));
//    }
//}
