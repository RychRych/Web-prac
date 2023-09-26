package webprac.webprac.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.models.Client;

@Controller
public class ClientController {
    private final ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping(value = "client")
    public String client(@RequestParam(value = "id", required = false) Integer id, Model model) {
        return setupModelForClient(clientDAO.getById(id), model);
    }

    private String setupModelForClient(Client client, Model model) {
        model.addAttribute("client", client);

        return "client";
    }
}
