package com.github.prmiguel.selenium;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiWebElement implements WebElement {

    private final Map<String, WebElement> elements;

    MultiWebElement(Map<String, WebElement> elements) {
        this.elements = elements;
    }

    @Override
    public void click() {
        elements.entrySet().stream().parallel()
                .forEach(e -> e.getValue().click());
    }

    @Override
    public void submit() {
        elements.entrySet().stream().parallel()
                .forEach(e -> e.getValue().submit());
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        elements.entrySet().stream().parallel()
                .forEach(e -> e.getValue().sendKeys(keysToSend));
    }

    @Override
    public void clear() {
        elements.entrySet().stream().parallel()
                .forEach(e -> e.getValue());
    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        List<String> texts = elements.entrySet().stream().parallel()
                .map(e -> e.getValue().getAttribute(name))
                .collect(Collectors.toList());
        if (texts.stream().allMatch(e -> e.equalsIgnoreCase(texts.stream().findAny().get()))) {
            return texts.stream().findAny().get();
        }
        return String.join(",", texts);
    }

    @Override
    public boolean isSelected() {
        List<Boolean> texts = elements.entrySet().stream().parallel()
                .map(e -> e.getValue().isSelected())
                .collect(Collectors.toList());
        if (texts.stream().allMatch(e -> e.equals(texts.stream().findAny().get()))) {
            return texts.stream().findAny().get();
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        List<String> texts = elements.entrySet().stream().parallel()
                .map(e -> e.getValue().getText())
                .collect(Collectors.toList());
        if (texts.stream().allMatch(e -> e.equalsIgnoreCase(texts.stream().findAny().get()))) {
            return texts.stream().findAny().get();
        }
        return String.join(",", texts);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        Map<String, WebElement> el = elements.entrySet().stream().parallel()
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().findElement(by)));

        return new MultiWebElement(el);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
