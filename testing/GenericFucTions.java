package dbsBookCab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenericFucTions {

	public static void enterdata(WebDriver driver,String locatorvalue,String data)
	{
		driver.findElement(By.id(locatorvalue)).sendKeys(data);
	}
	public static void click(WebDriver driver,String locatorvalue)
	{
		driver.findElement(By.id(locatorvalue)).click();;
	}
	public static String geText(WebDriver driver,String locatorvalue)
	{
		String text=driver.findElement(By.id(locatorvalue)).getText();
		return text;
	}
	public static boolean verify(WebDriver driver,String locatorvalue)
	{
		if(driver.findElement(By.id(locatorvalue)).isDisplayed())
		{
			if(driver.findElement(By.id(locatorvalue)).isEnabled())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}

}
