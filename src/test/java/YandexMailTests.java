import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CreateMailPage;
import pages.InFolderPage;
import pages.LoginPage;
import pages.MailBox;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class YandexMailTests {
    private String baseURL = "yandex.ru";
    private WebDriver driver;
    
    LoginPage loginPage;
    MailBox mailBox;
    CreateMailPage createMailPage;
    InFolderPage draftsPage, sentPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Volha_Bondarava3\\Downloads\\chromedriver_win32\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        this.driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogin() {
        driver.get(baseURL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        
        Assert.assertTrue(loginPage.checkLogin(), "Login error. ");
    }

    @Test (dependsOnMethods = "testLogin")
    public void testNewMailWithoutSending() {
        mailBox = PageFactory.initElements(driver, MailBox.class);
        mailBox.newMail();
        createMailPage = PageFactory.initElements(driver, CreateMailPage.class);
    }
}
