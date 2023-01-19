package com.github.prmiguel.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WebDriverPool {

    private final Map<String, WebDriver> pool = new HashMap<>();
    private ScreenGridManager sgm;
    private String sessionId;

    public WebDriverPool(int a, ScreenGrid screenGrid) {
        sgm = new ScreenGridManager(screenGrid);
        IntStream.rangeClosed(1, a).forEach(i -> {

//            DesiredCapabilities capability = DesiredCapabilities.chrome();
                    WebDriver wd = null;
//            try {
//                wd = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capability);
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }

                    wd = new ChromeDriver();
                    pool.put(((RemoteWebDriver) wd).getSessionId().toString(), wd);
                }
        );

        organize();
        sessionId = pool.entrySet().stream().findFirst().get().getKey();
    }

    public WebDriverPool(WebDriver... wds) {
        Arrays.stream(wds).parallel().forEach(wd -> pool.put(((RemoteWebDriver) wd).getSessionId().toString(), wd));
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

    public void organize() {
        getInstances().forEach(wd -> {
            wd.getValue().manage().window().setSize(sgm.getDimension());
            wd.getValue().manage().window().setPosition(sgm.getPositions().remove());
        });
    }

    public Stream<Map.Entry<String, WebDriver>> getInstances() {
        return pool.entrySet().stream();
    }
}
