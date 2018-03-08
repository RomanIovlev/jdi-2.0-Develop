package com.epam.jdi.uitests.web.selenium.elements.base;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import org.openqa.selenium.*;

public class J extends Element implements WebElement {
    public void click() {
        getWebElement().click();
    }
    public void submit() {
        getWebElement().submit();
    }
    public void sendKeys(CharSequence... charSequences) {
        getWebElement().sendKeys(charSequences);
    }
    public void clear() {
        getWebElement().clear();
    }
    public String getTagName() {
        return getWebElement().getTagName();
    }
    public boolean isSelected() {
        return getWebElement().isSelected();
    }
    public boolean isEnabled() {
        return enabled();
    }
    public String getText() {
        return getWebElement().getText();
    }
    public boolean isDisplayed() {
        return displayed();
    }
    public Point getLocation() {
        return getWebElement().getLocation();
    }
    public Dimension getSize() {
        return getWebElement().getSize();
    }
    public Rectangle getRect() {
        return getWebElement().getRect();
    }
    public String getCssValue(String s) {
        return getWebElement().getCssValue(s);
    }
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return getWebElement().getScreenshotAs(outputType);
    }

    public String getAttribute(String name) {
        return super.getAttribute(name);
    }
}
