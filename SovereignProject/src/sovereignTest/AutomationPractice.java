package sovereignTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationPractice {
	
WebDriver driver;
Select select;
Actions action = new Actions(driver);
WebDriverWait wait;
String OrderComplete;
String Totalprice;
String PriceOrderTable;
JavascriptExecutor js = (JavascriptExecutor)driver;

@BeforeClass
public void setup() {
System.setProperty("webdriver.chrome.driver","E:\\Testing\\chromedriver_win32\\chromedriver.exe");
driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().deleteAllCookies();
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}

@Test(priority = 1)
public void AccountCreation(){
driver.get("http://automationpractice.com/index.php");
driver.findElement(By.linkText(	"Sign in")).click();
driver.findElement(By.name("email_create")).clear();
driver.findElement(By.name("email_create")).sendKeys("akengg1988@gmail.com");
driver.findElement(By.name("SubmitCreate")).click();
driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

WebElement male_radio_button=driver.findElement(By.name("id_gender"));
male_radio_button.click();

driver.findElement(By.name("customer_firstname")).clear();
driver.findElement(By.name("customer_firstname")).sendKeys("Amit");

driver.findElement(By.name("customer_lastname")).clear();
driver.findElement(By.name("customer_lastname")).sendKeys("Kumar");

driver.findElement(By.name("passwd")).clear();
driver.findElement(By.name("passwd")).sendKeys("Akumar1988");

select = new Select(driver.findElement(By.id("days")));
select.selectByValue("5");

select = new Select(driver.findElement(By.id("months")));
select.selectByValue("5");

select = new Select(driver.findElement(By.id("years")));
select.selectByValue("1988");

driver.findElement(By.name("firstname")).clear();
driver.findElement(By.name("firstname")).sendKeys("Amit");

driver.findElement(By.name("lastname")).clear();
driver.findElement(By.name("lastname")).sendKeys("Kumar");

driver.findElement(By.name("company")).clear();
driver.findElement(By.name("company")).sendKeys("Qualitykiosk");

driver.findElement(By.name("address1")).clear();
driver.findElement(By.name("address1")).sendKeys("Test1");

driver.findElement(By.name("address2")).clear();
driver.findElement(By.name("address2")).sendKeys("Test2");

driver.findElement(By.name("city")).clear();
driver.findElement(By.name("city")).sendKeys("Mumbai");

driver.findElement(By.name("address2")).clear();
driver.findElement(By.name("address2")).sendKeys("Test2");

select = new Select(driver.findElement(By.id("id_state")));
select.selectByValue("1");

driver.findElement(By.name("postcode")).clear();
driver.findElement(By.name("postcode")).sendKeys("32655");

select = new Select(driver.findElement(By.id("id_country")));
select.selectByValue("21");

driver.findElement(By.name("other")).clear();
driver.findElement(By.name("other")).sendKeys("Test3");

driver.findElement(By.name("phone_mobile")).clear();
driver.findElement(By.name("phone_mobile")).sendKeys("8082035025");

driver.findElement(By.name("alias")).clear();
driver.findElement(By.name("alias")).sendKeys("Test4");

driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
driver.findElement(By.linkText("Sign out")).click();


}

@Test(priority = 2)
public void LoginTest() {
	
driver.findElement(By.name("email")).clear();
driver.findElement(By.name("email")).sendKeys("akengg1988@gmail.com");

driver.findElement(By.name("passwd")).clear();
driver.findElement(By.name("passwd")).sendKeys("Akumar1988");

driver.findElement(By.name("SubmitLogin")).click();
	
}

@Test(priority = 3)
public void ProductOrder(){

WebElement Women = driver.findElement(By.linkText("Women"));
js.executeScript("arguments[0].scrollIntoView(true);", Women);
Women.click();
WebElement SortBy = driver.findElement(By.id("selectProductSort"));
js.executeScript("arguments[0].scrollIntoView(true);", SortBy);
SortBy.click();
List<WebElement> ImageHover = driver.findElements(By.xpath("//parent::div[@class='product-image-container']"));
WebElement QuickView = driver.findElement(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//a[@class='quick-view']"));
int ImageHover_size = ImageHover.size();
for (int i = 0; i < ImageHover_size; i++) {
	if (i == 0) {
		action.moveToElement(ImageHover.get(i)).build().perform();
		// QuickView.click();
		wait.until(ExpectedConditions.elementToBeClickable(QuickView)).click();
		break;
	}
}
WebElement fancybox = driver.findElement(By.id("fancybox-frame1585479933808"));
wait.until(ExpectedConditions.visibilityOf(fancybox));
driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
WebElement Quantity = driver.findElement(By.id("quantity_wanted"));
Quantity.clear();
Quantity.sendKeys("2");
WebElement AddtoCart = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
AddtoCart.click();
driver.switchTo().defaultContent();
WebElement ProceedCheckout = driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]"));
ProceedCheckout.click();
WebElement TotalPrice = driver.findElement(By.id("total_price"));
js.executeScript("arguments[0].scrollIntoView(true);", TotalPrice);
///////// Verifying the total cost of product//////////////////////////
Totalprice = TotalPrice.getText();
System.out.println("Total cost of product" + ":-" + Totalprice);
WebElement Proceedbutton = driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]"));
js.executeScript("arguments[0].scrollIntoView(true);", Proceedbutton);
Proceedbutton.click();
WebElement AddressProceed = driver.findElement(By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]"));
js.executeScript("arguments[0].scrollIntoView(true);", AddressProceed);
AddressProceed.click();
WebElement ShippingProceed = driver.findElement(By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]"));
js.executeScript("arguments[0].scrollIntoView(true);", ShippingProceed);
WebElement TNC = driver.findElement(By.id("cgv"));
if (!TNC.isSelected()) {
	TNC.click();
}
WebElement Payment = driver.findElement(By.xpath("//a[@class='bankwire']"));
Payment.click();
WebElement OrderConfirm = driver.findElement(By.xpath("//span[contains(text(),'I confirm my order')]"));
OrderConfirm.click();
WebElement OrderDone = driver.findElement(By.xpath("//strong[contains(text(),'Your order on My Store is complete.')]"));
OrderComplete = OrderDone.getText();
Assert.assertEquals(OrderComplete, "Your order on My Store is complete.");
WebElement Account = driver.findElement(By.xpath("//a[@class='account']"));
js.executeScript("arguments[0].scrollIntoView(true);", Account);
Account.click();
WebElement OrderHistory = driver.findElement(By.xpath("//span[contains(text(),'Order history and details')]"));
OrderHistory.click();
WebElement OrderList = driver.findElement(By.id("order-list"));
js.executeScript("arguments[0].scrollIntoView(true);", OrderList);
List<WebElement> PriceList = driver.findElements(By.xpath("//tr//span[@class='price']"));
int PriceList_size = PriceList.size();
for (int i = 0; i < PriceList_size; i++) {
	if (i == 0) {
		PriceOrderTable = PriceList.get(i).getText();
		break;
	}
}
js.executeScript("window.scrollTo(document.body.scrollHeight, 0)"); // scrolling to the top of the page
// Verifying the amount of order under ORDER HISTORYs
Assert.assertEquals(PriceOrderTable, Totalprice);
}

@AfterClass
public void TearDown(){

driver.quit();
	
}	
}
