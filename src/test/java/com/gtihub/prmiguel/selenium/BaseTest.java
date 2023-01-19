package com.gtihub.prmiguel.selenium;

import com.github.prmiguel.selenium.MultiWebDriver;
import com.github.prmiguel.selenium.ScreenGrid;
import com.github.prmiguel.selenium.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver wd;

    @BeforeMethod
    public void setup() {
        WebDriverPool wdp = new WebDriverPool(3, ScreenGrid._2x2);
        wd = new MultiWebDriver(wdp);
    }

    @AfterMethod
    public void teardown() {
        wd.quit();
    }
}
