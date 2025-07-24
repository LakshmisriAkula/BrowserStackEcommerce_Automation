package com.bstack.extentreportlistener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.bstack.base.TestBase;

public class ExtentTestListener implements ITestListener, ISuiteListener {

	private static ExtentReports extent;
	private static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

		// Add System Info
		String browser = System.getProperty("browser", "chrome"); // default to chrome
		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("Suite", suite.getName());
	}

	@Override
	public void onFinish(ISuite suite) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		com.bstack.base.TestBase.logger = test;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (test != null) {
			test.fail("Test failed: " + result.getThrowable());

			WebDriver driver = TestBase.driver;
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String filePath = "screenshots/" + result.getName() + "_" + timestamp + ".png";

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(filePath);
			dest.getParentFile().mkdirs();

			try {
				org.openqa.selenium.io.FileHandler.copy(src, dest);
				test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest.getAbsolutePath()).build());
			} catch (IOException e) {
				test.fail("Screenshot error: " + e.getMessage());
			}
		} else {
			System.err.println("ExtentTest is null in onTestFailure for: " + result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (test != null) {
			test.skip("Test skipped");
		} else {
			System.err.println("ExtentTest is null in onTestSkipped for: " + result.getMethod().getMethodName());
		}
	}

}