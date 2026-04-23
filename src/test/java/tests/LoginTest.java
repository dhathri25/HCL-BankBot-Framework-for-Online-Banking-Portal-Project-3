package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    ConfigReader config = new ConfigReader();

    // ✅ DataProvider (MANDATORY)
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {config.getUsername(), config.getPassword(), true},
            {"wrongUser", "wrongPass", false}
        };
    }
    

    // ✅ Combined login test (valid + invalid)
    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean isValid) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (isValid) {
            Assert.assertTrue(driver.getTitle().contains("Guru99 Bank"),
                    "Valid login failed");
        } else {
            Alert alert = loginPage.waitForAlert();
            Assert.assertTrue(alert.getText().toLowerCase().contains("not valid"));
            alert.accept();
        }
    }

    // ✅ Screenshot demo (NO Thread.sleep)
    @Test
    public void testScreenshotDemo() {

        LoginPage loginPage = new LoginPage(driver);

        // Fill invalid data
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPass");

        // ✅ Proper wait instead of sleep
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

        // ❗ Fail to trigger screenshot
        Assert.fail("Screenshot with filled invalid credentials");
    }

    // ✅ Invalid login with alert (for report)
    @Test
    public void testInvalidLoginAlert() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrongUser", "wrongPass");

        Alert alert = loginPage.waitForAlert();

        String alertText = alert.getText();
        Assert.assertTrue(alertText.toLowerCase().contains("not valid"));

        // Accept alert
        alert.accept();
    }
}