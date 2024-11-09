package NewTest.STTproject;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllAutomation {
    
    public static void main(String[] args) throws InterruptedException, InvalidFormatException, IOException {
        WebDriverManager.chromedriver().setup();        
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.blazedemo.com/");
        driver.manage().window().maximize();
        
        //first website
        File srcfile = new File("C:\\Users\\karth\\eclipse-workspace\\STTproject\\target\\GlobalSQA_DataDrivenTesting.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(srcfile);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
       
        int col = sheet.getRow(0).getLastCellNum();
        System.out.println(col);
        

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            System.out.println("Processing row of TESTng: " + i);

            String Control = getCellValueAsString(sheet.getRow(i).getCell(0));
            String Departure = getCellValueAsString(sheet.getRow(i).getCell(1));
            String Destination = getCellValueAsString(sheet.getRow(i).getCell(2));
            String Flight = getCellValueAsString(sheet.getRow(i).getCell(3));
            String Airline = getCellValueAsString(sheet.getRow(i).getCell(4));
            String Name = getCellValueAsString(sheet.getRow(i).getCell(5));
            String Address = getCellValueAsString(sheet.getRow(i).getCell(6));
            String City = getCellValueAsString(sheet.getRow(i).getCell(7));
            String State = getCellValueAsString(sheet.getRow(i).getCell(8));
            String Zip_Code = getCellValueAsString(sheet.getRow(i).getCell(9));
            String Card_Type = getCellValueAsString(sheet.getRow(i).getCell(10));
            String Credit_Card_Number = getCellValueAsString(sheet.getRow(i).getCell(11));
            String Month = getCellValueAsString(sheet.getRow(i).getCell(12));
            String Year = getCellValueAsString(sheet.getRow(i).getCell(13));
            String NameonCard = getCellValueAsString(sheet.getRow(i).getCell(14));

            System.out.println("Control: " + Control);

            if (Control.equals("Yes")) {
            	//dropdown
                driver.findElement(By.xpath("/html/body/div[3]/form/select[1]")).click();
                driver.findElement(By.xpath("//select[@name='fromPort']//option[text()='" + Departure + "']")).click();
                //destination
                driver.findElement(By.xpath("/html/body/div[3]/form/select[2]")).click();
                driver.findElement(By.xpath("//select[@name='toPort']//option[text()='" + Destination + "']")).click();
                //next
                driver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();

                // flight and airline on table
                boolean found = false;
                for (int rowIdx = 1; rowIdx <= 5; rowIdx++) {
                    String flightXpath = "/html/body/div[2]/table/tbody/tr[" + rowIdx + "]/td[2]";
                    String flightValue = driver.findElement(By.xpath(flightXpath)).getText();
                    String airlineXpath = "/html/body/div[2]/table/tbody/tr[" + rowIdx + "]/td[3]";
                    String airlineValue = driver.findElement(By.xpath(airlineXpath)).getText();
                    String chooseButtonXpath = "/html/body/div[2]/table/tbody/tr[" + rowIdx + "]/td[1]/input";

                    if (flightValue.equals(Flight) && airlineValue.equals(Airline)) {
                        driver.findElement(By.xpath(chooseButtonXpath)).click();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Flight and Airline not found for: " + Flight + " - " + Airline);
                }

                // form
                driver.findElement(By.xpath("//*[@id=\"inputName\"]")).sendKeys(Name);
                driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys(Address);
                driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(City);
                driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys(State);
                driver.findElement(By.xpath("//*[@id=\"zipCode\"]")).sendKeys(Zip_Code);
                driver.findElement(By.xpath("//*[@id=\"cardType\"]")).click();
                driver.findElement(By.xpath("//select[@id='cardType']//option[text()='" + Card_Type + "']")).click();
                driver.findElement(By.xpath("//*[@id=\"creditCardNumber\"]")).sendKeys(Credit_Card_Number);
                driver.findElement(By.xpath("//*[@id=\"creditCardMonth\"]")).sendKeys(Month);
                driver.findElement(By.xpath("//*[@id=\"creditCardYear\"]")).sendKeys(Year);
                driver.findElement(By.xpath("//*[@id=\"nameOnCard\"]")).sendKeys(NameonCard);

                // Submitting the form
                driver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();

                // back
                driver.findElement(By.xpath("/html/body/div[1]/div/div/a[2]")).click();
            }
        }

        // Close the first driver
        driver.quit();

        // Setup for the second website
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream"); // Automatically allow camera access
        options.addArguments("--allow-insecure-localhost"); // Allow insecure localhost
        driver = new ChromeDriver(options);
        driver.get("https://demo.wpeverest.com/user-registration/profile-registration-form/");
        driver.manage().window().maximize();
        
        

       
        File srcfile2 = new File("C:\\Users\\karth\\eclipse-workspace\\STTproject\\target\\2ndWebsite.xlsx");
        XSSFWorkbook workbook2 = new XSSFWorkbook(srcfile2);
        XSSFSheet sheet2 = workbook2.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            System.out.println("Processing row: " + i);

            String Control = getCellValueAsString1(sheet2.getRow(i).getCell(1));
            String FirstName = getCellValueAsString1(sheet2.getRow(i).getCell(2));
            String LastName = getCellValueAsString1(sheet2.getRow(i).getCell(3));
            String DisplayName = getCellValueAsString1(sheet2.getRow(i).getCell(4));
            String PhotoOption = getCellValueAsString1(sheet2.getRow(i).getCell(5)); // "t" for take a photo or "u" for upload
            String PhotoPath = getCellValueAsString1(sheet2.getRow(i).getCell(6)); // File path for upload
            String UserEmail = getCellValueAsString1(sheet2.getRow(i).getCell(7));
            String Username = getCellValueAsString1(sheet2.getRow(i).getCell(8));
            String UserPassword = getCellValueAsString1(sheet2.getRow(i).getCell(9));
            if(Control.equals("y"))
            {
                if (PhotoOption.equals("t")) {
                    driver.findElement(By.xpath("//*[@id=\"user-registration-form-329\"]/form/div[1]/div/div/div/button[2]")).click();
                    Thread.sleep(1000); 
                    driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/button[1]")).click(); 
                    driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/button[1]")).click();
                } else if (PhotoOption.equals("u")) {
                    WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']")); // Adjust this XPath if necessary
                    uploadElement.sendKeys(PhotoPath); 
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
                driver.navigate().refresh();
            }
           
        }

        // Close the workbook and driver
        driver.close();
        workbook.close();
        workbook2.close();
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue()); // Cast to int to avoid decimal values
            default:
                return "";
        }
    }
    
    private static String getCellValueAsString1(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }
        return cell.toString();
    }
}



//if (Gender.equalsIgnoreCase("Male")) {
//
//    driver2.findElement(By.xpath("//*[@id=\"radio_1569440596_Male\"]")).click();
//
//} else if (Gender.equalsIgnoreCase("Female")) {
//
//    driver2.findElement(By.xpath("//*[@id=\"radio_1569440596_Female\"]")).click();
//
//} else if (Gender.equalsIgnoreCase("Other")) {
//
//    driver2.findElement(By.xpath("//*[@id=\"radio_1569440596_Other\"]")).click();
//
//}