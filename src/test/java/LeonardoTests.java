import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.files.DownloadActions.click;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LeonardoTests {
        @Test
        void test01CartTests(){
            $x("//button[contains(.,'Позже')]").click();
            $x("//span[.='каталог']/parent::button[contains(@class,'header__nav-item')]").click();
            $x("//a[@href='/ishop/tree_3812270609/']").click();
            $x("//div[contains(@class,'widgets__item')]/a[contains(.,'Кисти')]").click();
            $x("//span[.='В корзину']/parent::a[contains(@class,'btn btn_primary btn_h44 goods-preview__add-to-cart-btn')]").click();
            $x("//a[@href='/cart/']").click();
            $x("//div[@id='cart-list-1']").shouldHave(text("Кисть синтетика \"VISTA-ARTISTA\" 50231-01 круглая короткая ручка №01"));


            //span[.='каталог']/parent::button[contains(@class,'header__nav-item')]
            //button[contains(@class,'header__nav-item')]
            //a[contains(., 'Кисть синтетика "VISTA-ARTISTA" 50231-01 круглая короткая ручка №01')]

        }
}
