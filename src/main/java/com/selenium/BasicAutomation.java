package com.selenium.basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicAutomation {
    public static void main(String[] args) {
       // System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();  // ← fixed semicolon

        driver.get("https://www.wikipedia.org");
        System.out.println("Page Title: " + driver.getTitle());

        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        System.out.println("Results Page Title: " + driver.getTitle());

       driver.quit();
    }

}



