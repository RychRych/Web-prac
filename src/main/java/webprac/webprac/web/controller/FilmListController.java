package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprac.webprac.DAO.FilmDAO;
import webprac.webprac.models.Film;

import java.util.List;

@Controller
public class FilmListController {
    private final FilmDAO filmDAO;

    public FilmListController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping(value = "/films")
    public String films(
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "year", required = false) Integer year,
        Model model
    ) {
        List<Film> films;
        if (title != null) {
            films = filmDAO.getByTitle(title);
        } else if (year != null) {
            films = filmDAO.getByYear(year);
        } else {
            films = filmDAO.getAll().stream().toList();
        }

        model.addAttribute("films", films);
        return "films";
    }
}
