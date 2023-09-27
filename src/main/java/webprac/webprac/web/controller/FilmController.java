package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/film")
    public String film(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "company") String company,
            @RequestParam(value = "director") String director,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "availableCd") Integer availableCd,
            @RequestParam(value = "cdPrice") Double cdPrice,
            @RequestParam(value = "availableCassette") Integer availableCassette,
            @RequestParam(value = "cassettePrice") Double cassettePrice,
            @RequestParam(value = "cassetteTotal") Integer cassetteTotal,
            @RequestParam(value = "cdTotal") Integer cdTotal,
            Model model
    ) {
        Film film = new Film(id, title, company, director, year, availableCd, cdPrice, availableCassette, cassettePrice, cdTotal, cassetteTotal);
        film = filmDAO.update(film);

        return film(film.getId(), model);
    }

    @GetMapping(value = "/film")
    public String film(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("film", new Film());
        } else {
            model.addAttribute("film", filmDAO.getById(id));
        }

        return "film";
    }
}
