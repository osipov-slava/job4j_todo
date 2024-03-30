package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class HbnUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    /**
     * Create new user in database.
     *
     * @param user user.
     * @return user with id.
     */
    @Override
    public User create(User user) {
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    /**
     * List of users ordered by id.
     *
     * @return user's list.
     */
    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("FROM User ORDER BY id ASC", User.class);
    }

    /**
     * Find user by ID.
     *
     * @return user.
     */
    @Override
    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "FROM User WHERE id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * List of users where login LIKE %key%
     *
     * @param key key.
     * @return user's list.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "FROM User WHERE login LIKE :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Find user by login.
     *
     * @param login login.
     * @return Optional of user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "FROM User WHERE login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }

    /**
     * Find user by email and password.
     *
     * @param email    email.
     * @param password password.
     * @return Optional of user.
     */
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return crudRepository.optional(
                "FROM User WHERE email = :fEmail AND password = :fPassword", User.class,
                Map.of("fEmail", email,
                        "fPassword", password)
        );
    }

    /**
     * Update user in database.
     *
     * @param user user.
     */
    @Override
    public User update(User user) {
        crudRepository.run(session -> session.merge(user));
        return user;
    }

    /**
     * Delete user from database by id.
     *
     * @param userId ID.
     * @return is updated.
     */
    @Override
    public boolean delete(int userId) {
        try {
            var result = crudRepository.run(
                    "DELETE FROM User WHERE id = :fId",
                    Map.of("fId", userId)
            );
            return result > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
