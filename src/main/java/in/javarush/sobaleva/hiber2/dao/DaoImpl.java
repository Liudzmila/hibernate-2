package in.javarush.sobaleva.hiber2.dao;

import in.javarush.sobaleva.hiber2.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.util.List;

public abstract class DaoImpl<T, PK extends Serializable> implements Dao<T, PK> {
    private final Class<T> entityClass;

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return MySessionFactory.getSessionFactory().openSession();
    }

    @Override
    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T update(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public T getById(PK id) {
        try (Session session = getSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleTransactionException(Exception e) {
        if (e instanceof NullPointerException || e instanceof IllegalArgumentException) {
            System.err.println("Ошибка: " + e.getMessage());
        } else {
            e.printStackTrace();
        }
    }
}