package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateMailPage extends MailBox {

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

    public CreateMailPage(WebDriver driver) {
        super(driver);
    }

    public CreateMailPage setFields(String address, String subject, String body) {
        addressField.sendKeys(address);
        subjectField.sendKeys(subject);
        bodyField.sendKeys(body);
        return this;
    }

    public CreateMailPage saveToDrafts() {
        inboxBtn.click();
        saveBtn.click();
        return this;
    }
}
