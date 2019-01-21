package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AutomationHomePage {

    private WebDriver driver;
    private Actions actions = new Actions(Driver.getDriver());

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    public WebElement myCart;

    @FindBy (xpath ="//ul[@id='homefeatured']//li//img")
    public List<WebElement> products;

    @FindBy (xpath = "//ul//span[contains(text(),'Add to cart')]")
    public List<WebElement> addToCartLink;


    @FindBy (xpath = "//span[@title='Close window']")
    public WebElement closePopupWindow;

    @FindBy (xpath = "//span[@title='Continue shopping']//span")
    public WebElement continueShopping;

    @FindBy (xpath = "//span[.='Total']//preceding-sibling::span")
    public WebElement myCartTotal;

    @FindBy (xpath = "//div[@class='cart_block_list']//img")
    public List<WebElement> productsInCart;

    @FindBy (xpath = "//a[@title='Log in to your customer account']")
    public WebElement signInLink;

    @FindBy (xpath = "//a[@title='Log me out']")
    public WebElement signOutBtn;

    @FindBy (xpath = "//a//span[contains(@class,'ajax_cart')][contains(@style,'inline')][not(contains(.,'$'))]")
    public List<WebElement> cartSummary;

    @FindBy (xpath = "//a[@class='ajax_cart_block_remove_link']")
    public WebElement xRemovesProductFromCart;





    public AutomationHomePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    /**
     *
     * @param productName
     *
     * Adding the given product name to the cart
     */
    public void addProductToCart(String productName){
        for (int i = 0; i <products.size(); i++){
            if(products.get(i).getAttribute("alt").contains(productName)){
                actions.moveToElement(products.get(i)).perform();
                addToCartLink.get(i).click();
                continueShopping.click();
                return;
            }
        }
    }

    /**
     * Adding random product from the homepage to the shopping cart
     */
    public void addRandomProductToCart(){
        addProductToCart(products.get((int)(Math.random() * products.size())).getAttribute("alt").trim());
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
        actions.sendKeys(Keys.PAGE_UP).perform();
        actions.moveToElement( myCart).perform();
        for (int i =0; i < productsInCart.size() ; i++) {
            if(productsInCart.get(i).isDisplayed())
                answer = true;
            if(answer == false)
                return false;
        }
        return productsInCart.size() == 0 ? true : answer ;
    }

    public String getCartSummary(){
        actions.sendKeys(Keys.PAGE_UP).perform();
        String summary = "";
        for (WebElement element: cartSummary) {
            summary +=  element.getText() + " ";
        }
    return summary.trim();
    }


    public void removeProductFromCart() throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,3000)");
        actions.moveToElement( myCart).build().perform();
        actions.click(xRemovesProductFromCart).perform();
        Thread.sleep(1000);
    }

}
