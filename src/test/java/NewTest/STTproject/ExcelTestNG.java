package NewTest.STTproject;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelTestNG {
    WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    @BeforeTest
    public void beforeTest() throws InvalidFormatException, IOException {
        // Set up WebDriver and open browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.blazedemo.com/");
        driver.manage().window().maximize();

        // Load the Excel file
        File srcfile = new File("C:\\Users\\karth\\eclipse-workspace\\STTproject\\target\\GlobalSQA_DataDrivenTesting.xlsx");
        workbook = new XSSFWorkbook(srcfile);
        sheet = workbook.getSheetAt(0);
    }

    @Test
    public void f() {
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
                driver.findElement(By.xpath("/html/body/div[3]/form/select[1]")).click();
                driver.findElement(By.xpath("//select[@name='fromPort']//option[text()='" + Departure + "']")).click();
                driver.findElement(By.xpath("/html/body/div[3]/form/select[2]")).click();
                driver.findElement(By.xpath("//select[@name='toPort']//option[text()='" + Destination + "']")).click();
                driver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();

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

                // Fill the form
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

                // Submit the form
                driver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();

                // Go back to home
                driver.findElement(By.xpath("/html/body/div[1]/div/div/a[2]")).click();
            }
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        // Close workbook and browser after test
        workbook.close();
        driver.close();
    }

    // Helper method to get the cell value as a string, handling numeric cells
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
}
