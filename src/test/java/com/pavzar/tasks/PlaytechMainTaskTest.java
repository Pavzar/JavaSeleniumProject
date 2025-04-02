package test.java.com.pavzar.tasks;

import main.java.com.pavzar.pages.HomePage;
import main.java.com.pavzar.pages.JobsPage;
import main.java.com.pavzar.pages.LifeAtPlaytechPage;
import org.junit.Test;
import java.util.List;

public class PlaytechMainTaskTest extends BaseTest {

    // Executes the task using locator-based interactions and exports result to txt.
    @Test
    public void mainTaskUsingLocator() {
        HomePage homePage = new HomePage(driver);

        homePage.openHomePageUsingLocator();
        List<String> locations = homePage.getLocationsUsingLocator();

        testResults.append("Found ")
                .append(locations.size())
                .append(" locations: ")
                .append(String.join(", ", locations))
                .append(".")
                .append("\n");
        System.out.printf("Locations: %s.%n", String.join(", ", locations));

        LifeAtPlaytechPage lifeAtPlaytechPage = homePage.openLifeAtPlaytechPageUsingLocator();
        String casinoDescription = lifeAtPlaytechPage.getCasinoDescription();

        testResults.append("Description text of the Casino unit: ")
                .append(casinoDescription)
                .append("\n");
        System.out.printf("Description text of the Casino unit: %s %n", casinoDescription);

        JobsPage jobsPage = lifeAtPlaytechPage.openAllJobsPageUsingLocator();
        List<String> availableLinks = jobsPage.getEstonianJobLinksUsingLocator();
        testResults.append("Available links: ")
                .append(String.join(", ", availableLinks))
                .append("\n");
        System.out.printf("Available links: %s.%n", String.join(", ", availableLinks));
    }

    // Executes the task using coordinate-based interactions and exports result to txt.
    @Test
    public void mainTaskUsingCoordinates() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePageUsingCoordinates();
        List<String> locations = homePage.getLocationsUsingCoordinates();

        testResults.append("Found ")
                .append(locations.size())
                .append(" locations: ")
                .append(String.join(", ", locations))
                .append(".")
                .append("\n");
        System.out.printf("Locations: %s.%n", String.join(", ", locations));

        LifeAtPlaytechPage lifeAtPlaytechPage = homePage.openLifeAtPlaytechPageUsingCoordinates();
        String casinoDescription = lifeAtPlaytechPage.getCasinoDescription();
        testResults.append("Description text of the Casino unit: ")
                .append(casinoDescription)
                .append("\n");
        System.out.printf("Description text of the Casino unit: %s %n", casinoDescription);

        JobsPage jobsPage = lifeAtPlaytechPage.openAllJobsPageUsingCoordinates();
        List<String> availableLinks = jobsPage.getEstonianJobLinksUsingCoordinates();
        testResults.append("Available links: ")
                .append(String.join(", ", availableLinks))
                .append("\n");
        System.out.printf("Available links: %s.%n", String.join(", ", availableLinks));
    }
}