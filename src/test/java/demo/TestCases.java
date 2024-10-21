package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends Wrappers {
    ChromeDriver driver;

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        setDriver(driver); 
        
    }

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        // Navigate to Google Form
        navigateToURL(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform?pli=1");

        Thread.sleep(3000);
        // Fill in the 'Crio Learner' in the first text box
        enterText(By.xpath("(//input[contains(@class,'zHQkBf')])[1]"), "Crio Learner");
        Thread.sleep(3000);

        // Get the current epoch time and add it to the second textbox message
        String currentEpoch = String.valueOf(Instant.now().getEpochSecond());
        String message = "I want to be the best QA Engineer! " + currentEpoch;
        enterText(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"), message);

        // Select the 'Automation Testing experience' radio button (assuming the label
        // contains 'Automation Testing')
        clickElement(By.xpath("//span[contains(@class,'tyNBNd')]//span[contains(text(),'0 - 2')]"));

        // Select checkboxes for 'Java', 'Selenium', and 'TestNG'
        clickElement(By.xpath("//span[contains(text(),'Java')]"));
        clickElement(By.xpath("//span[contains(text(),'Selenium')]"));
        clickElement(By.xpath("//span[contains(text(),'TestNG')]"));

        Thread.sleep(3000);
         WebElement dropdownElement = driver.findElement(By.xpath("//div[@class='ry3kXd']"));
         dropdownElement.click();  
         Thread.sleep(2000); 
         WebElement optionElement = driver.findElement(By.xpath("(//div[contains(@class,'ncFHed')]//span[not(contains(text(),'Choose'))])[1]"));
         optionElement.click();  
 
         Thread.sleep(3000);

        // Select dropdown = new Select(driver.findElement(By.xpath("//div[contains(@class,'ncFHed')]")));
        // Thread.sleep(3000);
        // dropdown.selectByVisibleText("Mr");
        // Thread.sleep(3000);

        // Provide the current date minus 7 days in the date field
        LocalDate dateMinus7 = LocalDate.now().minusDays(7);
        String formattedDate = dateMinus7.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        enterText(By.xpath("//input[@type='date']"), formattedDate);

        // Provide the time '07:30' in the time field
        enterText(By.xpath("(//input[contains(@class,'zHQkBf')])[3]"), "07");
        enterText(By.xpath("(//input[contains(@class,'zHQkBf')])[4]"), "30");

        // Submit the form
        clickElement(By.xpath("//span[text()='Submit']"));
        Thread.sleep(5000);

        // Wait for the success message and print it
        WebElement successMessage = driver.findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));
                
        System.out.println("Form Submission Success Message: " + successMessage);
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();
    }
}
