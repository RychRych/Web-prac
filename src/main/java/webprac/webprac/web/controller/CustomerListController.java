package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.models.Client;

import java.util.List;

@Controller
public class CustomerListController {
    private final ClientDAO clientDAO;

    public CustomerListController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping(value = "/clients")
    public String clients(
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        List<Client> clients;
        if (name != null && !name.isEmpty()) {
            clients = clientDAO.getClientByName(name);
        } else {
            clients = clientDAO.getAll().stream().toList();
        }

        model.addAttribute("clients", clients);
        return "clients";
    }
}
