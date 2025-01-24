package tests;

import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.ConstructorPage;
import ru.yandex.LoginPage;
import ru.yandex.UserAccountPage;

import java.time.Duration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static ru.yandex.Constants.WAIT_DURATION;

public class UserAccountTest extends BaseUITest{

    protected UserApi userApi;
    protected UserData userData;
    private final String name = "John";
    private final String password = "password112345";
    private final String email = "123458@123456mail.com";
    protected String accessToken;

    @Before
    public void setUp() throws InterruptedException {
        userApi = new UserApi();
        userData = new UserData(name, password, email);

        ValidatableResponse response = userApi.createUser(userData);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("success", is(true));

        userData.setName(null);
        response = userApi.loginUser(userData);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        accessToken = response.extract().path("accessToken");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickUserAccountButton();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(UserAccountPage.USER_ACCOUNT_PAGE_URL));
    }

    @After
    public void userDelete(){
        ValidatableResponse response = userApi.deleteUser(accessToken);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .and()
                .body("success", is(true));
    }

    @Test
    @DisplayName("Open User Account")
    public void PossibleToEnterUserAccount() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(UserAccountPage.USER_ACCOUNT_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Logout from User Account")
    public void PossibleToLogOut(){

        UserAccountPage userAccountPage = new UserAccountPage(driver);
        userAccountPage.clickLogoutButton();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        String currentUrl = driver.getCurrentUrl();
        assertEquals(LoginPage.LOGIN_PAGE_URL, currentUrl);
    }
}
