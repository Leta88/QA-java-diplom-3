package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static ru.yandex.Constants.BASE_URL;
import static ru.yandex.Constants.WAIT_DURATION;

public class RegisterPage extends BasePage {

    private final String REGISTER_PAGE_URL = BASE_URL + "register";
    private final By NameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By EmailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By PasswordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By RegisterButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By RegisterError = By.xpath("//p[starts-with(@class, 'input__error')]");
    private final By LogInButton = By.xpath("//a[@href='/login']");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Open register page")
    public RegisterPage openRegisterPage(){
        driver.get(REGISTER_PAGE_URL);
        return this;
    }

    @Step("Filling in Name")
    public void setName(String name) throws InterruptedException {
        setTextField(NameField, name);
    }

    @Step("Filling in Email")
    public void setEmail(String email) throws InterruptedException {
        setTextField(EmailField, email);
    }

    @Step("Filling in Password")
    public void setPassword(String password) throws InterruptedException {
        setTextField(PasswordField, password);
    }

    @Step("Click on Register button")
    public void clickRegisterButton() {
        clickElement(RegisterButton);
    }

    @Step("Switch to the login")
    public void switchToLoginButton() {
        clickElement(LogInButton);
    }

    @Step("Getting text of registration error")
    public String getRegistrationError() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.visibilityOfElementLocated(RegisterError));
        return driver.findElement(RegisterError).getText();
    }
}
