package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static ru.yandex.Constants.BASE_URL;

public class PasswordRecoveryPage extends BasePage{

    private final By LogInButton = By.xpath("//a[@href='/login']");
    public static final String PASSWORD_RECOVERY_PAGE_URL = BASE_URL + "forgot-password";

    public PasswordRecoveryPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Open password recovery page")
    public PasswordRecoveryPage openPasswordRecoveryPage(){
        driver.get(PASSWORD_RECOVERY_PAGE_URL);
        return this;
    }

    @Step("Switch back to the login")
    public void switchToLoginButton() {
        clickElement(LogInButton);
    }
}
