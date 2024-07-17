package TestCases;

import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tests {
    WebDriver driver;
    HomePage objectHomePage;

    @BeforeTest
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.nytimes.com/international/");
        driver.manage().window().maximize();

        objectHomePage=new HomePage(driver);
    }

    @Test(priority = 1)
    public void navigateToHOmePageAndVerifyCurrentPage()
    {

        objectHomePage.verifyHeader();
        objectHomePage.verifyCurrentPage();
        objectHomePage.verifyDate();

    }

    @Test(priority = 2)
    public void verifyLoadedContent()
    {
        objectHomePage.verifyLoadedRegions();
        objectHomePage.verifyLoadedFooterNavigationOptions();
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
    public void clickFooterNavigationLinks()
    {
        objectHomePage.clickOnFooterNavigationOptions();
    }


    @AfterTest
            public void dispose()
    {
        this.driver.close();
        this.driver.quit();
    }

}
