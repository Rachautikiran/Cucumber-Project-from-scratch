package utils;

import factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String captureScreenshot(String testName) {

        // Take screenshot and store temporarily in sourceFile
        File sourceFile = ((TakesScreenshot)
                DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);

        // Create destination path
        String destinationPath =
                System.getProperty("user.dir")
                        + "/screenshots/"
                        + testName
                        + ".png";

        // Create destination file object
        File destinationFile =
                new File(destinationPath);

        try {

            // Copy screenshot from source to destination
            FileUtils.copyFile(
                    sourceFile,
                    destinationFile
            );

        } catch (IOException e) {

            e.printStackTrace();
        }

        // Return screenshot path
        return destinationPath;
    }
}