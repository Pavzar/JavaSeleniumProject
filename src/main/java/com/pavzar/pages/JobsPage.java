package main.java.com.pavzar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * JobsPage extends BasePage and encapsulates interactions with the Jobs page.
 * Handles filtering jobs by location and retrieving available job links.
 */
public class JobsPage extends BasePage {

    private final String selectLocationCSS = ".column-title.column-title__location";
    private final String estoniaOptionCSS = ".search-column__item.locations-column__item:nth-child(6)";
    private final String searchButtonCSS = ".blue-button";
    private final String visibleJobLinksCSS = "a.job-item:not([style*='display: none'])";

    private final int selectLocationCoordinateX = 860;
    private final int selectLocationCoordinateY = 540;
    private final int estoniaOptionCoordinateX = 820;
    private final int estoniaOptionCoordinateY = 800;
    private final int searchButtonCoordinateX = 1557;
    private final int searchButtonCoordinateY = 534;

    public JobsPage(WebDriver driver) {
        super(driver);
    }

    private void clickLocationDropdownUsingLocator() {
        clickUsingLocator(By.cssSelector(selectLocationCSS));
    }

    private void clickEstoniaOptionUsingLocator() {
        clickUsingLocator(By.cssSelector(estoniaOptionCSS));
    }

    private void clickSearchButtonUsingLocator() {
        clickUsingLocator(By.cssSelector(searchButtonCSS));
    }

    private void clickLocationDropdownUsingCoordinates() {
        waitForElementToBeClickable(By.cssSelector(selectLocationCSS));
        clickUsingCoordinates(selectLocationCoordinateX, selectLocationCoordinateY);
    }

    private void clickEstoniaOptionUsingCoordinates() {
        waitForElementToBeClickable(By.cssSelector(estoniaOptionCSS));
        clickUsingCoordinates(estoniaOptionCoordinateX, estoniaOptionCoordinateY);
    }

    private void clickSearchButtonUsingCoordinates() {
        waitForElementToBeClickable(By.cssSelector(searchButtonCSS));
        clickUsingCoordinates(searchButtonCoordinateX, searchButtonCoordinateY);
    }


    private void searchJobsInEstoniaUsingCoordinates() {
        clickLocationDropdownUsingCoordinates();
        clickEstoniaOptionUsingCoordinates();
        clickSearchButtonUsingCoordinates();
        waitForPageCompleteState();
    }

    private void searchJobsInEstoniaUsingLocator() {
        clickLocationDropdownUsingLocator();
        clickEstoniaOptionUsingLocator();
        clickSearchButtonUsingLocator();
        waitForPageCompleteState();
    }

    private List<String> getJobLinks() {
        List<String> availableLinks = new ArrayList<>();
        List<WebElement> visibleJobLinks = waitForElements(By.cssSelector(visibleJobLinksCSS));

        for (WebElement link : visibleJobLinks) {
            availableLinks.add(link.getDomAttribute("href"));
        }

        return availableLinks;
    }

    public List<String> getEstonianJobLinksUsingLocator() {
        searchJobsInEstoniaUsingLocator();
        return getJobLinks();
    }

    public List<String> getEstonianJobLinksUsingCoordinates() {
        searchJobsInEstoniaUsingCoordinates();
        return getJobLinks();
    }
}