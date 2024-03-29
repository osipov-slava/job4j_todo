package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnTaskRepository implements TaskRepository {

    private final SessionFactory sf;

    @Override
    public int create(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.getTransaction().commit();
            return (int) session.save(task);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public boolean update(int id, Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("UPDATE Task SET title = :t, description = :d, done = :done WHERE id = :id");
            query.setParameter("t", task.getTitle())
                    .setParameter("d", task.getDescription())
                    .setParameter("done", task.isDone())
                    .setParameter("id", id);
            var result = query.executeUpdate() > 0;
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean done(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("UPDATE Task SET done = :done WHERE id = :id");
            query.setParameter("id", id)
                    .setParameter("done", true);
            var result = query.executeUpdate() > 0;
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("DELETE Task WHERE id = :id");
            query.setParameter("id", id);
            var result = query.executeUpdate() > 0;
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task WHERE id=:id");
            query.setParameter("id", id);
            session.getTransaction().commit();
            return query.uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Task> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task ORDER BY id");
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Task> findFinished() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task WHERE done = true");
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Task> findInProgress() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var query = session.createQuery("FROM Task WHERE done = false");
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }
}