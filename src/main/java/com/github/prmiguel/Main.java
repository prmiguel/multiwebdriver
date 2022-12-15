package com.github.prmiguel;

import com.github.prmiguel.selenium.MultiWebDriver;
import com.github.prmiguel.selenium.WebDriverPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        WebDriverPool a = new WebDriverPool(3);
        WebDriver b = new MultiWebDriver(a);
        b.get("http://www.google.com");
        System.out.println(b.getCurrentUrl());
        b.findElement(By.cssSelector("input[name='q']")).sendKeys("Testing");
        b.findElement(By.cssSelector("input[name='q']")).sendKeys(Keys.ENTER);
        b.quit();
    }
}