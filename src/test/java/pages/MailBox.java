package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBox {

    protected WebDriverWait wait;
    protected WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Написать')]/parent::a")
    protected WebElement newMailBtn;

    @FindBy(xpath = "//span[contains(text(),'Входящие')]")
    protected WebElement inboxBtn;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    protected WebElement draftsBtn;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    protected WebElement sentBtn;

    @FindBy(xpath = "//a[contains(text(),'Выйти')]")
    protected WebElement logoffBtn;

    public MailBox(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public MailBox newMail() {
        newMailBtn.click();
        return this;
    }

    public MailBox inbox() {
        inboxBtn.click();
        return this;
    }

    public MailBox drafts() {
        draftsBtn.click();
        return this;
    }

    public MailBox sent() {
        sentBtn.click();
        return this;
    }

    public MailBox logoff() {
        logoffBtn.click();
        return this;
    }
}
