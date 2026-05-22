package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class AppHooks {

    // Create single ExtentReports instance
    private static ExtentReports extentReports =
            ExtentReportManager.getExtentReports();

    // Thread-safe ExtentTest object
    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();


    @Before
    public void setup(Scenario scenario) {

        LoggerUtil.logger.info(
                "========== Test Execution Started =========="
        );

        // Launch browser
        DriverFactory.initDriver();

        LoggerUtil.logger.info(
                "Browser launched successfully"
        );

        // Open application URL
        DriverFactory.getDriver().get(
                ConfigReader.getProperty("url")
        );

        LoggerUtil.logger.info(
                "Navigated to application URL"
        );

        // Create Extent test entry
        ExtentTest test =
                extentReports.createTest(
                        scenario.getName()
                );

        // Set ExtentTest object to current thread
        extentTest.set(test);

        LoggerUtil.logger.info(
                "Scenario Started : "
                        + scenario.getName()
        );
    }


    @After
    public void tearDown(Scenario scenario) {

        // Check if scenario failed
        if (scenario.isFailed()) {

            LoggerUtil.logger.error(
                    "Scenario Failed : "
                            + scenario.getName()
            );

            // Capture screenshot
            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            scenario.getName()
                    );

            // Add fail status in report
            extentTest.get().fail(
                    "Test Failed"
            );

            // Attach screenshot to report
            extentTest.get().addScreenCaptureFromPath(
                    screenshotPath
            );

        } else {

            LoggerUtil.logger.info(
                    "Scenario Passed : "
                            + scenario.getName()
            );

            // Add pass status in report
            extentTest.get().pass(
                    "Test Passed"
            );
        }

        // Close browser
        DriverFactory.quitDriver();

        LoggerUtil.logger.info(
                "Browser closed successfully"
        );

        LoggerUtil.logger.info(
                "========== Test Execution Finished =========="
        );

        // Write data into report
        extentReports.flush();
    }
}