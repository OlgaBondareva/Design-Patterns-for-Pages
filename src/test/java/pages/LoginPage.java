package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriverWait wait;
    private WebDriver driver;
    private String baseURL;

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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
        this.baseURL = driver.getCurrentUrl();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage setLogin(String value) {
        loginField.sendKeys(value);
        return this;
    }

    public LoginPage setPassword(String value) {
        passField.sendKeys(value);
        return this;
    }

    public LoginPage setFields(String login, String pass) {
        setLogin(login);
        setPassword(pass);
        loginCheckBox.click();
        return this;
    }

    public LoginPage submitForm() {
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
}
