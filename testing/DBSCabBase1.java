package dbsBookCab;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jayway.restassured.RestAssured;

public class DBSCabBase1 
{
	static WebDriver driver;
	static Properties properties;
	public static HashMap< String, String> hashdata;
	
	public DBSCabBase1() throws Throwable
	{
		properties=new Properties();
		FileInputStream pro=new FileInputStream(System.getProperty("user.dier")+"\\properties\\DBSCabBookroperyies.properties");
		properties.load(pro);
		hashdata=new HashMap<String, String>();
		
	}
	public static boolean signUpWithGoogle() 
		{
		boolean flag=true;
		try
		{
		driver.findElement(By.id(properties.getProperty("username"))).sendKeys(properties.getProperty("userEmail"));
		
		}
		catch(Exception e1)
		{
			System.out.println(e1);
			 flag= false;
		}
		return flag;
		
		
	}

	public static boolean NewUserProfile() {
		boolean flag=true;
		try{
		GenericFucTions.enterdata(driver, properties.getProperty("newProfileName"), "Testdata");
		GenericFucTions.enterdata(driver, properties.getProperty("newEmailId"), "23456");
		GenericFucTions.enterdata(driver, properties.getProperty("newPhoneNumber"), "23456");
		GenericFucTions.click(driver, properties.getProperty("newSubMitbutton"));
		}
		catch(Exception e1)
		{
			flag=false;
		}
		return flag;
				
			}

	public static int ResponceCode() throws Exception 
	{
		
       WebElement link=driver.findElement(By.id(""));
        	String href = link.getAttribute("href");
          int  statusCode = RestAssured.get(href).statusCode();

            if(200 != statusCode) {
                System.out.println(href + " gave a response code of " + statusCode);
                throw new Exception("Responce code not matching");
            }
			return statusCode;
            
	}

	public static boolean dashBoard()
	{
		boolean destination =GenericFucTions.verify(driver, properties.getProperty("destination"));
		return destination;
		
		}

	public static boolean SearchCabs() 
	{
		boolean cabtype =GenericFucTions.verify(driver, properties.getProperty("cabtype"));
		
		if(cabtype=true)
		{
			System.out.println(cabtype +"is displaying");
		}
		else
		{
			System.out.println(cabtype +"is not displaying");
		}
		return cabtype;
		
	}

	public static void getUserInfo() 
	{
		
	}

	public static boolean selectDestination() 
	{
		boolean flag=true;
		try{
		GenericFucTions.enterdata(driver, properties.getProperty("cabtype"), "NewCity");
		}
		catch(Exception e1)
		{
			flag=false;
		}
		return flag;
	}

	public static boolean getCabInforWithPrice()
	{
		boolean flag=true;
		try
		{
			
		String driverName =GenericFucTions.geText(driver, properties.getProperty("driverName"));
		hashdata.put("driverName", driverName);
		
		
		String cabType =GenericFucTions.geText(driver, properties.getProperty("cabType"));
		hashdata.put("driverName", cabType);
		
		String cabNumber =GenericFucTions.geText(driver, properties.getProperty("cabNumber"));
		hashdata.put("cabNumber", cabNumber);
		
		String CabphoneNumber =GenericFucTions.geText(driver, properties.getProperty("CabphoneNumber"));
		hashdata.put("cabNumber", CabphoneNumber);
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
		
		
	}

	public static void selectCab() {
		
		
	}

	public static void confirmBooking() {
		
		
	}

	public static void cabInformation() {
		
		
	}

	public static void canCel() {
		// TODO Auto-generated method stub
		
	}

	public static boolean completeRide() 
	{
		boolean flag=true;
		try
		{
		GenericFucTions.click(driver, properties.getProperty("completeRide"));
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
		
	}

	public static boolean payment() 
	{
		try{
		GenericFucTions.enterdata(driver, properties.getProperty("cardUserName"), "DbsUser");
		GenericFucTions.enterdata(driver, properties.getProperty("cardNumber"), "0109191");
		GenericFucTions.enterdata(driver, properties.getProperty("CVV"), "533");
		GenericFucTions.enterdata(driver, properties.getProperty("expdate"), "092019");
		GenericFucTions.click(driver, properties.getProperty("submit"));
		
		String sucessAlert=GenericFucTions.geText(driver, properties.getProperty("sucess"));
		if(sucessAlert.equals("payement sucessfull"))
		{
			System.out.println("card details are correct");
			return true;
		}
		else
		{
			System.out.println("card details are not correct");
			return false;
		}
		}
		catch(Exception e1)
		{
			
		}
		return false;
		
				
	}

}
