package com.gtihub.prmiguel.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendKeysTest extends BaseTest {

    @Test
    public void sendKeys_onInputElement_enterProvidedText() {
        wd.get("https://the-internet.herokuapp.com/inputs");
        WebElement we = wd.findElement(By.cssSelector("input[type='number']"));
        we.sendKeys("0123456789");
        Assert.assertEquals(we.getAttribute("value"), "0123456789");
    }
}
