package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static ru.yandex.Constants.BASE_URL;

public class LoginPage extends BasePage{

    private final By EmailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By PasswordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By LoginButton = By.xpath("//button[text()='Войти']");
    public static final String LOGIN_PAGE_URL = BASE_URL + "login";

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Open login page")
    public LoginPage openLoginPage(){
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    @Step("Filling in Email")
    public void setEmail(String email) throws InterruptedException {
        setTextField(EmailField, email);
    }

    @Step("Filling in password")
    public void setPassword(String password) throws InterruptedException {
        setTextField(PasswordField, password);
    }

    @Step("Click on Login button")
    public void clickLoginButton() {
        clickElement(LoginButton);
    }
}
