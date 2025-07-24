package rohanTest;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

	@Test(priority = 1)
	public void StoreTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));

		driver.get("https://rohanqa.deposyt.store/");
		Assert.assertEquals(driver.getTitle(), "Deposyt Store");

		// Click on SHOP NOW
		driver.findElement(By.xpath("//a[normalize-space()='SHOP NOW']")).click();
//		String targetProduct = "Live 22";
//		String listingPrice = "";
//		String detailPagePrice = "";
//
//		// Wait until products are visible
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.group")));
//
//		// Find all product blocks
//		List<WebElement> products = driver.findElements(By.cssSelector("a.group"));
//
//		for (WebElement product : products) {
//			WebElement title = product.findElement(By.cssSelector("[data-testid='product-title']"));
//
//			if (title.getText().trim().equals(targetProduct)) {
//				// Get listing page price
//				listingPrice = product.findElement(By.cssSelector("[data-testid='price']")).getText().trim();
//				System.out.println("Listing Price: " + listingPrice);
//
//				// Scroll into view and click
//				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
//				product.click();
//
//				
//				wait.until(ExpectedConditions.urlContains("/products/live-22"));
//
//			
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[data-testid='price']")));
//
//			
//				WebElement detailPrice = driver.findElement(By.cssSelector("p[data-testid='price']"));
//				detailPagePrice = detailPrice.getText().trim();
//				System.out.println("Product Page Price: " + detailPagePrice);
//
//				// Validate
//				Assert.assertEquals(detailPagePrice, listingPrice, "Price mismatch!");
//
//				break; 
//				//same selector unable to match 
//			}
//		}
		// search the apple watch
		driver.findElement(By.cssSelector("[data-testid=\"nav-search-link\"]")).click();
		driver.findElement(By.cssSelector("[data-testid='nav-search-link']")).click();
		// Wait for search input to be visible
		WebElement searchInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='search-input']")));
		searchInput.sendKeys("Apple Watch");
		// click on apple watch
		WebElement clickonElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href=\"/products/apple-watch\"]")));
		clickonElement.click();
		// click on add to cart
		WebElement clickonCart = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"add-product-button\"]")));
		clickonCart.click();
		// Locate the Cart element using data-testid
		WebElement cartIcon = driver.findElement(By.cssSelector("[data-testid='nav-cart-link']"));

		// Use Actions class to hover over
		Actions actions = new Actions(driver);
		actions.moveToElement(cartIcon).perform();
		// validate the price
		// Wait for the subtotal element to be visible
		WebElement subtotalElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='cart-subtotal']")));

		// Extract the text and clean it
		String actualSubtotal = subtotalElement.getText().trim();
		String expectedSubtotal = "$240.00";

		// Validate using TestNG assertion
		Assert.assertEquals(actualSubtotal, expectedSubtotal, "Subtotal does not match!");
		Thread.sleep(2000);
		List<WebElement> cartLinks = driver.findElements(By.cssSelector("[data-testid='nav-cart-link']"));
		for (WebElement link : cartLinks) {
			if (link.getText().trim().contains("Cart")) { // Just check if it contains the word "Cart"
				link.click();
				break;
			}
		}
		WebElement dropdown=driver.findElement(By.cssSelector("[data-testid=\"product-select-button\"]"));
		// Create a Select instance
		Select select = new Select(dropdown);

		// Select by value attribute
		select.selectByValue("2");
		//validate the price 
		 WebElement priceElement = driver.findElement(By.xpath("//span[contains(text(), '$480.00')]"));

	        //  Get actual price text and trim spaces
	        String actualPrice = priceElement.getText().trim();

	        // Set the EXPECTED price (update it to your case)
	        String expectedPrice = "$480.00";
	        Assert.assertEquals(actualPrice, expectedPrice, "The displayed price is not as expected!");
	        //click on checkout 
	        driver.findElement(By.cssSelector("[data-testid=\"checkout-button\"]")).click();
	     // 1. Subtotal Amount: Extract and validate
	        WebElement subtotalElem = driver.findElement(By.xpath("//tr[td[contains(text(),'Subtotal')]]/td[last()]"));
	        String subtotalStr = subtotalElem.getText().replace("$", "").trim();
	        double subtotal = Double.parseDouble(subtotalStr);

	        // You can get expected subtotal from your test data or product price * quantity
	        double expectedSubtotal1 = 480.00; // Update as per your cart quantity

	        Assert.assertEquals(subtotal, expectedSubtotal1, "Checkout subtotal is not correct!");

	        // 2. Tax
	        WebElement taxElem = driver.findElement(By.xpath("//tr[td[contains(text(),'Taxes')]]/td[last()]"));
	        String taxStr = taxElem.getText().replace("$", "").trim();
	        double taxes = Double.parseDouble(taxStr);

	        // 3. Total
	        WebElement totalElem = driver.findElement(By.xpath("//tr[td[contains(text(),'Total')]]/td[last()]"));
	        String totalStr = totalElem.getText().replace("$", "").trim();
	        double total = Double.parseDouble(totalStr);

	        // Validate total = subtotal + taxes
	        double calculatedTotal = subtotal + taxes;
	        Assert.assertEquals(total, calculatedTotal, 0.01, "Total is not equal to Subtotal + Taxes!");

	        // 4. Shipping Charges
	        WebElement shippingElem = driver.findElement(By.xpath("//tr[td[contains(text(),'Shipping')]]/td[last()]"));
	        String shippingStr = shippingElem.getText().replace("$", "").trim();
	        double shipping = Double.parseDouble(shippingStr);

	        Assert.assertEquals(shipping, 0.0, "Shipping charges should be 0!");
	}
}
