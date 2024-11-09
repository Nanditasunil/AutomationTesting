package NewTest.STTproject;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SweetShop {

    public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException {
        // Setup ChromeDriver with options
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream"); // Automatically allow camera access
        options.addArguments("--allow-insecure-localhost"); // Allow insecure localhost
        
        WebDriver driver = new ChromeDriver(options); // Create ChromeDriver with options

        driver.get("https://demo.wpeverest.com/user-registration/profile-registration-form/");
        driver.manage().window().maximize();
        
        File srcfile = new File("C:\\Users\\karth\\eclipse-workspace\\STTproject\\target\\2ndWebsite.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(srcfile);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Loop through rows starting from 1 to skip the header
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            System.out.println("Processing row: " + i);

            // Retrieve values from the Excel sheet
            String Control = getCellValueAsString(sheet.getRow(i).getCell(1));
            String FirstName = getCellValueAsString(sheet.getRow(i).getCell(2));
            String LastName = getCellValueAsString(sheet.getRow(i).getCell(3));
            String DisplayName = getCellValueAsString(sheet.getRow(i).getCell(4));
            String PhotoOption = getCellValueAsString(sheet.getRow(i).getCell(5)); // "t" for take a photo or "u" for upload
            String PhotoPath = getCellValueAsString(sheet.getRow(i).getCell(6)); // File path for upload
            String UserEmail = getCellValueAsString(sheet.getRow(i).getCell(7));
            String Username = getCellValueAsString(sheet.getRow(i).getCell(8));
            String UserPassword = getCellValueAsString(sheet.getRow(i).getCell(9));
            if(Control.equals("y"))
            {
            	 // Handle photo logic first
                if (PhotoOption.equals("t")) {
                	// Clicking the "Take a Photo" button
                	driver.findElement(By.xpath("//*[@id=\"user-registration-form-329\"]/form/div[1]/div/div/div/button[2]")).click();
                	Thread.sleep(1000); // Wait for the permission dialog to appear (you can adjust this)
                	driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/button[1]")).click(); // Click "Allow"
                	driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/button[1]")).click();
            
                } else if (PhotoOption.equals("u")) {
                    WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']")); // Adjust this XPath if necessary
                    uploadElement.sendKeys(PhotoPath); // Upload the file
                    driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/button[1]")).click();
                }

                // Wait briefly before filling in the form
                Thread.sleep(2000);

                // Filling the form fields
                driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys(FirstName); // First Name
                driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys(LastName); // Last Name
                driver.findElement(By.xpath("//*[@id=\"display_name\"]")).sendKeys(DisplayName); // Display Name
                driver.findElement(By.xpath("//*[@id=\"user_email\"]")).sendKeys(UserEmail); // User Email
                driver.findElement(By.xpath("//*[@id=\"user_login\"]")).sendKeys(Username); // Username
                driver.findElement(By.xpath("//*[@id=\"user_pass\"]")).sendKeys(UserPassword); // User Password
                driver.findElement(By.xpath("//*[@id=\"user-registration-form-329\"]/form/div[3]/button")).click(); // Submit

                // Optional: Wait for a few seconds before processing the next row
                Thread.sleep(1000);
            }
           
        }

        // Close the workbook and driver
        workbook.close();
        driver.quit();
    }

    // Helper method to get cell value as string
    private static String getCellValueAsString(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }
        return cell.toString();
    }
}








