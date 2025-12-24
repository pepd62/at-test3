import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalcusTests {
    // Название: Расчёт ежемесячного платежа по ипотеке при стандартных условиях
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Selenide.open("https://calcus.ru/kalkulyator-ipoteki");

        getWebDriver().manage().window().maximize();

        $x("//button[contains(@class, 'js-accept-cookie')]").click();
        $x("//a[contains(text(),'По сумме кредита')]").click();

    }
    @Test
    void test01BoundaryValues(){
        $x("//input[@name='credit_sum']").setValue("2 500 000");
        $x("//input[@name='period']").setValue("50");
        $x("//input[@name='percent']").setValue("15");
        $("#payment-type-1").shouldBe(checked);
        $x("//input[@type='submit']")
                .shouldBe(clickable)
                .click();
        $x("//div[contains(@class,'calc-result-value result-placeholder-monthly_payment format-currency')]")
                .shouldBe(visible)
                .shouldHave(text("31 268,12"));
    }
    @Test
    void test03BoundaryValues(){
        $x("//input[@name='credit_sum']").setValue("2 500 000");
        $x("//input[@name='period']").setValue("51");
        $x("//input[@name='percent']").setValue("15");
        $("#payment-type-1").shouldBe(checked);
        $x("//input[@type='submit']")
                .shouldBe(clickable)
                .click();
        $x("//div[@id='period-error']")
                .shouldBe(visible)
                .shouldHave(text("Максимальное значение 50\n"));
    }
    @Test
    void test03DifferentiatedPay(){
        $x("//input[@name='credit_sum']").setValue("2 500 000");
        $x("//input[@name='period']").setValue("7");
        $x("//input[@name='percent']").setValue("15");
        $("#payment-type-2").doubleClick();
        $x("//input[@type='submit']")
                .shouldBe(clickable)
                .click();
        $x("//div[contains(@class,'calc-result-value result-placeholder-monthly_payment format-currency')]")
                .shouldBe(visible)
                .shouldHave(text("61 011,90"));
    }
}