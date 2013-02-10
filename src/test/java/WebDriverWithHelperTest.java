package test;


import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;


/**
 * @author Ross Rowe
 */
@Listeners({SauceOnDemandTestListener.class})
public class WebDriverWithHelperTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    public SauceOnDemandAuthentication authentication;

    private WebDriver driver;

    @Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("icreativeapp") String username,
                      @Optional("8e40a4f9-07bd-4bdb-88f2-806eb88c63ab") String key,
                      @Optional("XP") String os,
                      @Optional("opera") String browser,
                      @Optional("11") String browserVersion,
                      Method method) throws Exception {

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(key)) {
            authentication = new SauceOnDemandAuthentication(username, key);
        } else {
            authentication = new SauceOnDemandAuthentication();
        }

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
        desiredCapabilities.setVersion(System.getenv("SELENIUM_VERSION"));
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
        this.driver = new RemoteWebDriver(
                new URL("http://icreativeapp:8e40a4f9-07bd-4bdb-88f2-806eb88c63ab@ondemand.saucelabs.com:80/wd/hub"),
                desiredCapabilities);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }

    @Test
    public void basic() throws Exception {
        driver.get("http://markavip.com");
        driver.findElement(By.className("do_modal")).click();
        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-form")));
        Assert.assertTrue(driver.findElement(By.className("login-form")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }
}
