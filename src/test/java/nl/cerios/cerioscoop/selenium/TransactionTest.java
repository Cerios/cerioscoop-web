package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import nl.cerios.cerioscoop.selenium.pages.CustomerPage;
import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.cerioscoop.selenium.pages.TransactionPage;
import nl.cerios.testutil.SeleniumTest;


public class TransactionTest extends SeleniumTest{
	
	@Test
	public void shouldLoginAndNavigateToTransactionPage(){
	CustomerPage customerPage = new IndexPage(getWebDriver()).loginToCustomerPage("C","C");
	 			
	 TransactionPage transactionPage = new CustomerPage(getWebDriver()).navigateToTransactions();
	
	 	Assert.assertNotNull(customerPage);
	 	Assert.assertNotNull(transactionPage);
	 	
	 	final String movieTitle = getWebDriver().findElement(By.id("movietitle")).getText().toString();
	 	final String roomName = getWebDriver().findElement(By.id("roomname")).getText().toString();
	 	final String showDate = getWebDriver().findElement(By.id("showdate")).getText().toString();
	 	final String showTime = getWebDriver().findElement(By.id("showtime")).getText().toString();
	 	final String reservedChairs = getWebDriver().findElement(By.id("reservedchairs")).getText().toString();
	 	
	    Assert.assertEquals("Wedding Crashers", movieTitle);
        Assert.assertEquals("blue room", roomName);
	    Assert.assertEquals("2016-09-20", showDate);
	    Assert.assertEquals("16:00:00", showTime);
	    Assert.assertEquals("3", reservedChairs);
	}
}
