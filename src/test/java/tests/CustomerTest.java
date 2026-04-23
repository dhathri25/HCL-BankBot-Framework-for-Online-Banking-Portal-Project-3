package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NewCustomerPage;

public class CustomerTest extends BaseTest {

	@Test
	public void testCreateCustomer() {

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login("mngr658981", "avEgYzU");
	    

	    NewCustomerPage customerPage = new NewCustomerPage(driver);
	    customerPage.clickNewCustomer();

	    String email = "test" + System.currentTimeMillis() + "@gmail.com";

	    customerPage.createCustomer(
	            "Dhathri",
	            "01011880",
	            "Hyderabad",
	            "Hyderabad",
	            "Telangana",
	            "500031",
	            "98786340367",
	            email,
	            "password123"
	    );

	    String msg = customerPage.getSuccessMessage();
	    Assert.assertTrue(msg.contains("Customer Registered Successfully"));

	    // ✅ Capture ID
	    String customerId = customerPage.getCustomerId();
	    System.out.println("Customer ID: " + customerId);

	    // ✅ Store globally
	    System.setProperty("custId", customerId);
	}
}