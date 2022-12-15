package com.github.prmiguel.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MultiWebDriver implements WebDriver {

    private WebDriverPool wdp;

    public MultiWebDriver(WebDriverPool wdp) {
        this.wdp = wdp;
    }

    @Override
    public void get(String url) {
        wdp.getInstances().parallel().forEach(wd -> wd.getValue().get(url));
    }

    @Override
    public String getCurrentUrl() {
        List<String> urls = wdp.getInstances().parallel().map(wd -> wd.getValue().getCurrentUrl()).collect(Collectors.toList());
        if(urls.stream().allMatch(url -> url.equalsIgnoreCase(urls.get(0)))) return urls.stream().findAny().get();
        throw new RuntimeException("asd");
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        Map<String, WebElement> elements = wdp.getInstances().parallel()
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().findElement(by)));

        return new MultiWebElement(elements);
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {
        wdp.getInstances().parallel().forEach(wd -> wd.getValue().close());
    }

    @Override
    public void quit() {
        wdp.getInstances().parallel().forEach(wd -> wd.getValue().quit());
    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }
}
