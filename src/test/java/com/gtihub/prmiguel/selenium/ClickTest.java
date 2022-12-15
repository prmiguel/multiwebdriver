package com.gtihub.prmiguel.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClickTest extends BaseTest {

    @Test
    public void click_onUncheckedCheckbox_checkboxChecked() {
        wd.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement we = wd.findElement(By.cssSelector("input[type='checkbox']:nth-of-type(1)"));
        we.click();
        Assert.assertEquals(we.isSelected(), true);
    }

    @Test
    public void click_onCheckedCheckbox_checkboxUnchecked() {
        wd.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement we = wd.findElement(By.cssSelector("input[type='checkbox']:nth-of-type(2)"));
        we.click();
        Assert.assertEquals(we.isSelected(), false);
    }
}
