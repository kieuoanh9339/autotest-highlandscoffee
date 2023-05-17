package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAuth {

    ChromeDriver chrome;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.get("https://shop.highlandscoffee.com.vn/");
        chrome.manage().window().maximize();
        chrome.findElement(By.xpath("/html/body/header/div[1]/div/div/div[3]/a[2]")).click();
        waitForSecond(3);
    }

    @Test
    public void signWithErrorPassword() {
        chrome.findElement(By.xpath("//*[@id=\"customer_email\"]")).sendKeys("comtrang7@gmail.com");
        chrome.findElement(By.xpath("//*[@id=\"customer_password\"]")).sendKeys("comtrang1");

        waitForSecond(2);

        chrome.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/div[2]/button")).click();

        waitForSecond(2);
        Assert.assertTrue(chrome.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]")).isDisplayed());

    }

    @Test
    public void signWithCorrect() {
        chrome.findElement(By.xpath("//*[@id=\"customer_email\"]")).sendKeys("comtrang7@gmail.com");
        chrome.findElement(By.xpath("//*[@id=\"customer_password\"]")).sendKeys("comtrang");

        waitForSecond(2);

        chrome.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/div[2]/button")).click();

        waitForSecond(5);
        Assert.assertTrue(chrome.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div/h5")).isDisplayed());

    }


    @AfterMethod
    public void clear() {
        chrome.quit();
    }

    public void wait(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForSecond(int seconds) {
        wait(Duration.ofSeconds(seconds));
    }
}
