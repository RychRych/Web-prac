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
import webprac.webprac.models.Film;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class FilmDAOTest {

    @Autowired
    private FilmDAO filmDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetAll() {
        List<Film> filmListAll = (List<Film>)filmDAO.getAll();
        List<Film> assertListAll = new ArrayList<>();
        assertListAll.add(new Film(1, "Зелёная миля", "Castle Rock Entertainment Darkwoods Productions",
                "Фрэнк Дарабонт", 1999, 15, 230.0, 10, 170.0, 800, 315));
        assertListAll.add(new Film(2, "Список Шиндлера", "Amblin Entertainment Universal Pictures",
                "Стивен Спилберг", 1993, 20, 140.0, 15, 135.0, 500, 250));
        assertListAll.add(new Film(3, "Побег из Шоушенка", "Castle Rock Entertainment",
                "Фрэнк Дарабонт",1994, 40, 350.0, 24, 230.0, 1000, 700));
        assertEquals(filmListAll, assertListAll);

    }
    @Test
    void testAddFilm() {
        Film addFilm = new Film(4, "Форрест Гамп", "The Tisch Company", "Роберт Земекис", 1994, 54, 195.0, 23, 150.0, 199,200);
        filmDAO.save(addFilm);
        List<Film> filmList = (List<Film>)filmDAO.getAll();

        List<Film> assertList = new ArrayList<>();
        assertList.add(new Film(1, "Зелёная миля", "Castle Rock Entertainment Darkwoods Productions",
                "Фрэнк Дарабонт", 1999, 15, 230.0, 10, 170.0, 800, 315));
        assertList.add(new Film(2, "Список Шиндлера", "Amblin Entertainment Universal Pictures",
                "Стивен Спилберг", 1993, 20, 140.0, 15, 135.0, 500, 250));
        assertList.add(new Film(3, "Побег из Шоушенка", "Castle Rock Entertainment",
                "Фрэнк Дарабонт",1994, 40, 350.0, 24, 230.0, 1000, 700));
        assertList.add(new Film(4, "Форрест Гамп", "The Tisch Company", "Роберт Земекис", 1994, 54, 195.0, 23, 150.0, 199,200));
        assertEquals(filmList, assertList);
    }

    @Test
    void testDeleteFilm() {
        Film deleteFilm = new Film(3, "Побег из Шоушенка", "Castle Rock Entertainment",
                "Фрэнк Дарабонт",1994, 40, 350.0, 24, 230.0, 1000, 700);
        filmDAO.delete(deleteFilm);
        List<Film> filmList = (List<Film>)filmDAO.getAll();

        List<Film> assertList = new ArrayList<>();
        assertList.add(new Film(1, "Зелёная миля", "Castle Rock Entertainment Darkwoods Productions",
                "Фрэнк Дарабонт", 1999, 15, 230.0, 10, 170.0, 800, 315));
        assertList.add(new Film(2, "Список Шиндлера", "Amblin Entertainment Universal Pictures",
                "Стивен Спилберг", 1993, 20, 140.0, 15, 135.0, 500, 250));

        assertEquals(filmList, assertList);
    }

    @Test
    void testGetFilmById() {
        Integer validId = 3;
        Integer invalidId = 6;
        Film assertFilm = new Film(3, "Побег из Шоушенка", "Castle Rock Entertainment",
                "Фрэнк Дарабонт",1994, 40, 350.0, 24, 230.0, 1000, 700);
        assertEquals(filmDAO.getById(validId), assertFilm);
        assertNull(filmDAO.getById(invalidId));
    }

    @Test
    void testUpdateFilm() {
        String filmDirector = "Грета Гервиг";
        Integer cntAvailableCassette = 34;
        Double filmCdPrice = 135.0;

        Film updateFilm = filmDAO.getById(2);
        updateFilm.setDirector(filmDirector);
        updateFilm.setAvailable_cassette(cntAvailableCassette);
        updateFilm.setPrice_of_cd(filmCdPrice);
        filmDAO.update(updateFilm);

        Film assertFilm = filmDAO.getById(2);
        assertEquals(filmDirector, assertFilm.getDirector());
        assertEquals(cntAvailableCassette, assertFilm.getAvailable_cassette());
        assertEquals(filmCdPrice, assertFilm.getPrice_of_cd());

    }


    @BeforeEach
    void beforeEach() {
        List<Film> filmList = new ArrayList<>();
        filmList.add(new Film(1, "Зелёная миля", "Castle Rock Entertainment Darkwoods Productions",
                "Фрэнк Дарабонт", 1999, 15, 230.0, 10, 170.0, 800, 315));
        filmList.add(new Film(2, "Список Шиндлера", "Amblin Entertainment Universal Pictures",
                "Стивен Спилберг", 1993, 20, 140.0, 15, 135.0, 500, 250));
        filmList.add(new Film(3, "Побег из Шоушенка", "Castle Rock Entertainment",
                "Фрэнк Дарабонт",1994, 40, 350.0, 24, 230.0, 1000, 700));
        filmDAO.saveAll(filmList);
    }


    @AfterEach
    void afterEach() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE film RESTART IDENTITY CASCADE;", int.class).executeUpdate();
            session.getTransaction().commit();
        }
    }
}