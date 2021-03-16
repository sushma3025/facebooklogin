package utilities;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest 
{
    public ExtentReports extent;

	
	@BeforeTest
	public void setReport()
	{
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);

	}
	
	@AfterTest
	public void end()
	{
		extent.flush();
		
	}
	


}
