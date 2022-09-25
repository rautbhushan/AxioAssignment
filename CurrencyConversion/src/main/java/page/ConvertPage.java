package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConvertPage extends Base{
	String amountTextbox = "amount";
	String fromDropdown = "midmarketFromCurrency";
	String toDropdown = "midmarketToCurrency";
	String currencyFromOption = "//li[contains(@id,'midmarketFromCurrency-option')]//*[contains(text(),'%s')]";
	String currencyToOption = "//li[contains(@id,'midmarketToCurrency-option')]//*[contains(text(),'%s')]";
	String convertBtn = "//button[@type='submit']";
	String resultConvertedText = "//*[contains(@class,'result__ConvertedText')]";
	String resultBigRate = "//*[contains(@class,'result__BigRate')]";
	WebDriver driver;

	public ConvertPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterAmount(String amount) {
		driver.findElement(By.id(amountTextbox)).click();
		driver.findElement(By.id(amountTextbox)).sendKeys(amount);
	}

	public void selectFromCurrency(String currency) {
		driver.findElement(By.id(fromDropdown)).click();
		driver.findElement(By.xpath(String.format(currencyFromOption, currency))).click();
	}

	public void selectToCurrency(String currency) {
		driver.findElement(By.id(toDropdown)).click();
		driver.findElement(By.xpath(String.format(currencyToOption, currency))).click();
	}

	public void clickConvertButton() {
		driver.findElement(By.xpath(convertBtn)).click();
	}

	public String getConversionResult() {
		String from = driver.findElement(By.xpath(resultConvertedText)).getText();
		String to = driver.findElement(By.xpath(resultBigRate)).getText();
		return from + " " + to;
	}

}
