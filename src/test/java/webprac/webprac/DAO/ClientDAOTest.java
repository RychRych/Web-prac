package webprac.webprac.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import webprac.webprac.models.Client;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ClientDAOTest {

    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetAll() {
        List<Client> clientListAll = (List<Client>)clientDAO.getAll();
        List<Client> assertListAll = new ArrayList<>();
        assertListAll.add(new Client(1, "Мельникова Эмилия Михайловна", "въезд Бухарестская, 06", "83586074997"));
        assertListAll.add(new Client(2, "Демьянов Степан Степанович", "бульвар Балканская, 65", "80561319691"));
        assertListAll.add(new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012"));
        assertEquals(clientListAll, assertListAll);
    }

    @Test
    void testAddClient() {
        Client addClient = new Client(4, "Амирова Елена Владимировна", "улица Тропининра, 9", "81903458729");
        clientDAO.save(addClient);
        List<Client> clientList = (List<Client>)clientDAO.getAll();

        List<Client> assertList = new ArrayList<>();
        assertList.add(new Client(1, "Мельникова Эмилия Михайловна", "въезд Бухарестская, 06", "83586074997"));
        assertList.add(new Client(2, "Демьянов Степан Степанович", "бульвар Балканская, 65", "80561319691"));
        assertList.add(new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012"));
        assertList.add(new Client(4, "Амирова Елена Владимировна", "улица Тропининра, 9", "81903458729"));

        assertEquals(clientList, assertList);
    }

    @Test
    void testDeleteClient() {
        Client deleteClient = new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012");
        clientDAO.delete(deleteClient);
        List<Client> clientList = (List<Client>)clientDAO.getAll();

        List<Client> assertList = new ArrayList<>();
        assertList.add(new Client(1, "Мельникова Эмилия Михайловна", "въезд Бухарестская, 06", "83586074997"));
        assertList.add(new Client(2, "Демьянов Степан Степанович", "бульвар Балканская, 65", "80561319691"));

        assertEquals(clientList, assertList);
    }

    @Test
    void testGetClientById() {
        Integer validId = 3;
        Integer invalidId = 6;
        Client assertClient = new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012");
        assertEquals(clientDAO.getById(validId), assertClient);
        assertNull(clientDAO.getById(invalidId));
    }

    @Test
    void testUpdateClient() {
        String clientAddress = "улица Щёлковская, 3";
        String clientPhone = "89601899816";
        Client updateClient = clientDAO.getById(1);
        updateClient.setClientAddress(clientAddress);
        updateClient.setClientPhone(clientPhone);
        clientDAO.update(updateClient);

        Client assertClient = clientDAO.getById(1);
        assertEquals(clientAddress, assertClient.getClientAddress());
        assertEquals(clientPhone, assertClient.getClientPhone());
    }

    @BeforeEach
    void beforeEach() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1, "Мельникова Эмилия Михайловна", "въезд Бухарестская, 06", "83586074997"));
        clientList.add(new Client(2, "Демьянов Степан Степанович", "бульвар Балканская, 65", "80561319691"));
        clientList.add(new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012"));
        clientDAO.saveAll(clientList);
    }

    @AfterEach
    void afterEach() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE client RESTART IDENTITY CASCADE;", int.class).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
