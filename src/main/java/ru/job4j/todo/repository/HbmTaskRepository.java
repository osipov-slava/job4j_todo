package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {

    private final SessionFactory sf;

    @Override
    public int create(Task task) {
        Session session = sf.openSession();
        int result = 0;
        try {
            session.beginTransaction();
            result = (int) session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean update(int id, Task task) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET title = :t description = :d WHERE id = :id")
                    .setParameter("title", task.getTitle())
                    .setParameter("description", task.getDescription())
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Task WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Optional<Task> result = Optional.empty();
        try {
            session.beginTransaction();
            var query = session.createQuery("from Task where id=:id", Task.class);
            query.setParameter("id", id);
            result = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Collection<Task> findAll() {
        Session session = sf.openSession();
        List<Task> userList = Collections.emptyList();
        try {
            session.beginTransaction();
            var query = session.createQuery("from Task");
            userList = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userList;
    }
}