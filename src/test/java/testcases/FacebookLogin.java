package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.BaseTest;



public class FacebookLogin extends BaseTest
{
	private static Logger Log = LogManager.getLogger(FacebookLogin.class.getName());
   
	
	
	@Test
	public void login() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("FacebookLogin", "Login into Facebook Account");

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Resource\\confi.properties");
		Properties prop = new Properties();
		prop.load(fis);
		test.pass("Successfully loaded the properties file");
		String url = prop.getProperty("url");
		String unameID = prop.getProperty("unameID");
		String passwordID = prop.getProperty("passwordID");
		String submitName = prop.getProperty("submitName");
		String driverpath = prop.getProperty("path");
		System.setProperty("webdriver.chrome.driver", driverpath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println(driver.getCurrentUrl());
		System.out.println(url);
		if((driver.getCurrentUrl()).equals(url))
		{
			Log.info("Opened Url");
			test.pass("Successfully Opened url");
		}
		else
		{
			Log.error("Failed to open url");
			test.fail("Unable to open url");
		}
		FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir")+"\\Resource\\FacebookLogin.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis2);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(1);
		String uname = row.getCell(0).getStringCellValue();
		Log.info("Username taken from excel sheet and stored in uname");
		String pwd = row.getCell(1).getStringCellValue();
		Log.info("Password taken from excel sheet and stored in pwd");
		driver.findElement(By.id(unameID)).sendKeys(uname);
		Log.info("Username entered in username field");
		test.info("Username entered in username field");
		driver.findElement(By.id(passwordID)).sendKeys(pwd);
		Log.info("Password entered in the password field");
		test.info("Password entered in the password field");

		driver.findElement(By.name(submitName));
		String actual = driver.getCurrentUrl();
		String expected = url;
		
		if(actual.contains(expected))
		{
			test.pass("Successfully logged in");	
		}
		else
		{
        test.fail("Test failed");
        }
	}
	
	
	
}
