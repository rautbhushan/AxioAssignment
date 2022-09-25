package page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	WebDriver driver;
	String url = "https://www.xe.com/currencyconverter/";
	String closePopup = "//*[@aria-label='close']";

	public WebDriver setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		click(By.xpath(closePopup));
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(By.xpath(closePopup));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public boolean click(By locator) {
		boolean flag = isElementPresent(locator);
		if (flag) {
			driver.findElement(locator).click();
			return true;
		} else {
			return false;
		}
	}

}
