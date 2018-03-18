
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitGoogleLogin {

    static String myUserName = "lauraong1@yahoo.com.sg";
    static String myPassword = "wsadwsad12";
    //can only use letters numbers and periods
    static String[] invalidUsername = {"Laura Ong", "Liu!Bei", "su!rly_as", "Guo J_ia", "12345", "@sutd", "laura->ong",
                                    "Sima_Yi.123", "..", "Hello Mojo", "!@#$%^&*()", ":) | (:", "' ¯\\_(ツ)_/¯",
                                        "( ͡° ͜ʖ ͡°)","(ᵔᴥᵔ)"," (=^ェ^=)"," ʕ •ᴥ•ʔ"," （・⊝・", "┻━┻ ┻━┻", ""};

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/ongajong/Documents/Week 6/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://gmail.com/");

        // get all the links
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        // get the user name field of the account page
        WebElement username = driver.findElement(By.name("identifier"));
     
        // send my user name to fill up the box
        int i = 0;
        while (i < invalidUsername.length){
            System.out.println(invalidUsername[i]);
            username.sendKeys(invalidUsername[i]);
            WebElement nextButton = driver.findElement(By.id("identifierNext"));
            nextButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // wait only until the password element becomes visible
            i++;
            username.clear();
        }

        // locate the "Next" button in the account page
        username.sendKeys(myUserName);
        WebElement nextButton = driver.findElement(By.id("identifierNext"));
        nextButton.click();

        //explicitly wait until the password field is present in the page
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // wait only until the password element becomes visible
            wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            // now locate the password field in the current page
            WebElement password = driver.findElement(By.name("password"));
            // send password 
            password.sendKeys(myPassword);
            // login and :)
            nextButton = driver.findElement(By.id("passwordNext"));
            nextButton.click();
        } catch (Exception NoSuchElementException) {
            System.out.println("login name invalid");
        }
    }
}
