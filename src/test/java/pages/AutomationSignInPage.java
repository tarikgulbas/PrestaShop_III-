package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Configuration;
import utilities.Driver;

public class AutomationSignInPage {
    private WebDriver driver;

    @FindBy (id = "email")
    public WebElement username;

    @FindBy (id = "passwd")
    public WebElement password;

    @FindBy (id = "SubmitLogin")
    public WebElement signInBtn;

    @FindBy (id = "email_create")
    public WebElement emailCreate;

    @FindBy (id = "SubmitCreate")
    public WebElement emailCreateBtn;

    public AutomationSignInPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }


    public void login(){
        username.sendKeys(Configuration.getProperty("username"));
        password.sendKeys(Configuration.getProperty("password"));
        signInBtn.click();

    }
}
