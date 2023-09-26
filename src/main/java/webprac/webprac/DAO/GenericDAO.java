package webprac.webprac.DAO;

import java.util.Collection;

public interface GenericDAO<T> {
    T getById(Integer id);

    Collection<T> getAll();

    void save(T entity);

    void saveAll(Collection<T> entities);

    void delete(T entity);

    void deleteById(Integer id);

    void update(T entity);
}
