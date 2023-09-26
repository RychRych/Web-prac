package webprac.webprac.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import webprac.webprac.DAO.FilmDAO;
import webprac.webprac.models.Film;
import webprac.webprac.models.Transaction;

import java.util.List;


@Repository
public class FilmDAOImpl extends GenericDAOImpl<Film> implements FilmDAO {
    public FilmDAOImpl() {
        super(Film.class);
    }

    @Override
    public List<Film> getByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            NativeQuery<Film> query = session.createNativeQuery(
                    "SELECT * FROM film WHERE title LIKE :tit",
                    Film.class
            ).setParameter("tit", "%" + title + "%");
            query.getResultList();
            return query.list();
        }
    }

    @Override
    public List<Film> getByYear(Integer year) {
        try (Session session = sessionFactory.openSession()) {
            NativeQuery<Film> query = session.createNativeQuery(
                    "SELECT * FROM film WHERE year_of_release = :year",
                    Film.class
            ).setParameter("year", year);
            query.getResultList();
            return query.list();
        }
    }
}