package test.java.com.pavzar.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * BaseTest is an abstract class that sets up and tears down the WebDriver environment for tasks.
 * Handles logging and exporting task results to a .txt file.
 */
public abstract class BaseTest {
    protected WebDriver driver;
    protected StringBuilder testResults;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

        Dimension initialSize = new Dimension(1920, 1080);
        driver.manage().window().setSize(initialSize);

        testResults = new StringBuilder();
        testResults.append("Set resolution: ")
                .append(initialSize.getWidth())
                .append("x")
                .append(initialSize.getHeight())
                .append("\n");

        driver.manage().window().maximize();

        Dimension maximizedSize = driver.manage().window().getSize();

        testResults.append("Maximized resolution: ")
                .append(maximizedSize.getWidth())
                .append("x")
                .append(maximizedSize.getHeight())
                .append("\n");
    }

    // JUnit rule to capture the current test method name for result file naming
    @Rule
    public TestName testName = new TestName();

    @After
    public void tearDown() {
        try {
            Path filePath = Path.of("results_" + testName.getMethodName() + ".txt");
            Files.writeString(
                    filePath,
                    testResults.toString(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            System.out.println("Test results exported to " + filePath.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
