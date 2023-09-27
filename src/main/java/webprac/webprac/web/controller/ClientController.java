package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.DAO.TransactionDAO;
import webprac.webprac.models.Client;
import webprac.webprac.models.Transaction;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Controller
public class ClientController {
    private final ClientDAO clientDAO;
    private final TransactionDAO transactionDAO;

    public ClientController(ClientDAO clientDAO, TransactionDAO transactionDAO) {
        this.clientDAO = clientDAO;
        this.transactionDAO = transactionDAO;
    }

    @PostMapping(value = "/client")
    public String client(
        @RequestParam(value = "clientId", required = false) Integer id,
        @RequestParam(value = "name") String name,
        @RequestParam(value = "address") String address,
        @RequestParam(value = "phone") String phone,
        Model model
    ) {
        Client client = new Client(id, name, address, phone);
        client = clientDAO.update(client);

        return client(client.getId(), model);
    }

    @GetMapping(value = "client")
    public String client(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            Client client = new Client();
            model.addAttribute("client", client);
            model.addAttribute("transaction", List.of());
            return "client";
        } else {
            Client client = clientDAO.getById(id);
            model.addAttribute("client", client);

            List<Transaction> transactionList = transactionDAO.getByClientIdForSpecifiedDates(id, null, null);
            model.addAttribute("transactions", transactionList);
        }

        return "client";
    }
}
