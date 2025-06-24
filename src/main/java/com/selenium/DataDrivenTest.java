import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

        import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataDrivenTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org");
    }

    @Test
    public void searchTest() throws IOException {
        FileInputStream fis = new FileInputStream(new File("data.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // start from row 1 to skip header
            Row row = sheet.getRow(i);
            String keyword = row.getCell(0).getStringCellValue();

            WebElement searchBox = driver.findElement(By.name("search"));
            searchBox.clear();
            searchBox.sendKeys(keyword);
            searchBox.submit();

            try {
                Thread.sleep(2000); // wait for results to load
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Search for: " + keyword + " → " + driver.getTitle());
            driver.navigate().back();
        }

        workbook.close();
        fis.close();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
