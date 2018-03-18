/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week6;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ongajong
 */
public class GetTitle {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/ongajong/Documents/Week 6/chromedriver");

        WebDriver driver = new ChromeDriver();
        System.out.println(driver.getTitle());
        driver.get("http://blank.org/");

        // get all the links
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());
        // click all links in a web page
        for (int i = 0; i < links.size(); i++) {
            System.out.println("*** Navigating to" + " " + links.get(i).getAttribute("href"));
            if (links.get(i).getAttribute("href") == null) {
                continue;
            }
            boolean staleElementLoaded = true;
            while (staleElementLoaded) {
                try {
                    driver.navigate().to(links.get(i).getAttribute("href"));
                    Thread.sleep(3000);
                    System.out.println(driver.getTitle());
                    //Cohort Exercise 9
                    if (driver.getTitle().isEmpty()){
                        System.out.println("Test Fails");
                        break;
                    }
                    driver.navigate().back();
                    links = driver.findElements(By.tagName("a"));
                    System.out.println("*** Navigated to" + " " + links.get(i).getAttribute("href"));
                    staleElementLoaded = false;
                } catch (StaleElementReferenceException e) {
                    staleElementLoaded = true;
                }
            }
        }
    }
//		
//		// get all the links
//		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
//		System.out.println(links.size());
//				
//		System.out.println("***Printing all link Titles***");
//		// print all the links
//		for (int i = 0; i < links.size(); i=i+1) {
//			System.out.println(i + " " + links.get(i).getTitle());
//		}	
//		System.out.println("***Prining all link addresses***");
//		// print all the hyper links
//		for (int i = 0; i < links.size(); i=i+1) {
//			System.out.println(i + " " + links.get(i).getAttribute("href"));
//		}		

}
