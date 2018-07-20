package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;
import java.util.Iterator;

public class MailBox {

    private WebDriver driver;

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
    private WebElement inboxFolderBtn;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftsFolderBtn;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private WebElement sentFolderBtn;

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

    public void newMail() {
        newMailBtn.click();
    }

    public void logoff(String name) {
        WebElement nameBtn = driver.findElement(By.xpath("//div[contains(text(),'" + name + "')]"));
        nameBtn.click();
        logoffBtn.click();
    }

    public void setLogin(String value) {
        loginField.sendKeys(value);
    }

    public void setPassword(String value) {
        passField.sendKeys(value);
    }

    public boolean login(String login, String pass) {
        String curURL = driver.getCurrentUrl();
        loginBtn.click();
        setLogin(login);
        setPassword(pass);
        loginCheckBox.click();
        logBtn.click();
        return !driver.getTitle().equals(curURL);
    }

    public boolean checkDraftsFolder(String address, String subject, String body) {
        draftsFolderBtn.click();
        driver.navigate().refresh();
        Collection<WebElement> messagesList = driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        if (messagesList.isEmpty()) {
            return false;
        }

        Iterator<WebElement> i = messagesList.iterator();
        boolean mailFound = false;
        WebElement message;

        while (i.hasNext()) {
            message = i.next();
            if (message.findElement(By.xpath("//span[@class='mail-MessageSnippet-FromText']")).getAttribute("title").trim().equalsIgnoreCase(address) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span")).getAttribute("title").trim().equals(subject) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/child::span")).getText().trim().equals(body)) {
                mailFound = true;
                break;
            }
        }
        return mailFound;
    }

    public boolean checkSentFolder(String address, String subject, String body) {
        sentFolderBtn.click();
        driver.navigate().refresh();
        Collection<WebElement> messagesList = driver.findElements(By.xpath("//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']"));
        if (messagesList.isEmpty()) {
            return false;
        }
        Iterator<WebElement> i = messagesList.iterator();
        boolean mailFound = false;

        WebElement message;

        while (i.hasNext()) {
            message = i.next();
            if (message.findElement(By.xpath("//span[@class='mail-MessageSnippet-FromText']")).getAttribute("title").trim().equalsIgnoreCase(address) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span")).getAttribute("title").trim().equals(subject) &&
                    message.findElement(By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/child::span")).getAttribute("title").trim().equals(body)) {
                mailFound = true;
                break;
            }
        }
        return mailFound;
    }

    public void setMailFields(String address, String subject, String body) {
        addressField.sendKeys(address);
        subjectField.sendKeys(subject);
        bodyField.sendKeys(body);
    }

    public void saveToDrafts() {
        inboxFolderBtn.click();
        saveBtn.click();
    }

    public void sendMailFromDraft(String subject) {
        draftsFolderBtn.click();
        driver.navigate().refresh();
        WebElement draftMail = driver.findElement(By.xpath("//span[contains(text(), '" + subject + "')]"));
        draftMail.click();
        sendBtn.click();
    }
}
