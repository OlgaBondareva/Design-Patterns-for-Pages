import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailBox;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class YandexMailTests {
    private String baseURL = "yandex.ru";
    private WebDriver driver;
    
    private LoginPage loginPage;
    private MailBox mailBox;
    private ChangeMailPage changeMailPage;
    private InFolderPage folderPage;

    private static final String login = "test.webdriver.java";
    private static final String password = "qwe123456";
    private static final String address = "Volha_Bondarava3@epam.com";
    private static final String subject = "Webdriver Test";
    private static final String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";

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
        loginPage.setAndSubmit(login, password);
        Assert.assertTrue(loginPage.checkLogin(), "Can't login into mail. ");
    }

    @Test (dependsOnMethods = "testLogin")
    public void testNewMailWithoutSending() {
        mailBox = PageFactory.initElements(driver, MailBox.class);
        mailBox.newMail();
        changeMailPage = PageFactory.initElements(driver, ChangeMailPage.class);
        changeMailPage.setFields(address, subject, body);
        changeMailPage.saveToDrafts();
        folderPage = PageFactory.initElements(driver, InFolderPage.class);
        Assert.assertTrue(folderPage.checkDraftsFolder(address, subject, body), "Can't find required message in draft folder. ");
    }

    @Test (dependsOnMethods = {"testLogin", "testNewMailWithoutSending"})
    public void testDraftContent() {

        folderPage.checkSentFolder(address, subject, body);

    }
}
