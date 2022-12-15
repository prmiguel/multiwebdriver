package com.github.prmiguel.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WebDriverPool {

    private final Map<String, WebDriver> pool = new HashMap<>();
    private String sessionId;

    public WebDriverPool(int a) {
        ScreenGridManager sgm = new ScreenGridManager(2, 2);
        IntStream.rangeClosed(1, a).forEach(i -> {
                    WebDriver wd = new ChromeDriver();
                    wd.manage().window().setSize(sgm.getDimension());
                    wd.manage().window().setPosition(sgm.getPositions().remove());
                    pool.put(((RemoteWebDriver) wd).getSessionId().toString(), wd);
                }
        );

        sessionId = pool.entrySet().stream().findFirst().get().getKey();
    }

    public void getAll() {
        pool.entrySet().parallelStream().forEach(es -> es.getValue().get("https://www.google.com"));
    }

    public void killAll() {
        pool.entrySet().parallelStream().forEach(es -> es.getValue().quit());
    }

    public String getSessionId() {
        return sessionId;
    }

    public void switchSessionId() {

    }
    public Stream<Map.Entry<String, WebDriver>> getInstances() {
        return pool.entrySet().stream();
    }
}
