package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Setup {
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.porsche.com/usa/modelstart/");
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public static int randomNumber(int start, int end) {
        return start + (int) (Math.random() * (end - start + 1));
    }

    public static String expiryDate() {

        String month = randomNumber(1, 12) + "";
        String year = randomNumber(19, 23) + "";
        month = Integer.parseInt(month) < 10 ? "0" + month : month;
        return month + "/" + year;
    }

    public static String currentDate() {
        LocalDate localDate = LocalDate.now();
        return DateTimeFormatter.ofPattern("MM/dd/yyyy").format(localDate);

    }


}




