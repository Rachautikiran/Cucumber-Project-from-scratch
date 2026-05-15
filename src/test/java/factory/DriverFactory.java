package factory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");

        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }

        configureCachedChromeDriver();

        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout")))
        );
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Please call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static void configureCachedChromeDriver() {
        if (System.getProperty("webdriver.chrome.driver") != null) {
            return;
        }

        Optional<Path> cachedDriver = findCachedChromeDriver();

        cachedDriver.ifPresent(path -> System.setProperty("webdriver.chrome.driver", path.toString()));
    }

    private static Optional<Path> findCachedChromeDriver() {
        Optional<Path> currentUserDriver = findLatestDriverInRoot(
                Path.of(System.getProperty("user.home"), ".cache", "selenium", "chromedriver", "win64")
        );

        if (currentUserDriver.isPresent()) {
            return currentUserDriver;
        }

        Path usersRoot = Path.of(System.getenv().getOrDefault("SystemDrive", "C:"), "Users");
        if (!Files.exists(usersRoot)) {
            return Optional.empty();
        }

        try (Stream<Path> userHomes = Files.list(usersRoot)) {
            return userHomes
                    .filter(Files::isDirectory)
                    .map(path -> path.resolve(".cache").resolve("selenium").resolve("chromedriver").resolve("win64"))
                    .map(DriverFactory::findLatestDriverInRoot)
                    .flatMap(Optional::stream)
                    .max(Comparator.comparing(Path::toString));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    private static Optional<Path> findLatestDriverInRoot(Path cacheRoot) {
        if (!Files.exists(cacheRoot)) {
            return Optional.empty();
        }

        try (Stream<Path> candidates = Files.list(cacheRoot)) {
            return candidates
                    .filter(Files::isDirectory)
                    .map(path -> path.resolve("chromedriver.exe"))
                    .filter(Files::exists)
                    .max(Comparator.comparing(Path::toString));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
