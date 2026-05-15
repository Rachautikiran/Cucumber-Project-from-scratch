package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.TimeoutException;
import utils.ConfigReader;

public class Apphooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        openApplication();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    private void openApplication() {
        String url = ConfigReader.getProperty("url");

        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                DriverFactory.getDriver().get(url);
                return;
            } catch (TimeoutException exception) {
                if (attempt == 2) {
                    throw exception;
                }
            }
        }
    }
}
