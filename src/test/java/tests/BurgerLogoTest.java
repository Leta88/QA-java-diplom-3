package tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.ConstructorPage;
import ru.yandex.LoginPage;
import ru.yandex.PasswordRecoveryPage;
import ru.yandex.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static ru.yandex.Constants.WAIT_DURATION;

public class BurgerLogoTest extends BaseUITest{


    @Test
    @DisplayName("Check clicking Logo forwards to the construction page from the register page")
    public void checkLogoForwardsToConstructorPageFromRegisterTest(){

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.clickLogo();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Check clicking Logo forwards to the construction page from the login page")
    public void checkLogoForwardsToConstructorPageFromLoginTest(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.clickLogo();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Check clicking Logo forwards to the construction page from the password recovery page")
    public void checkLogoForwardsToConstructorPageFromPasswordRecoveryTest(){

        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.openPasswordRecoveryPage();
        passwordRecoveryPage.clickLogo();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }
}
