package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getExtentReports() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            System.getProperty("user.dir")
                                    + "/reports/extent-report.html"
                    );

            sparkReporter.config().setReportName(
                    "Automation Test Report"
            );

            sparkReporter.config().setDocumentTitle(
                    "Selenium Cucumber Framework"
            );

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo(
                    "Tester",
                    "Kiran"
            );

            extentReports.setSystemInfo(
                    "Environment",
                    "QA"
            );
        }

        return extentReports;
    }
}