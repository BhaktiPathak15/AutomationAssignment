package Pages;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {
    WebDriver driver;
    @FindBy(xpath="//a[@aria-label='New York Times homepage' and @class='css-1qcrlqu eoab3xr0']")
    WebElement header;

    @FindBy(xpath="//span[@data-testid='todays-date']")
    WebElement date;

    @FindBy(xpath="//a[@href='/international/' and @lang='en']")
    WebElement currentPage;


    @FindBy(xpath="//ul[@data-testid='masthead-edition-menu']")
    WebElement regionsUl;

    @FindBy(xpath="//button[@data-testid='search-button']")
    WebElement searchButton;

    @FindBy(xpath="//input[@aria-label='Search the new york times']")
    WebElement searchBox;

    @FindBy(xpath="//input[@value='java']")
    WebElement showingResultValue;

    String str;

    @FindBy(xpath="//nav[@data-testid='footer']//child::ul[2]")
    WebElement footerNavFooterUl;





    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void verifyHeader()
    {
        String getHeaderText=header.getAttribute("aria-label");
        assertEquals("New York Times homepage",getHeaderText);

    }
    public void verifyDate()
    {
        String dateContent= date.getText();
        Date todaysDate=new Date();
        String formattedTodaysDate=new SimpleDateFormat("EEEEEEEEE, MMMMM d, y").format(todaysDate);
        assertEquals(dateContent, formattedTodaysDate, "Today's date is fine!");

    }

    public void verifyCurrentPage()
    {
        String currentPageText=currentPage.getText();
        assertEquals("INTERNATIONAL", currentPageText);
    }

    public List<String> getLoadedRegions()
    {
        List<WebElement> regionLis = regionsUl.findElements(By.tagName("li"));

        List<String> regions=new ArrayList<>();

        for(int i=0;i<regionLis.size();i++)
        {
            regions.add(regionLis.get(i).getText());
        }

        return regions;
    }

    public void verifyLoadedRegions()
    {
        List<String> availableRegions = getLoadedRegions();

        List<String> expectedRegions = new ArrayList<String>( Arrays.asList ("U.S.", "INTERNATIONAL", "CANADA", "ESPAÑOL", "中文"));
        
        System.out.println(availableRegions);

        Boolean areListsEqual = availableRegions.equals(expectedRegions);

        assertEquals(areListsEqual, true, "All expected regions are loaded in proper sequence.");

    }

    public void clickOnSearchButton()
    {
        searchButton.click();
    }

    public void verifySearchBoxPresent()
    {
        assertTrue(searchBox.isDisplayed(),"SearchBox is displayed on web page");
    }
    public void sendTextinSearchBox()
    {
        str="java";
        searchBox.sendKeys(str);

    }
    public void verifyTitle()
    {
        String title=driver.getTitle();
        System.out.println(title);
        String expectedTitle="The New York Times International - Breaking News, US News, World News, Videos";
        assertEquals(title,expectedTitle,"Title matched");
    }
    public void verifySearchResultsAreReletable()
    {
        assertEquals(showingResultValue.getAttribute("value"),str," Showing results as per input provided.");
        driver.getPageSource().contains(str);

    }

    public void scrolltoBottomofPage()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public List<WebElement> getLoadedFooter()
    {
        List<WebElement> footerNavLis = footerNavFooterUl.findElements(By.tagName("li"));

        return footerNavLis;

    }

    public List<String> getLoadedFooterNavigationOptions()
    {
        List<WebElement> footerNavLis = getLoadedFooter();

        List<String> footerOptions=new ArrayList<>();

        for(int i=0;i<footerNavLis.size();i++)
        {
            footerOptions.add(footerNavLis.get(i).getText());
        }

        return footerOptions;
    }

    public void verifyLoadedFooterNavigationOptions()
    {
        List<String> loadedFooterOptions = getLoadedFooterNavigationOptions();

        List<String> expectedRegions = new ArrayList<String>( Arrays.asList ("NYTCo", "Contact Us", "Accessibility", "Work with us", "Advertise", "T Brand Studio", "Your Ad Choices", "Privacy Policy", "Terms of Service", "Terms of Sale", "Site Map", "", "", "Help", "Subscriptions"));

        System.out.println(loadedFooterOptions);

        Boolean areListsEqual = loadedFooterOptions.equals(expectedRegions);

        assertEquals(areListsEqual, true, "All expected footer navigation links are loaded in proper sequence.");

    }

    public void clickOnFooterNavigationOptions()
    {
        List<WebElement> footerNavLis = getLoadedFooter();

        for(int i=0;i<footerNavLis.size();i++)
        {
            if (footerNavLis.get(i).getText().equals("Subscriptions"))
            {
                WebElement li = footerNavLis.get(i);
                WebElement a = li.findElement(By.tagName("a"));

                a.click();

            }
        }

    }

}
