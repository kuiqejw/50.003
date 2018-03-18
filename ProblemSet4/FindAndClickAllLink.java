package week6;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindAndClickAllLink {

    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("webdriver.gecko.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2018/Test/geckodriver");
        //WebDriver driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "/home/ongajong/Documents/Week 6/chromedriver");
        WebDriver driver = new ChromeDriver();

        //driver.get("https://sudiptac.bitbucket.io");
        driver.get("https://istd.sutd.edu.sg/");
        //driver.get("https://www.google.com.sg");

        // get all the links
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        // print all the links
//		for (int i = 0; i < 100; i=i+1) {
//			System.out.println(i + " " + links.get(i).getText());
//			System.out.println(i + " " + links.get(i).getAttribute("href"));
//		}
//		
        driver.manage().window().maximize();

        // click all links in a web page
        //for (int i = 0; i < links.size(); i++)
        while(true) {
            Random r = new Random();
            int showMe = 1 + r.nextInt(links.size());
            System.out.println("*** Navigating to" + " " + links.get(showMe).getAttribute("href"));
            if (links.get(showMe).getAttribute("href") == null) {
                continue;
            }
            boolean staleElementLoaded = true;
            while (staleElementLoaded) {
                try {
                    driver.navigate().to(links.get(showMe).getAttribute("href"));
                    Thread.sleep(3000);
                    driver.navigate().back();
                    links = driver.findElements(By.tagName("a"));
                    System.out.println("*** Navigated to" + " " + links.get(showMe).getAttribute("href"));
                    staleElementLoaded = false;
                } catch (StaleElementReferenceException e) {
                    staleElementLoaded = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
