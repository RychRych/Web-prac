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
import webprac.webprac.models.Film;
import webprac.webprac.models.Transaction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class TransactionDAOTest {
    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private FilmDAO filmDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetNotReturnedFilms() {
        List<Transaction> notReturnedFilms = transactionDAO.getNotReturnedFilmsFromClient(2);
        assertEquals(notReturnedFilms.get(0).getFilm_title(), "Зелёная миля");
        assertEquals(notReturnedFilms.get(1).getFilm_title(), "Список Шиндлера");
    }

    @Test
    void testGetByClientIdForSpecifiedDates() {

        List<Transaction> transactionsForPeriod = transactionDAO.getByClientIdForSpecifiedDates(1, null,null);
        assertEquals(transactionsForPeriod.size(), 2);
        transactionsForPeriod = transactionDAO.getByClientIdForSpecifiedDates(1, java.sql.Date.valueOf("2022-01-01"), null);
        assertEquals(transactionsForPeriod.size(), 1);

    }


    @Test
    void testGetByFilmIdForSpecifiedDates() {
        List<Transaction> transactionsForPeriod = transactionDAO.getByFilmIdForSpecifiedDates(1, null,  java.sql.Date.valueOf("2023-12-31"));
        assertEquals(transactionsForPeriod.size(), 1);
        transactionsForPeriod = transactionDAO.getByFilmIdForSpecifiedDates(1, java.sql.Date.valueOf("2022-01-01"), null);
        assertEquals(transactionsForPeriod.size(), 0);
    }

    @BeforeEach
    void beforeEach() {
        //clients
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1, "Мельникова Эмилия Михайловна", "въезд Бухарестская, 06", "83586074997"));
        clientList.add(new Client(2, "Демьянов Степан Степанович", "бульвар Балканская, 65", "80561319691"));
        clientList.add(new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012"));
        clientDAO.saveAll(clientList);

        //films
        List<Film> filmList = new ArrayList<>();
        filmList.add(new Film(1, "Зелёная миля", "Castle Rock Entertainment Darkwoods Productions",
                "Фрэнк Дарабонт", 1999, 15, 230.0, 10, 170.0, 800, 315));
        filmList.add(new Film(2, "Список Шиндлера", "Amblin Entertainment Universal Pictures",
                "Стивен Спилберг", 1993, 20, 140.0, 15, 135.0, 500, 250));
        filmList.add(new Film(3, "Побег из Шоушенка", "Castle Rock Entertainment",
                "Фрэнк Дарабонт",1994, 40, 350.0, 24, 230.0, 1000, 700));
        filmDAO.saveAll(filmList);

        //transactions
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, 2, 3, "Побег из Шоушенка", "cd",
                350.0, java.sql.Date.valueOf("2015-12-17"), java.sql.Date.valueOf("2015-12-27")));

        transactionList.add(new Transaction(2, 2, 1, "Зелёная миля", "cassette",
                350.0, java.sql.Date.valueOf("2019-10-03"), null));

        transactionList.add(new Transaction(3, 2, 2, "Список Шиндлера", "cd",
                350.0, java.sql.Date.valueOf("2022-12-31"), null));

        transactionList.add(new Transaction(4, 1, 2, "Список Шиндлера", "cassette",
                360.0, java.sql.Date.valueOf("2022-10-31"), java.sql.Date.valueOf("2023-09-01")));

        transactionList.add(new Transaction(5, 1, 1, "Зелёная миля", "cassette",
                250.0, java.sql.Date.valueOf("2020-11-11"), java.sql.Date.valueOf("2022-07-14")));
        transactionDAO.saveAll(transactionList);
    }



    @AfterEach
    void afterEach() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE transaction RESTART IDENTITY CASCADE;", int.class).executeUpdate();
            session.createNativeQuery("TRUNCATE client RESTART IDENTITY CASCADE;", int.class).executeUpdate();
            session.createNativeQuery("TRUNCATE film RESTART IDENTITY CASCADE;", int.class).executeUpdate();

            session.getTransaction().commit();
        }
    }
}