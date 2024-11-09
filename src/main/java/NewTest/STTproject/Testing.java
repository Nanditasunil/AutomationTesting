package NewTest.STTproject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testing {
	public static void main(String[] args)
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://sweetshop.netlify.app/");
		driver.manage().window().maximize();
		
		//going to login page
		driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[3]/a")).click();
		driver.manage().window().setSize(new Dimension(400, 300));
		driver.findElement(By.xpath("/html/body/nav/div/button/span")).click();
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[3]/a")).click();
		
		//logging in
		driver.findElement(By.xpath("//*[@id=\"exampleInputEmail\"]")).sendKeys("k@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"exampleInputPassword\"]")).sendKeys("123456879");
		driver.findElement(By.xpath("//*[@id=\"btn_s01llaq32\"]")).click();
		
		//sorting 
		driver.findElement(By.xpath("//*[@id=\"transactions\"]/thead/tr/th[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"transactions\"]/thead/tr/th[1]/a")).click();
		
		//sorting the order total
		driver.findElement(By.xpath("//*[@id=\"transactions\"]/thead/tr/th[4]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"transactions\"]/thead/tr/th[4]/a")).click();
		
	}
}
