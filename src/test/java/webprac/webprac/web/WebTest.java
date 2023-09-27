package webprac.webprac.web;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import webprac.webprac.DAO.ClientDAO;
import webprac.webprac.DAO.FilmDAO;
import webprac.webprac.DAO.TransactionDAO;
import webprac.webprac.models.Client;
import webprac.webprac.models.Film;
import webprac.webprac.models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class WebTest {
    protected FirefoxDriver driver;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private FilmDAO filmDAO;

    @Autowired
    private TransactionDAO transactionDAO;
    @Test
    void MainPage() {
        driver = new FirefoxDriver();
        this.driver.get("localhost:8080/");
        assertEquals("","Welcome", driver.getTitle());
        this.driver.quit();
    }
    @Test
    void rentFilm() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/");
        driver.findElement(By.linkText("Список клиентов")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).sendKeys("Демьянов");
        driver.findElement(By.xpath("//input[@type='submit' and @value='Поиск']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr/td/a"));
        assertEquals("", 1, elements.size());
        assertEquals("","Демьянов Степан Степанович", elements.get(0).getText());
        elements.get(0).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Выдать фильм")).click();
        driver.findElement(By.name("filmName")).click();
        driver.findElement(By.name("filmName")).sendKeys("Побег из Шоушенка");
        driver.findElement(By.name("from")).click();
        driver.findElement(By.name("from")).sendKeys("2023-09-01");
        driver.findElement(By.name("til")).click();
        driver.findElement(By.name("til")).sendKeys("2023-09-08");
        Select s = new Select(driver.findElement(By.name("media")));
        s.selectByValue("cassette");
        driver.findElement(By.name("Выдать")).click();
        driver.quit();
    }
    @Test
    void updateClient() {
        String updateAddress = "Улица Пушкина, 13";
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/");
        driver.findElement(By.linkText("Список клиентов")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).sendKeys("Демьянов");
        driver.findElement(By.xpath("//input[@type='submit' and @value='Поиск']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr/td/a"));
        assertEquals("", 1, elements.size());
        assertEquals("","Демьянов Степан Степанович", elements.get(0).getText());
        elements.get(0).click();
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(updateAddress);
        driver.findElement(By.name("update")).click();
        assertEquals("", driver.findElement(By.name("address")).getAttribute("value"), updateAddress);
        driver.quit();
    }

    @Test
    void updateFilm() {
        Integer updateYear = 2008;
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/");
        driver.findElement(By.linkText("Список фильмов")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys("Побег из Шоушенка");
        driver.findElement(By.xpath("//input[@type='submit' and @value='Поиск']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr/td/a"));
        assertEquals("", 1, elements.size());
        elements.get(0).click();
        driver.findElement(By.name("year")).click();
        driver.findElement(By.name("year")).clear();
        driver.findElement(By.name("year")).sendKeys(String.valueOf(updateYear));
        driver.findElement(By.name("update")).click();
        assertEquals("", driver.findElement(By.name("year")).getAttribute("value"), String.valueOf(updateYear));
        driver.quit();
    }
    @Test
    void addFilm() {
        String title = "Молчание ягнят";
        String director = " Джонатан Демми";
        String company = "Strong Heart Production";
        String year = "1991";
        String priceDisk = "130.0";
        String priceCassette = "165.0";
        String availableCassette = "20";
        String availableCd = "10";
        String allCassette = "30";
        String allCd = "50";

        FirefoxDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/");
        driver.findElement(By.linkText("Список фильмов")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("add")).click();
        driver.findElement(By.name("title")).sendKeys(title);
        driver.findElement(By.name("company")).sendKeys(company);
        driver.findElement(By.name("director")).sendKeys(director);
        driver.findElement(By.name("year")).sendKeys(year);
        driver.findElement(By.name("availableCd")).sendKeys(availableCd);
        driver.findElement(By.name("cdPrice")).sendKeys(priceDisk);
        driver.findElement(By.name("availableCassette")).sendKeys(availableCassette);
        driver.findElement(By.name("cassettePrice")).sendKeys(priceCassette);
        driver.findElement(By.name("cassetteTotal")).sendKeys(allCassette);
        driver.findElement(By.name("cdTotal")).sendKeys(allCd);
        driver.findElement(By.name("update")).click();

        assertEquals("", driver.findElement(By.name("title")).getAttribute("value"), title);
        assertEquals("", driver.findElement(By.name("company")).getAttribute("value"), company);
        assertEquals("", driver.findElement(By.name("director")).getAttribute("value"), director);
        assertEquals("", driver.findElement(By.name("year")).getAttribute("value"), year);
        assertEquals("", driver.findElement(By.name("availableCd")).getAttribute("value"), availableCd);
        assertEquals("", driver.findElement(By.name("cdPrice")).getAttribute("value"), priceDisk);
        assertEquals("", driver.findElement(By.name("availableCassette")).getAttribute("value"), availableCassette);
        assertEquals("", driver.findElement(By.name("cassettePrice")).getAttribute("value"), priceCassette);
        assertEquals("", driver.findElement(By.name("cassetteTotal")).getAttribute("value"), allCassette);
        assertEquals("", driver.findElement(By.name("cdTotal")).getAttribute("value"), allCd);
        driver.quit();
    }
    @Test
    void addClient() {
        String name = "Рычкова Любовь Михайловна";
        String address = "Ленинские горы, 1с48А";
        String phone = "89601899806";

        FirefoxDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/");
        driver.findElement(By.linkText("Список клиентов")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("add")).click();
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("address")).sendKeys(address);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("update")).click();

        assertEquals("", driver.findElement(By.name("name")).getAttribute("value"), name);
        assertEquals("", driver.findElement(By.name("address")).getAttribute("value"), address);
        assertEquals("", driver.findElement(By.name("phone")).getAttribute("value"), phone);
        driver.quit();
    }

    @BeforeClass
    void beforeClass() {
        this.driver = new FirefoxDriver();
        //clients
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1, "Мельникова Эмилия Михайловна", "въезд Бухарестская, 06", "83586074997"));
        clientList.add(new Client(2, "Демьянов Степан Степанович", "бульвар Балканская, 65", "80561319691"));
        clientList.add(new Client(3, "Журавлева Анастасия Николаевна", "шоссе Гоголя, 16", "81818907012"));
        clientList.add(new Client(4, "Демьянов Андрей Станиславосич", "бульвар Балканская, 65", "80576312699"));

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

    @AfterClass
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

