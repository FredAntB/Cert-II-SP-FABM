import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SPTestLinkSauceDemoTests extends BaseTest {
    String username = "standard_user";
    String password = "secret_sauce";

    private void login()
    {
        // WebElement usernameTextBox = driver.findElement(By.id("user-name"));
        WebElement usernameTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        usernameTextBox.sendKeys(username);

        //WebElement passwordTextBox = driver.findElement(By.id("password"));
        WebElement passwordTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        passwordTextBox.sendKeys(password);

        // WebElement loginButton = driver.findElement(By.id("login-button"));
        WebElement loginButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("login-button")));
        loginButton.click();

    }

    // SD-2: Esta prueba verifica que los productos que son mostrados en la
    // pagina principal pueden ser ordenados de acuerdo a su precio de mayor
    // valor a menor valor a traves del drop down de ordenamiento.
    @Test
    public void TestSortProductsByPriceHighToLow()
    {
        login();

        // WebElement sortingDropDown = driver.findElement(By.className("product_sort_container"));
        WebElement sortingDropDown = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("product_sort_container")));
        Select sortingDropDownOption = new Select(sortingDropDown);
        sortingDropDownOption.selectByVisibleText("Price (high to low)");

        // Verification
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));

        List<Double> productsPrices = new ArrayList<>();

        for(WebElement product : products)
        {
            // System.out.println(product.getText());
            productsPrices.add(Double.parseDouble(product.getText().substring(1)));
        }

        for(Double productPrice : productsPrices)
        {
            System.out.println(productPrice);
        }

        boolean isSorted = Ordering.natural().reverse().isOrdered(productsPrices);

        Assertions.assertTrue(isSorted);
    }

    // SD-1:  Esta prueba verifica que los productos que son mostrados en la
    // pagina principal pueden ser ordenados de acuerdo a su precio de menor
    // valor a mayor valor a traves del drop down de ordenamiento.
    @Test
    public void TestSortProductsByPriceLowToHigh()
    {
        login();

        // WebElement sortingDropDown = driver.findElement(By.className("product_sort_container"));
        WebElement sortingDropDown = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("product_sort_container")));
        Select sortingDropDownOption = new Select(sortingDropDown);
        sortingDropDownOption.selectByVisibleText("Price (low to high)");

        // Verification
        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        List<Double> productsPrices = new ArrayList<>();

        for(WebElement product : products)
        {
            productsPrices.add(Double.parseDouble(product.getText().substring(1)));
        }

        for(Double productPrice : productsPrices)
        {
            System.out.println(productPrice);
        }

        boolean isSorted = Ordering.natural().isOrdered(productsPrices);
        Assertions.assertTrue(isSorted);
    }

    // SD-12: Esta prueba verifica que al presionar el botón Add To Cart
    // de un producto, este cambia de estado, que el carrito despliega un
    // contador de cuantos productos fueron agregados al mismo y que el
    // producto se encuentra en el carrito.
    @Test
    public void TestAddToCartButtonCountAdded()
    {
        login();

        // WebElement addToCartButton = driver.findElement(By.className("btn_primary"));
        WebElement addToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("btn_primary")));
        addToCartButton.click();

        Assertions.assertEquals(addToCartButton.getText(), "REMOVE");

        // classnames -> fa-layers-counter shopping_cart_badge
        // WebElement cartProductCounter = driver.findElement(By.className("fa-layers-counter"));
        WebElement cartProductCounter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("fa-layers-counter")));
        Assertions.assertEquals(cartProductCounter.getText(), "1");

        // classnames -> shopping_cart_link fa-layers fa-fw
        // WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        WebElement cartButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_link")));
        cartButton.click();

        List<WebElement> productsInCart = driver.findElements(By.className("cart_item"));
        Assertions.assertEquals(productsInCart.size(), 1);
    }

    // SD-19: Esta prueba verifica que no es posible continuar el checkout sin
    // ingresar un nombre en el formulario "Checkout: Your Information"
    @Test
    public void TestCheckoutFailsWithoutName()
    {
        login();

        // WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        WebElement cartButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_link")));
        cartButton.click();

        // WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        WebElement checkoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("checkout_button")));
        checkoutButton.click();

        // WebElement lastnameTextBox = driver.findElement(By.id("last-name"));
        WebElement lastnameTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("last-name")));
        lastnameTextBox.sendKeys("Viscarra");

        // WebElement postalCodeTextBox = driver.findElement(By.id("postal-code"));
        WebElement postalCodeTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postal-code")));
        postalCodeTextBox.sendKeys("12345");

        // WebElement continueButton = driver.findElement(By.className("cart_button"));
        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("cart_button")));
        continueButton.click();

        // WebElement errorButton = driver.findElement(By.className("error-button"));
        WebElement errorButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("error-button")));

        Assertions.assertTrue(errorButton.isDisplayed());
    }

    // SD-20: Esta prueba verifica que no es posible continuar el checkout sin
    // ingresar un apellido en el formulario "Checkout: Your Information"
    @Test
    public void TestCheckoutFailsWithoutLastname()
    {
        login();

        // WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        WebElement cartButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_link")));
        cartButton.click();

        // WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        WebElement checkoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("checkout_button")));
        checkoutButton.click();

        // WebElement lastnameTextBox = driver.findElement(By.id("first-name"));
        WebElement lastnameTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("first-name")));
        lastnameTextBox.sendKeys("Mauricio");

        // WebElement postalCodeTextBox = driver.findElement(By.id("postal-code"));
        WebElement postalCodeTextBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postal-code")));
        postalCodeTextBox.sendKeys("12345");

        // WebElement continueButton = driver.findElement(By.className("cart_button"));
        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("cart_button")));
        continueButton.click();

        // WebElement errorButton = driver.findElement(By.className("error-button"));
        WebElement errorButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("error-button")));

        Assertions.assertTrue(errorButton.isDisplayed());
    }
}
