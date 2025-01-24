package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.yandex.RegisterPage;
import static org.junit.Assert.assertEquals;

public class RegisterErrorTest extends BaseUITest{

    @Test
    @DisplayName("Impossible to Register with short password")
    public void impossibleToRegisterWithShortPassword() throws InterruptedException {
        Allure.description("Password shouldn't be less than 6 symbols");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();

        registerPage.setName(RandomStringUtils.randomAlphabetic(4));
        registerPage.setEmail(RandomStringUtils.randomAlphabetic(4));
        registerPage.setPassword(RandomStringUtils.randomAlphabetic(4));
        registerPage.clickRegisterButton();

        String expectedError = "Некорректный пароль";
        assertEquals(registerPage.getRegistrationError(), expectedError);
    }
}
