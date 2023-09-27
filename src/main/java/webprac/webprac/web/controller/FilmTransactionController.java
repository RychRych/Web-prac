package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.DAO.FilmDAO;
import webprac.webprac.DAO.TransactionDAO;
import webprac.webprac.models.Client;
import webprac.webprac.models.Film;
import webprac.webprac.models.Transaction;

import java.util.List;

@Controller
public class FilmTransactionController {
    private final TransactionDAO transactionDAO;
    private final FilmDAO filmDAO;
    private final ClientDAO clientDAO;

    public FilmTransactionController(TransactionDAO transactionDAO, FilmDAO filmDAO, ClientDAO clientDAO) {
        this.transactionDAO = transactionDAO;
        this.filmDAO = filmDAO;
        this.clientDAO = clientDAO;
    }

    @GetMapping(value = "/transaction")
    public String transaction(
            @RequestParam(value = "clientId") Integer id,
            Model model
    ) {
        Client client = clientDAO.getById(id);
        model.addAttribute("error", false);
        model.addAttribute("clientId", id);
        model.addAttribute("clientName", client.getClientName());
        return "transaction";
    }

    @PostMapping(value = "/transaction")
    public String transaction(
            @RequestParam(value = "clientId") Integer id,
            @RequestParam(value = "filmName") String name,
            @RequestParam(value = "from") String date, // Note: dates expected in yyyy-mm-dd format
            @RequestParam(value = "til") String til,
            @RequestParam(value = "media") String media,
            Model model
    ) {
        try {
            List<Film> byTitle = filmDAO.getByTitle(name);
            Film film = byTitle.get(0);
            Transaction transaction = new Transaction();
            transaction.setClient_id(id);
            transaction.setFilm_id(film.getId());
            transaction.setFilm_title(film.getTitle());
            transaction.setRent_price(film.getPrice_of_cd());
            transaction.setType_of_carrier(media);
            transaction.setDate_of_lease(java.sql.Date.valueOf(date));
            transaction.setDate_of_return(java.sql.Date.valueOf(til));

            transactionDAO.save(transaction);
            return new ClientController(clientDAO, transactionDAO).client(id, model);
        } catch (Exception ignored) {
            Client client = clientDAO.getById(id);

            model.addAttribute("error", true);
            model.addAttribute("clientId", id);
            model.addAttribute("clientName", client.getClientName());

            return "transaction";
        }
    }
}
