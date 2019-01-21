package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AutomationMyAccountPage {

    private WebDriver driver;
    private Actions actions = new Actions(Driver.getDriver());

    @FindBy (xpath = "//a[@title='View my shopping cart']")
    public WebElement  myCart;

    @FindBy (xpath = "//div[@class='cart_block_list']//img")
    public List<WebElement> productsInCart;

    @FindBy (xpath = "//span[.='Total']//preceding-sibling::span")
    public WebElement myCartTotal;

    @FindBy (xpath = "//a[@title='My Store']")
    public WebElement homePageLink;




    public AutomationMyAccountPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }


    /**
     *
     * Verifying if all selected products are in the shopping cart
     *
     * @return false if one of the selected product is not in the cart
     * true o/w
     */
    public boolean isAllSelectedProductInCart(){
        boolean answer = false;
        actions.moveToElement( myCart).perform();
        for (int i =0; i < productsInCart.size() ; i++) {
            if(productsInCart.get(i).isDisplayed())
                answer = true;
            if(answer == false)
                return false;
        }
        return productsInCart.size() == 0 ? true : answer ;
    }
}
