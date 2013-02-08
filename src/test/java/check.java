import com.saucelabs.common.SauceOnDemandAuthentication; 
import com.saucelabs.common.SauceOnDemandSessionIdProvider; 
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;

import static org.testng.Assert.assertEquals;
/**
 *
 * @author Ross Rowe
 */

public class check{

    private WebDriver driver;

    /**
     * If the tests can rely on the username/key to be supplied by environment variables or the existence
     * of a ~/.sauce-ondemand file, then we don't need to specify them as parameters, just create a new instance
     * of {@link SauceOnDemandAuthentication} using the no-arg constructor.
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
			  @Optional("firefox") String browser,
			  @Optional("5.0") String browserVersion,
			  Method method) throws Exception {


        DesiredCapabilities capabillities = new DesiredCapabilities();
        capabillities.setBrowserName(browser);
        capabillities.setCapability("version", browserVersion);
        capabillities.setCapability("platform", Platform.valueOf(os));
        capabillities.setCapability("name", method.getName());
        this.driver = new RemoteWebDriver(
					  new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
					  capabillities);
    }

    @Test
	public void basic() throws Exception {
        driver.get("http://www.amazon.com/");
        assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }

    @AfterMethod
	public void tearDown() throws Exception {
        driver.quit();
    }
}