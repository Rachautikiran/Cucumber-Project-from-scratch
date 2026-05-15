package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

import javax.swing.*;

public class Apphooks {

    @Before
    public void setUp(){
        DriverFactory.initDriver();

        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
