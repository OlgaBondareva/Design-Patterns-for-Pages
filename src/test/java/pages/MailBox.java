package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class MailBox {

    private WebDriverWait wait;
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-FromText']/child::span")
    private LinkedList<WebElement> addressesList;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span")
    private LinkedList<WebElement> subjectList;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/child::span")
    private LinkedList<WebElement> bodysList;

    @FindBy(className = "ns-view-container-desc mail-MessagesList js-messages-list")
    private Collection<WebElement> messagesList;

    @FindBy(name = "to")
    private WebElement addressField;

    @FindBy(name = "subj-80831344dbf9e619ae8435b57bc64ed9043bf460")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@class='cke_contents cke_reset']/textarea[@role='textbox']")
    private WebElement bodyField;

    @FindBy(xpath = "//span[contains(text(),'Сохранить и перейти')]")
    private WebElement saveBtn;

    @FindBy(xpath = "//span[contains(text(), 'Отправить')]")
    private WebElement sendBtn;

    @FindBy(xpath = "//span[contains(text(),'Написать')]/parent::a")
    private WebElement newMailBtn;

    @FindBy(xpath = "//span[contains(text(),'Входящие')]")
    private WebElement inboxBtn;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftsBtn;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private WebElement sentBtn;

    @FindBy(xpath = "//a[contains(text(),'Выйти')]")
    private WebElement logoffBtn;

    @FindBy(xpath = "//span[contains(text(),'Войти')]/parent::a")
    private WebElement loginBtn;

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "passwd")
    private WebElement passField;

    @FindBy(className = "passport-Checkbox")
    private WebElement loginCheckBox;

    @FindBy(className = "passport-Button")
    private WebElement logBtn;

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

    public MailBox setLogin(String value) {
        loginField.sendKeys(value);
        return this;
    }

    public MailBox setPassword(String value) {
        passField.sendKeys(value);
        return this;
    }

    public MailBox setFields(String login, String pass) {
        setLogin(login);
        setPassword(pass);
        loginCheckBox.click();
        return this;
    }

    public MailBox submitForm() {
        logBtn.click();
        return this;
    }

    public LoginPage setAndSubmit(String login, String pass) {
        setFields(login, pass);
        return submitForm();
    }

    public boolean checkLogin() {
        return !driver.getTitle().equals(baseURL);
    }

    public boolean checkDraftsFolder(String address, String subject, String body) {
        driver.navigate().refresh();
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        draftsBtn.click();
        Iterator<WebElement> i = messagesList.iterator();
        boolean mailFound = false;

        WebElement message;

        while (i.hasNext()) {
            message = i.next();
            if (message.findElement(By.xpath("//span[@class='mail-MessageSnippet-FromText']/child::span")).getAttribute("title").equals(address) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span")).getAttribute("title").equals(subject) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/child::span")).getAttribute("title").equals(body)) {
                mailFound = true;
                break;
            }
        }
        return mailFound;
    }

    public boolean checkSentFolder(String address, String subject, String body) {
        driver.navigate().refresh();
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sentBtn.click();
        Iterator<WebElement> i = messagesList.iterator();
        boolean mailFound = false;

        WebElement message;

        while (i.hasNext()) {
            message = i.next();
            if (message.findElement(By.xpath("//span[@class='mail-MessageSnippet-FromText']/child::span")).getAttribute("title").equals(address) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span")).getAttribute("title").equals(subject) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/child::span")).getAttribute("title").equals(body)) {
                mailFound = true;
                break;
            }
        }
        return mailFound;
    }

    public MailBox setFields(String address, String subject, String body) {
        addressField.sendKeys(address);
        subjectField.sendKeys(subject);
        bodyField.sendKeys(body);
        return this;
    }

    public MailBox saveToDrafts() {
        inboxBtn.click();
        saveBtn.click();
        return this;
    }

    public MailBox sendMailFromDraft() {

    }
}
