package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import nl.cerios.cerioscoop.selenium.pages.BuyTicketsPage;
import nl.cerios.cerioscoop.selenium.pages.CustomerPage;
import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.cerioscoop.selenium.pages.PaymentTicketsPage;
import nl.cerios.testutil.SeleniumTest;

public class BuyTicketsWithPageObjectModelTest extends SeleniumTest {
	private CustomerPage customerPage;
	private IndexPage homePage;
	private BuyTicketsPage buyTicketsPage;
	private PaymentTicketsPage paymentTicketsPage;
	
	@Test
	public void buyTicketsTest() throws InterruptedException{
		//Happy flow
		customerPage = new IndexPage(getWebDriver()).loginToCustomerPage("C","C");
		homePage = new IndexPage(getWebDriver());
		buyTicketsPage = homePage.navigateToBuyTicketsPage("6");
		buyTicketsPage.enterNumberOfTickets("3");
		paymentTicketsPage = buyTicketsPage.clickOnTheBuyButton();
		Assert.assertNotNull(paymentTicketsPage);
		Assert.assertEquals("Payment tickets", getWebDriver().getTitle());		
		getWebDriver().findElement(By.id("navbar-logout")).click();
		
		//Er zit een maximum aan het aantal tickets dat kan worden gekocht
		customerPage = new IndexPage(getWebDriver()).loginToCustomerPage("C","C");
		homePage = new IndexPage(getWebDriver());
		buyTicketsPage = homePage.navigateToBuyTicketsPage("6");
		buyTicketsPage.enterNumberOfTickets("55");
		paymentTicketsPage = buyTicketsPage.clickOnTheBuyButton();
		Assert.assertFalse(getWebDriver().getPageSource().contains("Maximum number of tickets exceeded!"));
		getWebDriver().findElement(By.id("navbar-logout")).click();
		
		//Minimaal één ticket moet worden gekocht
		customerPage = new IndexPage(getWebDriver()).loginToCustomerPage("C","C");
		homePage = new IndexPage(getWebDriver());
		buyTicketsPage = homePage.navigateToBuyTicketsPage("6");
		buyTicketsPage.enterNumberOfTickets("0");
		paymentTicketsPage = buyTicketsPage.clickOnTheBuyButton();
		Assert.assertFalse(getWebDriver().getPageSource().contains("You have to buy at least one ticket!"));
		getWebDriver().findElement(By.id("navbar-logout")).click();
		
		//Het aantal tickets mag het aantal beschikbare plaatsen niet overschrijden
		customerPage = new IndexPage(getWebDriver()).loginToCustomerPage("C","C");
		homePage = new IndexPage(getWebDriver());
		buyTicketsPage = homePage.navigateToBuyTicketsPage("6");
		buyTicketsPage.enterNumberOfTickets("51");
		paymentTicketsPage = buyTicketsPage.clickOnTheBuyButton();
		//TODO > Assert.assertFalse(paymentTicketsPage.getPageTitle().contains("There are not so many places available!"));
		Assert.assertFalse(getWebDriver().getPageSource().contains("There are not so many places available!"));
		getWebDriver().findElement(By.id("navbar-logout")).click();
	} 
}
