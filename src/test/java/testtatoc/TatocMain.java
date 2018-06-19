package testtatoc;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TatocMain {
	WebDriver driver;
	
	public void basicCourse() {
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.linkText("Basic Course")).click();
	}
	
	public void greenBox() {
		driver.findElement(By.className("greenbox")).click();
	}
	
	public void boxColor() {
		driver.switchTo().frame(driver.findElement(By.id("main")));
		String color = driver.findElement(By.xpath("//*[@id='answer']")).getAttribute("class");
		String color1;
		do {
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#child")));
		    color1 = driver.findElement(By.xpath("//div[@id='answer']")).getAttribute("class");
			driver.switchTo().parentFrame();
			if(color.equals(color1)) {
				driver.findElement(By.linkText("Proceed")).click();
				break;
			}
			else driver.findElement(By.linkText("Repaint Box 2")).click();
		}
		while(!color.equals(color1));
	}
	
	public void dragAndDrop() {
		WebElement fromWebElement = driver.findElement(By.xpath("//*[@id='dragbox']"));
		WebElement toWebElement = driver.findElement(By.xpath("//*[@id='dropbox']"));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(fromWebElement, toWebElement).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
	}
	
	public void popupWindow() {
		String parentWindowHandler = driver.getWindowHandle(); 
		String subWindowHandler = null;
		driver.findElement(By.linkText("Launch Popup Window")).click();
		Set<String> handles = driver.getWindowHandles(); 
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next().toString();
		    if(!subWindowHandler.contains(parentWindowHandler)) {
		    driver.switchTo().window(subWindowHandler);
			driver.findElement(By.xpath("//*[@id='name']")).sendKeys("Hello");
			driver.findElement(By.xpath("//*[@id='submit']")).click();
		    }
		}
		driver.switchTo().window(parentWindowHandler); 
		driver.findElement(By.linkText("Proceed")).click();
	}
	
	public void cookieGenerate() {
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/a[1]")).click();
		String token = driver.findElement(By.cssSelector("span#token")).getText();
		String tokenValue = token.substring(7);
		Cookie cookie = new Cookie("Token", tokenValue);
		driver.manage().addCookie(cookie);
		driver.findElement(By.xpath("/html/body/div/div[2]/a[2]")).click();
	}
}
