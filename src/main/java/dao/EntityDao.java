package dao;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    Optional<T> findByName(String name);

    void add(T t);

    void update(T t);

    void delete(T t);
}
