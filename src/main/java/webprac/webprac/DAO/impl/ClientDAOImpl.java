package webprac.webprac.DAO.impl;

import org.springframework.stereotype.Repository;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.models.Client;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {
    public ClientDAOImpl() {
        super(Client.class);
    }
}