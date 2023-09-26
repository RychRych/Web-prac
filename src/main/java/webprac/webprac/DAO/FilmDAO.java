package webprac.webprac.DAO;

import webprac.webprac.models.Film;

import java.util.List;

public interface FilmDAO extends GenericDAO<Film>{
    List<Film> getByTitle(String name);

    List<Film> getByYear(Integer year);
}
