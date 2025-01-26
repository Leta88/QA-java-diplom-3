package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static ru.yandex.Constants.BASE_URL;

public class UserAccountPage extends BasePage{

    public static final String USER_ACCOUNT_PAGE_URL = BASE_URL + "account/profile";
    private final By LogoutButton = By.xpath("//button[text()='Выход']");

    public UserAccountPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Logout from user account")
    public void clickLogoutButton() {
        clickElement(LogoutButton);
    }
}
