package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriverWait wait;
    private WebDriver driver;
    private String baseURL;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
        this.baseURL = driver.getCurrentUrl();
    }

    public WebDriver getDriver() {
        return driver;
    }


}
