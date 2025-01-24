package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.Constants.BASE_URL;
import static ru.yandex.Constants.WAIT_DURATION;

public class ConstructorPage extends BasePage{

    public static final String CONSTRUCTOR_PAGE_URL = BASE_URL;

    private final By userAccountButton = By.xpath("//a[@href='/account']");
    private final By enterUserAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//div[./span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[./span[text()='Начинки']]");

    public ConstructorPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Open constructor page")
    public ConstructorPage openConstructorPage(){
        driver.get(CONSTRUCTOR_PAGE_URL);
        return this;
    }

    @Step("Open user's account")
    public void clickUserAccountButton(){
        clickElement(userAccountButton);
    }

    @Step("Click on the account entering tab")
    public void clickEnterUserAccountButton(){
        clickElement(enterUserAccountButton);
    }

    private void clickTab(By inputLocator){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION))
                .until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(inputLocator));
    }

    @Step("Select Buns tab")
    public void clickBunsTab(){
        clickTab(bunsTab);
    }

    @Step("Select Sauces tab")
    public void clickSaucesTab(){
        clickTab(saucesTab);
    }

    @Step("Select Fillings tab")
    public void clickFillingsTab(){
        clickTab(fillingsTab);
    }

    public boolean checkIfBunsTabIsCurrent(){
        WebElement elementBuns = driver.findElement(bunsTab);
        WebElement elementSauces = driver.findElement(saucesTab);
        WebElement elementFillings = driver.findElement(fillingsTab);

        return (elementBuns.getAttribute("class").contains("current")) &&
                (!elementSauces.getAttribute("class").contains("current")) &&
                (!elementFillings.getAttribute("class").contains("current"));
    }

    public boolean checkIfSaucesTabIsCurrent(){
        WebElement elementBuns = driver.findElement(bunsTab);
        WebElement elementSauces = driver.findElement(saucesTab);
        WebElement elementFillings = driver.findElement(fillingsTab);

        return (!elementBuns.getAttribute("class").contains("current")) &&
                (elementSauces.getAttribute("class").contains("current")) &&
                (!elementFillings.getAttribute("class").contains("current"));
    }

    public boolean checkIfFillingsTabIsCurrent(){
        WebElement elementBuns = driver.findElement(bunsTab);
        WebElement elementSauces = driver.findElement(saucesTab);
        WebElement elementFillings = driver.findElement(fillingsTab);

        return (!elementBuns.getAttribute("class").contains("current")) &&
                (!elementSauces.getAttribute("class").contains("current")) &&
                (elementFillings.getAttribute("class").contains("current"));
    }

    @Step("Forwarded to the login page")
    public void loginVersions(int version){
        switch (version){
            case 1: clickUserAccountButton(); break;
            case 2: clickEnterUserAccountButton(); break;
        }
    }
}
