package testtatoc;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TatocTest extends TatocMain {

	@Test
	public void page1() {
		basicCourse();
		Assert.assertEquals("Grid Gate - Basic Course - T.A.T.O.C", driver.getTitle());
	}
	
	@Test(dependsOnMethods = {"page1"})
	public void page2() {
		greenBox();
		Assert.assertEquals("Frame Dungeon - Basic Course - T.A.T.O.C", driver.getTitle());
	}
	@Test(dependsOnMethods = {"page2"})
	public void page3() {
		boxColor();
		Assert.assertEquals("Drag - Basic Course - T.A.T.O.C", driver.getTitle());
	}
	
	@Test(dependsOnMethods = {"page3"})
	public void page4() {
		dragAndDrop();
		Assert.assertEquals("Windows - Basic Course - T.A.T.O.C", driver.getTitle());
	}
	
	@Test(dependsOnMethods = {"page4"})
	public void page5() {
		popupWindow();
		Assert.assertEquals("Cookie Handling - Basic Course - T.A.T.O.C", driver.getTitle());
	}
	
	@Test(dependsOnMethods = {"page5"})
	public void page6() {
		cookieGenerate();
		Assert.assertEquals("End - T.A.T.O.C", driver.getTitle());
	}


}
