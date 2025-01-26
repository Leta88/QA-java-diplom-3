package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.ConstructorPage;

public class ConstructorTabsTest extends BaseUITest{

    @Test
    @DisplayName("It is possible to select tab with buns")
    public void possibleToSwitchTabsBunsTest(){
        Allure.description("Check if it is possible to select tab with buns");

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();

        constructorPage.clickBunsTab();
        Assert.assertTrue(constructorPage.checkIfBunsTabIsCurrent());
    }

    @Test
    @DisplayName("It is possible to select tab with sauces")
    public void possibleToSwitchTabsSaucesTest(){
        Allure.description("Check if it is possible to select tab with sauces");

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();

        constructorPage.clickSaucesTab();
        Assert.assertTrue(constructorPage.checkIfSaucesTabIsCurrent());
    }

    @Test
    @DisplayName("It is possible to select tab with fillings")
    public void possibleToSwitchTabsFillingsTest(){

        Allure.description("Check if it is possible to select tab with fillings");
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();

        constructorPage.clickFillingsTab();
        Assert.assertTrue(constructorPage.checkIfFillingsTabIsCurrent());
    }
}
