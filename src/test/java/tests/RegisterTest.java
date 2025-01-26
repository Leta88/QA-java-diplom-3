package tests;

import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.RegisterPage;
import static ru.yandex.Constants.WAIT_DURATION;
import ru.yandex.LoginPage;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegisterTest extends BaseUITest {

    protected final String name;
    protected final String email;
    protected final String password;

    public RegisterTest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][] {
                { "John", "1@mail.rufyf66", "1234567"},
        };
    }

    @After
    public void deleteUser(){
        UserApi userApi = new UserApi();
        UserData userData = new UserData(null, password, email);
        ValidatableResponse response = userApi.loginUser(userData);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        String accessToken = response.extract().path("accessToken");
        response = userApi.deleteUser(accessToken);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Register new user via UI")
    public void registerNewUserTest() throws InterruptedException {

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();

        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        String currentUrl = driver.getCurrentUrl();
        assertEquals(LoginPage.LOGIN_PAGE_URL, currentUrl);
    }
}
