package com.selenium.basic;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Set;

public class AdvancedAutomation {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");

        driver.switchTo().frame("iframeResult");

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.id("cars")));
        dropdown.selectByVisibleText("Audi");

        // Alert (manually triggered)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('This is a test alert');");
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // New Tab
        js.executeScript("window.open('https://www.wikipedia.org')");
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }

        // Waits
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));

        driver.quit();
    }
}
