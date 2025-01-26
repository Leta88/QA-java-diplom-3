package tests;

import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.ConstructorPage;
import ru.yandex.LoginPage;
import ru.yandex.PasswordRecoveryPage;
import ru.yandex.RegisterPage;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static ru.yandex.Constants.WAIT_DURATION;

@RunWith(Parameterized.class)
public class LoginTest extends BaseUITest {

    protected UserApi userApi;
    protected UserData userData;
    private final String name;
    private final String password;
    private final String email;
    private final int loginVersion;
    protected String accessToken;

    public LoginTest(int loginVersion, String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.loginVersion = loginVersion;
    }

    @Before
    public void setUp(){
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

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {

                { 1, "John", "password112345", "john12345@123.com"},
                { 2, "Mary", "qwerty123", "mary12345@123.com"},
                { 3, "Ann", "123qwerty123", "ann12345@123.com"},
                { 4, "Mike", "11password115", "mike12345@321.com"}
        };
    }

    @Test
    @DisplayName("Login by different ways")
    public void checkLoginTest() throws InterruptedException {
        switch (loginVersion) {
            case 1:
            case 2:
                ConstructorPage constructorPage = new ConstructorPage(driver);
                constructorPage.openConstructorPage();
                constructorPage.loginVersions(loginVersion);
                break;
            case 3:
                RegisterPage registerPage = new RegisterPage(driver);
                registerPage.openRegisterPage();
                registerPage.switchToLoginButton();
                break;
            case 4:
                PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
                passwordRecoveryPage.openPasswordRecoveryPage();
                passwordRecoveryPage.switchToLoginButton();
                break;
        }

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        String currentUrl = driver.getCurrentUrl();
        assertEquals(LoginPage.LOGIN_PAGE_URL, currentUrl);

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }

}
