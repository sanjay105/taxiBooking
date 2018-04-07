package dbsBookCab;

import java.io.File;
import java.net.URL;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.RowSetProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCabService extends DBSCabBase1
{
	
	public TestCabService() throws Throwable {
		super();
		
	}

	public static ExtentReports report;
	public static ExtentTest reportData;
	
	public static	int pagePassCount=0;
	public static int pageFailCount=0;
	public static int pageSkipCount=0;
	public static int pageStepsCount=0;
	public static String pageLevelReportpath="";
	public static String pagereportname="";
	
	@BeforeClass
	public void ReportsStarts()
	{
	System.out.println("@BeforeClass Test");
	SimpleDateFormat extentReportPath=new SimpleDateFormat("HHmmss");
	String extentReportname=extentReportPath.format(new Date());
	System.out.println(extentReportname);
	pagereportname = this.getClass().getSimpleName();
	System.out.println("className "+pagereportname);
	pageLevelReportpath=System.getProperty("user.dir")+"\\PageReports\\"+pagereportname+"_"+"HtmlReports"+extentReportname+".html";
	 report=new ExtentReports(pageLevelReportpath);
	 System.out.println("report name "+report);
	 	report.config().documentTitle("Younique");
		report.config().reportName("Younique Regression Report");
		report.config().reportHeadline("-"+pagereportname);
	
	}
	   @BeforeTest 
		public void FirstStep() throws Throwable 
		{
			
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apps/Amazon/");
			File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", "Micromax A311");
			capabilities.setCapability("platformVersion", "4.4.2");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

//			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.quit();
		}
	 
	 @Test
	 public void CabServetest() throws Throwable
	 {
 reportData=report.startTest("CabService");
 
 		if(DBSCabBase1.signUpWithGoogle()==true)
 			{
 			reportData.log(LogStatus.PASS, "signUpWithGoogle");
 			}
 		 else
 		{
 			reportData.log(LogStatus.FAIL, "signUpWithGoogle");
 		}
 		
 		
 		if(DBSCabBase1.NewUserProfile()==true)
			{
			reportData.log(LogStatus.PASS, "NewUserProfile");
			}
		else
		{
			reportData.log(LogStatus.FAIL, "NewUserProfile");
		}
 		
 		
 		if(DBSCabBase1.ResponceCode()==200)
		{
		reportData.log(LogStatus.PASS, "ResponceCode");
		}
	else
	{
		reportData.log(LogStatus.FAIL, "ResponceCode");
	}
 		

 		if(DBSCabBase1.dashBoard()==true)
		{
		reportData.log(LogStatus.PASS, "dashBoardGoogle");
		}
	else
	{
		reportData.log(LogStatus.FAIL, "dashBoardGoogle");
	}
		 
 		if(DBSCabBase1.SearchCabs()==true)
		{
		reportData.log(LogStatus.PASS, "SearchCabs");
		}
 		else
 			{
		reportData.log(LogStatus.FAIL, "SearchCabs");
 			} 
 		
 		if(DBSCabBase1.selectDestination()==true)
		{
		reportData.log(LogStatus.PASS, "selectDestination");
		}
 		else
 			{
		reportData.log(LogStatus.FAIL, "selectDestination");
 			} 
		 
 		if(DBSCabBase1.getCabInforWithPrice()==true)
		{
		reportData.log(LogStatus.PASS, "getCabInforWithPrice");
		}
 		else
 			{
		reportData.log(LogStatus.FAIL, "getCabInforWithPrice");
 			}
 		
 		if(DBSCabBase1.getCabInforWithPrice()==true)
		{
		reportData.log(LogStatus.PASS, "getCabInforWithPrice");
		}
 		else
 			{
		reportData.log(LogStatus.FAIL, "getCabInforWithPrice");
 			}
		 
 		if(DBSCabBase1.payment()==true)
		{
		reportData.log(LogStatus.PASS, "payment");
		}
 		else
 			{
		reportData.log(LogStatus.FAIL, "payment");
 			}
		
	
		
		 
		 report.endTest(reportData);
		 
		 }
	 
	 @AfterTest
	 public void AfterTest()
	 {
		 report.flush();
		 report.close();
	 }
}
