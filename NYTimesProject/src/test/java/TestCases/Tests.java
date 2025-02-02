package TestCases;

import Pages.HomePage;
import Utility.ReadDataFromExcel;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Tests {
    WebDriver driver;
    HomePage objectHomePage;

    @BeforeTest @Parameters("browser")
    public void setup(String browser) {
            if (browser.equalsIgnoreCase("Edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if (browser.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException("Invalid browser value!!");
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            driver.get("https://www.nytimes.com/international/");
            driver.manage().window().maximize();
            objectHomePage = new HomePage(driver);

    }

    @Test(priority = 1)
    public void navigateToHomePageAndVerifyCurrentPage()
    {
        objectHomePage.verifyCurrentPage();
        objectHomePage.verifyDate();
    }

    @Test(priority = 2)
    public void verifyLoadedContent()
    {
        objectHomePage.verifyLoadedRegions();
        objectHomePage.verifyLoadedFooterNavigationOptions();
        objectHomePage.verifyHeader();
    }

    @Test(priority = 3)
    public void verifySearchOptionShowingRelatableResult()
    {
        objectHomePage.clickOnSearchButton();
        objectHomePage.verifySearchBoxPresent();
        objectHomePage.sendTextinSearchBox();
        objectHomePage.verifyTitle();
        objectHomePage.verifySearchResultsAreReletable();
    }

    @Test(priority = 4)
    public void clickFooterNavigationLinks() {
        objectHomePage.clickOnFooterNavigationOptions();
    }

    @AfterTest
            public void dispose()
    {
        driver.close();
        driver.quit();
    }

}
