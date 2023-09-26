package webprac.webprac.DAO;

import webprac.webprac.models.Client;

import java.util.List;

public interface ClientDAO extends GenericDAO<Client> {
    List<Client> getClientByName(String name);
}