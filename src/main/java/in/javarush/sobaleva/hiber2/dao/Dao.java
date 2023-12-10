package in.javarush.sobaleva.hiber2.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, PK extends Serializable> {

    T save(T entity);

    T update(T entity);

    boolean delete(T entity);

    T getById(PK id);

    List<T> getAll();
}