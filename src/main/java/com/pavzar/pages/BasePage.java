package main.java.com.pavzar.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.List;

// Abstract BasePage class provides common functionality for interacting with web pages using Selenium and AWT Robot.
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    private final By denyCookiesButtonLocator = By.id("CybotCookiebotDialogBodyButtonDecline");

    private final String playtechPageUrl = "https://www.playtechpeople.com";

    private final int denyButtonCoordinateX = 615;
    private final int denyButtonCoordinateY = 817;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementInDOM(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void scrollToElement(By locator) {
        WebElement element = waitForElementInDOM(locator);
        actions.moveToElement(element).perform();
    }

    // .refreshed method is used to avoid StaleElementReferenceException that happens sometimes during the task.
    protected List<WebElement> waitForElements(By locator) {
        return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        ));
    }

    protected void waitForTextToBePresentInElement(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected void waitForPageCompleteState() {
        wait.until(driver -> "complete".equals(((JavascriptExecutor) driver)
                .executeScript("return document.readyState")));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void visitUsingLocator() {
        driver.get(playtechPageUrl);
        denyAnnoyingCookiesUsingLocator();
    }

    protected void visitUsingCoordinates() {
        driver.get(playtechPageUrl);
        denyAnnoyingCookiesUsingCoordinates();
    }

    protected void denyAnnoyingCookiesUsingCoordinates() {
        waitForElement(denyCookiesButtonLocator);
        clickUsingCoordinates(denyButtonCoordinateX, denyButtonCoordinateY);
    }

    protected void denyAnnoyingCookiesUsingLocator() {
        WebElement denyCookiesButton = waitForElement(denyCookiesButtonLocator);
        denyCookiesButton.click();
    }

    protected void clickUsingLocator(By locator) {
        WebElement element = waitForElement(locator);
        element.click();
    }

    protected void clickClickableElementUsingLocator(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    protected void clickUsingCoordinates(int x, int y) {
        try {
            Robot robot = new Robot();

            robot.mouseMove(x, y);
            robot.delay(300);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    protected void moveToElement(By locator) {
        WebElement element = waitForElement(locator);
        actions.moveToElement(element).perform();
    }
}
