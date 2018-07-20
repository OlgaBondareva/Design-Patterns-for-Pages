import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MailBox;

import java.util.concurrent.TimeUnit;

public class YandexMailTests {

    private WebDriver driver;
    private MailBox mailBox;

    private static final String login = "test.webdriver.java";
    private static final String password = "qwe123456";
    private static final String address = "Volha_Bondarava3@epam.com";
    private static final String subject = "Webdriver Test";
    private static final String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Volha_Bondarava3\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        this.driver = new ChromeDriver(chromeOptions);
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        mailBox.logoff(login);
        driver.quit();
    }

    @Test
    public void testLogin() {
        String baseURL = "https://yandex.by/";
        driver.get(baseURL);
        mailBox = PageFactory.initElements(driver, MailBox.class);
        Assert.assertTrue(mailBox.login(login, password), "Can't login into mail. ");
    }

    @Test(dependsOnMethods = "testLogin")
    public void testDraft() {
        mailBox.newMail();
        mailBox.setMailFields(address, subject, body);
        mailBox.saveToDrafts();
        mailBox.goToDraftFolder();
        Assert.assertTrue(mailBox.checkFolder(address, subject, body), "Can't find required message in draft folder. ");
    }

    @Test(dependsOnMethods = {"testLogin", "testDraft"})
    public void testEmptyDraftFolder() {
        mailBox.sendMailFromDraft(subject);
        mailBox.goToDraftFolder();
        Assert.assertFalse(mailBox.checkFolder(address, subject, body));
    }

    @Test(dependsOnMethods = {"testLogin", "testDraft", "testEmptyDraftFolder"})
    public void testSentFolder() {
        mailBox.goToSentFolder();
        Assert.assertTrue(mailBox.checkFolder(address, subject, body));
    }
}
