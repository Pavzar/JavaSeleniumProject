package main.java.com.pavzar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * LifeAtPlaytechPage class extends BasePage and encapsulates interactions with the Life at Playtech page,
 * including retrieving of casino suite description text and navigating to the Jobs page.
 */
public class LifeAtPlaytechPage extends BasePage {

    private final String allJobsButtonCSS = "a.yellow-button[href='https://www.playtechpeople.com/jobs-our/']";
    private final String casinoUnitLinkCSS = "section#component-4 .product-cards a.product-card__link[href='https://www.playtech.com/products/casino/']";
    private final String casinoUnitDescriptionCSS = "section#component-4 .product-cards .product-card:first-of-type p";
    private final String searchButtonCSS = ".blue-button";

    private final int allJobsButtonCoordinateX = 1524;
    private final int allJobsButtonCoordinateY = 232;

    public LifeAtPlaytechPage(WebDriver driver) {
        super(driver);
    }

    private void waitForJobsPageToLoad() {
        waitForPageCompleteState();
        waitForElementToBeClickable(By.cssSelector(searchButtonCSS));
    }

    public JobsPage openAllJobsPageUsingLocator() {
        clickUsingLocator(By.cssSelector(allJobsButtonCSS));
        waitForJobsPageToLoad();
        return new JobsPage(driver);
    }

    public JobsPage openAllJobsPageUsingCoordinates() {
        clickUsingCoordinates(allJobsButtonCoordinateX, allJobsButtonCoordinateY);
        waitForJobsPageToLoad();
        return new JobsPage(driver);
    }

    private void scrollToCasinoUnit() {
        scrollToElement(By.cssSelector(casinoUnitLinkCSS));
    }

    public String getCasinoDescription() {
        scrollToCasinoUnit();
        WebElement casinoUnitDescription = waitForElement(By.cssSelector(casinoUnitDescriptionCSS));
        return casinoUnitDescription.getText();
    }
}
