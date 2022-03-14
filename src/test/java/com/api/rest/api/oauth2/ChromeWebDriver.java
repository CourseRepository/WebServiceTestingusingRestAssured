package com.api.rest.api.oauth2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriver {

	/***
	 * --headless -> a way to run the Chrome browser in a headless environment without the full browser UI 
	 * --disable-gpu -> Disable hardware acceleration
	 * --window-size=x,y -> open URL with a specific window size
	 */

	private static ChromeOptions getChromerOptions() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		ChromeOptions option = new ChromeOptions();
		option.addArguments(Arrays.asList("--headless", "--disable-gpu",
				String.format("--window-size=%d,%d", (int) size.getWidth(), (int) size.getHeight())));
		option.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		return option;

	}

	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\RATHR1\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(getChromerOptions());
		return driver;
	}

}
