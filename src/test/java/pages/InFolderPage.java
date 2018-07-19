package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class InFolderPage extends MailBox{

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-FromText']/child::span")
    private LinkedList<WebElement> addressesList;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span")
    private LinkedList<WebElement> subjectList;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']/child::span")
    private LinkedList<WebElement> bodysList;

    @FindBy(className = "ns-view-container-desc mail-MessagesList js-messages-list")
    private Collection<WebElement> messagesList;

    public InFolderPage(WebDriver driver) {
        super(driver);
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
}
