package webprac.webprac.DAO.impl;

import org.springframework.stereotype.Repository;
import webprac.webprac.DAO.FilmDAO;
import webprac.webprac.models.Film;



@Repository
public class FilmDAOImpl extends GenericDAOImpl<Film> implements FilmDAO {
    public FilmDAOImpl() {
        super(Film.class);
    }

}