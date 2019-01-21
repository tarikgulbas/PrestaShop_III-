package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AutomationHomePage;
import pages.AutomationMyAccountPage;
import pages.AutomationSignInPage;
import utilities.Configuration;
import utilities.Driver;

public class test {


    @Test
    public void cartLoginTest() {
        Driver.getDriver().get(Configuration.getProperty("url"));

        AutomationHomePage ahp = new AutomationHomePage();
        AutomationSignInPage asp = new AutomationSignInPage();
        AutomationMyAccountPage amp = new AutomationMyAccountPage();

        //Adding random product to the Cart and verifying if all selected products are displayed in the shopping cart
        ahp.addRandomProductToCart();
        Assert.assertTrue(ahp.isAllSelectedProductInCart(), "The products in cart is not displayed in the Home-Page Cart- VERIFICATION FAILED");


        //Signing in to the website with valid data
        ahp.signInLink.click();
        asp.login();

        //Verifying the selected products are displayed in the shopping cart after signing in
        Assert.assertTrue(amp.isAllSelectedProductInCart(), "The products in cart is not displayed in My Account-Page Cart - VERIFICATION FAILED");

        Driver.close();

    }


    @Test
    public void cartLogoutTest() {
        Driver.getDriver().get(Configuration.getProperty("url"));

        AutomationHomePage ahp = new AutomationHomePage();
        AutomationSignInPage asp = new AutomationSignInPage();
        AutomationMyAccountPage amp = new AutomationMyAccountPage();

        //Signing in to the website with valid data
        ahp.signInLink.click();
        asp.login();

        //Adding random product to the Cart and verifying all selected products are displayed in the shopping cart
        amp.homePageLink.click();
        ahp.addRandomProductToCart();
        Assert.assertTrue(ahp.isAllSelectedProductInCart(), "The products in cart is not displayed in the Home-Page Cart- VERIFICATION FAILED");

        //Signing out and verifying the cart displays "(empty)"
        amp.homePageLink.click();
        ahp.signOutBtn.click();
        Assert.assertEquals(ahp.getCartSummary(), "(empty)", "Verification of \"(empty)\" cart FAILED");

        Driver.close();
    }


    @Test
    public void cartIconDeleteTest() throws InterruptedException {
        Driver.getDriver().get(Configuration.getProperty("url"));

        AutomationHomePage ahp = new AutomationHomePage();
        AutomationSignInPage asp = new AutomationSignInPage();
        AutomationMyAccountPage amp = new AutomationMyAccountPage();

        //Adding random product to the Cart and verifying all selected products are displayed in the shopping cart
        ahp.addRandomProductToCart();
        Assert.assertTrue(ahp.isAllSelectedProductInCart(), "The products in cart is not displayed in the Home-Page Cart- VERIFICATION FAILED");

        //Removing product from the Cart and verifying the cart displays "(empty)"
        ahp.xRemovesProductFromCart.click();
        Thread.sleep(1000);
        Assert.assertEquals(ahp.getCartSummary(), "(empty)", "Verification of \"(empty)\" cart FAILED");

    }

    @Test
    public void checkCartCheckoutDeleteTest() throws InterruptedException {
        Driver.getDriver().get(Configuration.getProperty("url"));

        AutomationHomePage ahp = new AutomationHomePage();
        AutomationSignInPage asp = new AutomationSignInPage();
        AutomationMyAccountPage amp = new AutomationMyAccountPage();

        //Adding 2 random products to the Cart and verifying the cart has "2 Products" message
        ahp.addRandomProductToCart();
        ahp.addRandomProductToCart();
        Assert.assertEquals(ahp.getCartSummary(), "2 Products", "The cart summary - Home Page- VERIFICATION FAILED");

        //Removing products one by one and verifying the message is updated accordingly
        ahp.removeProductFromCart();
        Assert.assertEquals(ahp.getCartSummary(), "1 Product", "The cart summary - Home Page- VERIFICATION FAILED");

        ahp.removeProductFromCart();
        Assert.assertEquals(ahp.getCartSummary(), "(empty)", "The cart summary - Home Page- VERIFICATION FAILED");

        Driver.close();
    }



}
