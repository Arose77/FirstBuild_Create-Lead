package wdMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class SeMethods implements WdMethods{

	RemoteWebDriver driver = null;
	public void startApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			//Maximize the browser
			driver.manage().window().maximize();
			//Load the URL
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("The "+browser+" browser launched successfully");
		} catch (WebDriverException e) {
			System.err.println("No such browser");
			e.printStackTrace();
		} finally {
			takeSnap();}
	}

	public WebElement locateElement(String locator, String locValue) {
		WebElement ele = null;
		try {
			switch (locator) {
			case "id":
				ele = driver.findElementById(locValue);
				break;
			case "class":
				ele = driver.findElementByClassName(locValue);
				break;
			case "name":
				ele = driver.findElementByName(locValue);
				break;
			case "linktext":
				ele = driver.findElementByLinkText(locValue);
				break;
			case "xpath":
				ele = driver.findElementByXPath(locValue);
				break;

			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.err.println("No Such element is present");
			e.printStackTrace();
		}
		return ele;

	}

	public WebElement locateElement(String locValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public void type(WebElement ele, String data) {
		ele.sendKeys(data);
		System.out.println("The given value is entered "+data);
		takeSnap();
	}

	public void click(WebElement ele) {
		try {
			ele.click();
			System.out.println("The element is clicked successfully");
			takeSnap();
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText(WebElement ele) {
		// TODO Auto-generated method stub
		String s1=ele.getText();
		System.out.println(s1);
		return s1;

	}

	public void selectDropDownUsingText(WebElement ele, String value) {
		// TODO Auto-generated method stub
		WebElement dp=ele;
		Select dropDown=new Select(dp);
		dropDown.selectByVisibleText(value);
	}

	public void selectDropDownUsingIndex(WebElement ele, int index) {
		// TODO Auto-generated method stub
		//ele.get
		WebElement dp=ele;
		Select dropDown=new Select(dp);
		dropDown.selectByIndex(index);

	}

	public boolean verifyTitle(String expectedTitle) {
		// TODO Auto-generated method stub
		String title=driver.getTitle();
		if(title.contains(expectedTitle)) {
			return true;
		} else
			return false;
	}

	public void verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		//String expected=ele.getText();
		if(ele.getText().equals(expectedText)){
			System.out.println("Text matched");
		}else
			System.out.println("Text not matched");

	}

	/*public void verifyTitle() {
		// TODO Auto-generated method stub
		driver.getTitle();
		if(ele.getText().equals(expectedText)){
			System.out.println("Text matched");
		}else
			System.out.println("Text not matched");

	}*/

	public void verifyPartialText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		if(getText(ele).contains(expectedText)) {
			System.out.println("Partial text matched");
		} else
			System.out.println("Partial text not matched");

	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		// TODO Auto-generated method stub
		if(ele.getAttribute(value).equals(attribute)) {
			System.out.println("ExactAttribute matched");
		} else
			System.out.println("ExactAttribute not matched");
	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		// TODO Auto-generated method stub
		if(ele.getAttribute(value).contains(attribute)) {
			System.out.println("Partial Attribute matched");
		} else
			System.out.println("Partial attribute not matched");
	}

	public void verifySelected(WebElement ele) {
		// TODO Auto-generated method stub
		ele.isSelected();
		System.out.println("Element is selected");
	}

	public void clearText(WebElement ele) {
		// TODO Auto-generated method stub
		ele.clear();
		System.out.println("Text is cleared");
	}

	public void verifyDisplayed(WebElement ele) {
		// TODO Auto-generated method stub
		ele.isDisplayed();
	}

	public void switchToWindow(int index) {
		// TODO Auto-generated method stub
		List<String> windList=new ArrayList<String>();
		Set<String> allWindows1=driver.getWindowHandles();

		windList.addAll(allWindows1);
		driver.switchTo().window(windList.get(index));
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		System.out.println("Winodw switched successfully");
	}

	public void switchToFrame(WebElement ele) {
		// TODO Auto-generated method stub
		driver.switchTo().frame(ele);
		System.out.println("Switched to the frame");


	}

	public void acceptAlert() {
		// TODO Auto-generated method stub
		driver.switchTo().alert().accept();
		System.out.println("Alert accepted");
	}

	public void dismissAlert() {
		// TODO Auto-generated method stub
		driver.switchTo().alert().dismiss();
		System.out.println("Alert dismissed");
	}

	public String getAlertText() {
		// TODO Auto-generated method stub
		System.out.println("Alert text "+driver.switchTo().alert().getText());

		return null;
	}

	int i =1;
	public void takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./snaps/img"+i+".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i++;
	}

	public void closeBrowser() {
		// TODO Auto-generated method stub
		driver.close();
	}

	public void closeAllBrowsers() {
		// TODO Auto-generated method stub
		driver.quit();
	}

	@Override
	public void clickWithoutSnap(WebElement ele) {
		ele.click();
		System.out.println("The element is clicked successfully");

	}
}


