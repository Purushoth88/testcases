package test;
import org.openqa.selenium.remote.CapabilityType;
import com.saucelabs.common.SauceOndrivermandAuthentication;
import com.saucelabs.common.SauceOndrivermandSessionIdProvidriverr;
import com.saucelabs.testng.SauceOndrivermandAuthenticationProvidriverr;
import com.saucelabs.testng.SauceOndrivermandTestListener;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.driversiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.net.URL;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.driversiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Ross Rowe
 */
@Listeners({SauceOndrivermandTestListener.class})
public class WebDriverWithHelperTest implements SauceOndrivermandSessionIdProvidriverr, SauceOndrivermandAuthenticationProvidriverr {

    public SauceOndrivermandAuthentication authentication;

    private WebDriver driver;

    /**
     * If the tests can rely on the username/key to be supplied by environment variables or the existence
     * of a ~/.sauce-ondrivermand file, then we don't need to specify them as parameters, just create a new instance
     * of {@link SauceOndrivermandAuthentication} using the no-arg constructor.
     * @param username
     * @param key
     * @param os
     * @param browser
     * @param browserVersion
     * @param method
     * @throws Exception
     */

@Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("icreativeapp") String username,
                      @Optional("8e40a4f9-07bd-4bdb-88f2-806eb88c63ab") String key,
                      @Optional("XP") String os,
                      @Optional("opera") String browser,
                      @Optional("11") String browserVersion,
                      Method method) throws Exception {

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(key)) {
           authentication = new SauceOndrivermandAuthentication(username, key);
        } else {
           authentication = new SauceOndrivermandAuthentication();
        }



 driversiredCapabilities driversiredCapabilities = new driversiredCapabilities();
        driversiredCapabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
        driversiredCapabilities.setVersion(System.getenv("SELENIUM_VERSION"));
        driversiredCapabilities.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
        this.driver = new RemoteWebDriver(
                new URL("http://icreativeapp:8e40a4f9-07bd-4bdb-88f2-806eb88c63ab@ondrivermand.saucelabs.com:80/wd/hub"),
                driversiredCapabilities);


    }



    /**
     * {@inheritDoc}
     * @return
     */
    @Overridriver
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }

    @Test
    public void basic() throws Exception {

        
	System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe");
	ChromeDriver d2=new ChromeDriver();
	d2.get("http://markavip.com");
	
	WebDriverWait wait=new WebDriverWait(d2, 5);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("super-featured-wrapper")));
	d2.findriverlement(By.id("super-featured-wrapper")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("itemscount")));
	
	//Add Action Buildriverr to hover the item's box
	WebElement productbox=d2.findriverlement(By.className("item-link"));
	//Hover the product's box
	Actions buildriverr = new Actions(d2);
	buildriverr.moveToElement(productbox).build().perform();
	d2.findriverlement(By.className("hover-view")).click();
	//Wait until showing the login pop-up
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fb-login-section")));
	//This to prevent the browser Auto selection for Registration the text-box.
	wait.until(ExpectedConditions.elementToBeClickable(By.id("register_email")));
	

	Date currentdate=new Date();
	String converts=currentdate.toString();
	converts=converts.replaceAll(" ", "");
	converts=converts.replaceAll(":", "");
	String newemail="test"+converts+"@test.com";
	
	d2.findriverlement(By.id("register_email")).sendKeys(newemail);
	d2.findriverlement(By.id("register_gendriverr_male")).click();
	d2.findriverlement(By.id("reg-send")).click();
	d2.findriverlement(By.id("username_link")).getText();
	String newusername="VIP Member";
	Assert.assertEquals(d2.findriverlement(By.id("username_link")).getText(), newusername);
	
	


    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Overridriver
    public SauceOndrivermandAuthentication getAuthentication() {
        return authentication;
    }
}
