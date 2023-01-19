package com.github.prmiguel;

import com.github.prmiguel.selenium.MultiWebDriver;
import com.github.prmiguel.selenium.ScreenGrid;
import com.github.prmiguel.selenium.WebDriverPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Main {
    public static void main(String[] args) {
        WebDriverPool wdp = new WebDriverPool(3, ScreenGrid._2x2);
        WebDriver mwd = new MultiWebDriver(wdp);
        mwd.get("http://www.google.com");
        System.out.println(mwd.getCurrentUrl());
        mwd.findElement(By.cssSelector("input[name='q']")).sendKeys("Testing");
        mwd.findElement(By.cssSelector("input[name='q']")).sendKeys(Keys.ENTER);
        mwd.quit();
    }
}