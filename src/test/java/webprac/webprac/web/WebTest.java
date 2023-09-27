package webprac.webprac.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class WebTest {

    @Test
    void MainPage() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/");
        assertEquals("","Welcome", driver.getTitle());
        driver.quit();
    }
}
