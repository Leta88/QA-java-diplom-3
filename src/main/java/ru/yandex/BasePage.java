package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static ru.yandex.Constants.WAIT_DURATION;

public class BasePage {

    protected WebDriver driver;

    private final By Logo = By.className("AppHeader_header__logo__2D0X2");

    void setTextField(By inputLocator, String text) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.visibilityOfElementLocated(inputLocator));

        driver.findElement(inputLocator).click();
        driver.findElement(inputLocator).sendKeys(text);
    }

    void clickElement(By inputLocator){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.elementToBeClickable(inputLocator));
        driver.findElement(inputLocator).click();
    }

    public void clickLogo(){
        clickElement(Logo);
    }
}
