package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import page.Base;
import page.ConvertPage;
import page.Utility;

public class ConvertTest {
	WebDriver driver;
	Base base;
	ConvertPage convert;
	Utility utility = new Utility();;
	String output = "";

	@BeforeMethod
	public void beforeMethod() {
		base = new Base();
		driver = base.setup();
		convert = new ConvertPage(driver);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

	@DataProvider(name = "getData")
	public Object[][] getData() {
		Object[][] arrayObject = null;
		try {
			System.out.println(utility);
			arrayObject = utility.getExcelData(
					System.getProperty("user.dir") + "\\src\\main\\resources\\InputData\\inputData.xlsx", "Sheet2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayObject;
	}

	@Test(dataProvider = "getData")
	public void convertCorency(String amount, String from, String to) {
		convert.enterAmount(amount);
		convert.selectFromCurrency(from);
		convert.selectToCurrency(to);
		convert.clickConvertButton();
		output = output + convert.getConversionResult() + "\n";
		utility.writeOutputInFile(output);
	}

}
