package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnUserRepository implements UserRepository {

    private final SessionFactory sf;

    @Override
    public Optional<User> save(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM User WHERE (email = :email AND password = :password)")
                    .setParameter("email", email)
                    .setParameter("password", password);
            session.getTransaction().commit();
            return query.uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    public Collection<User> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM User");
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    public boolean deleteById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("DELETE User WHERE id = :id")
                    .setParameter("id", id);
            session.getTransaction().commit();
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
