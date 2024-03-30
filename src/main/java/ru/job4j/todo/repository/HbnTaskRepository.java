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
public class HbnTaskRepository implements TaskRepository {

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
        int result = 0;
        try {
            session.beginTransaction();
            var query = session.createQuery("UPDATE Task SET title = :t, description = :d, done = :done WHERE id = :id");
            query.setParameter("t", task.getTitle())
                    .setParameter("d", task.getDescription())
                    .setParameter("done", task.isDone())
                    .setParameter("id", id);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return (result > 0);
    }

    @Override
    public boolean done(int id) {
        Session session = sf.openSession();
        int result = 0;
        try {
            session.beginTransaction();
            var query = session.createQuery("UPDATE Task SET done = :done WHERE id = :id");
            query.setParameter("id", id)
                    .setParameter("done", true);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return (result > 0);
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        int result = 0;
        try {
            session.beginTransaction();
            var query = session.createQuery("DELETE Task WHERE id = :id");
            query.setParameter("id", id);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return (result > 0);
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Optional<Task> result = Optional.empty();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task WHERE id=:id");
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
            var query = session.createQuery("FROM Task ORDER BY id");
            userList = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public Collection<Task> findFinished() {
        Session session = sf.openSession();
        List<Task> userList = Collections.emptyList();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task WHERE done = true");
            userList = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public Collection<Task> findInProgress() {
        Session session = sf.openSession();
        List<Task> userList = Collections.emptyList();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task WHERE done = false");
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