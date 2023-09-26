package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprac.webprac.DAO.FilmDAO;
import webprac.webprac.DAO.impl.FilmDAOImpl;
import webprac.webprac.models.Film;

@Controller
public class FilmController {
    private final FilmDAO filmDAO;

    public FilmController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping(value = "film")
    public String film(@RequestParam(value = "id", required = false) Integer id, Model model) {
        return setupModelForFilm(filmDAO.getById(id), model);
    }

    private String setupModelForFilm(Film film, Model model) {
        model.addAttribute("film", film);

        return "film";
    }
}
