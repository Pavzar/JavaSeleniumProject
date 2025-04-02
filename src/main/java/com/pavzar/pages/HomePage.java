package main.java.com.pavzar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * HomePage class extends BasePage and encapsulates interactions with the homepage.
 * Handles retrieval of location data and navigation to the Life at Playtech page.
 */
public class HomePage extends BasePage {

    private final String locationCountriesCSS = ".location-wrap__item--bottom .item-title";
    private final String aboutUsTextCSS = ".banner-uppertitle";

    private final String aboutUsText = "ABOUT US";
    private final String locationsLinkText = "Locations";
    private final String lifeAtPlaytechLinkText = "Life at Playtech";
    private final String whoWeAreLinkText = "Who we are";

    private final int locationsTabCoordinateX = 743;
    private final int locationsTabCoordinateY = 232;
    private final int lifeAtPlaytechTabCoordinateX = 855;
    private final int lifeAtPlaytechTabCoordinateY = 232;
    private final int whoWeAreTabCoordinateX = 855;
    private final int whoWeAreTabCoordinateY = 320;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePageUsingLocator() {
        super.visitUsingLocator();
    }

    public void openHomePageUsingCoordinates() {
        super.visitUsingCoordinates();
    }

    private void moveToLocationsTab() {
        moveToElement(By.linkText(locationsLinkText));
    }

    private void moveToLifeAtPlaytechTab() {
        moveToElement(By.linkText(lifeAtPlaytechLinkText));
    }

    private void clickLocationsUsingCoordinates() {
        waitForElementToBeClickable(By.linkText(locationsLinkText));
        clickUsingCoordinates(locationsTabCoordinateX, locationsTabCoordinateY);
    }

    private void clickWhoWeAreOptionUsingLocator() {
        clickClickableElementUsingLocator(By.linkText(whoWeAreLinkText));
    }

    private void clickWhoWeAreOptionUsingCoordinates() {
        waitForElementToBeClickable(By.linkText(whoWeAreLinkText));
        clickUsingCoordinates(lifeAtPlaytechTabCoordinateX, lifeAtPlaytechTabCoordinateY);
        clickUsingCoordinates(whoWeAreTabCoordinateX, whoWeAreTabCoordinateY);
    }

    private void waitForLifeAtPlaytechTabToLoad() {
        waitForPageCompleteState();
        waitForTextToBePresentInElement(By.cssSelector(aboutUsTextCSS), aboutUsText);
    }

    public LifeAtPlaytechPage openLifeAtPlaytechPageUsingLocator() {
        moveToLifeAtPlaytechTab();
        clickWhoWeAreOptionUsingLocator();
        waitForLifeAtPlaytechTabToLoad();
        return new LifeAtPlaytechPage(driver);
    }

    public LifeAtPlaytechPage openLifeAtPlaytechPageUsingCoordinates() {
        clickWhoWeAreOptionUsingCoordinates();
        waitForLifeAtPlaytechTabToLoad();
        return new LifeAtPlaytechPage(driver);
    }

    private List<String> getLocations() {
        List<String> locations = new ArrayList<>();
        List<WebElement> AllCountries = waitForElements(By.cssSelector(locationCountriesCSS));

        for (WebElement country : AllCountries) {
            locations.add(country.getText());
        }

        return locations;
    }

    public List<String> getLocationsUsingLocator() {
        moveToLocationsTab();
        return getLocations();
    }

    public List<String> getLocationsUsingCoordinates() {
        clickLocationsUsingCoordinates();
        return getLocations();
    }
}
