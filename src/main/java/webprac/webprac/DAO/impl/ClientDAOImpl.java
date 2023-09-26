package webprac.webprac.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.models.Client;
import webprac.webprac.models.Film;

import java.util.List;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {
    public ClientDAOImpl() {
        super(Client.class);
    }

    @Override
    public List<Client> getClientByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            NativeQuery<Client> query = session.createNativeQuery(
                    "SELECT * FROM client WHERE full_name LIKE :tit",
                    Client.class
            ).setParameter("tit", "%" + name + "%");
            query.getResultList();
            return query.list();
        }
    }
}