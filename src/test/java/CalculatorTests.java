import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    // Название: Расчёт ежемесячного платежа по ипотеке при стандартных условиях
    @Test
    void test01Standard() {
        // Предусловия:
        // Открыт калькулятор ипотеки: https://calcus.ru/kalkulyator-ipoteki
        // Страница загружена полностью
        Configuration.pageLoadStrategy = "eager";
        Selenide.open("https://calcus.ru/kalkulyator-ipoteki");

        getWebDriver().manage().window().maximize();

        // Принять использование куки
        $x("//button[contains(@class, 'js-accept-cookie')]").click();

        // Убедиться что форма не содержит данных
        System.out.println("Value" + $x("//*[@name='cost']").getValue());
        $x("//*[@name='cost']").shouldBe(empty);
        $x("//*[@name='start_sum']").shouldBe(empty);
        $x("//*[@name='period']").shouldBe(empty);
        $x("//*[@name='percent']").shouldBe(empty);

        // Сумма кредита 0
        $x("//span[contains(@class, 'credit_sum_value')]").shouldHave(text("0"));

        // Ввести 3000000 в поле “Стоимость недвижимости”
        $x("//*[@name='cost']").setValue("3000000");
        // Ввести 600000 в поле “Первоначальный взнос”
        $x("//*[@name='start_sum']").setValue("600000");

        // Сумма кредита должна быть разностью между Стоимостью недвижимости и Первоначальным взносом
        $x("//span[contains(@class, 'credit_sum_value')]")
                .shouldHave(text("2 400 000 ₽"));
        // В строке «Первоначальный взнос» показывается 20% от Стоимости недвижимости
        $x("//div[contains(@class, 'start_sum_equiv')]").shouldHave(text("= 20 %"));

        // Ввести 20 лет в поле “Срок кредита”
        $x("//*[@name='period']").setValue("20");

        // Ввести 10% в поле “Процентная ставка”
        $x("//*[@name='percent']").setValue("10");

        // Убедиться, что выбран тип платежа “Аннуитетный”
        $("#payment-type-1").shouldBe(checked);

        // Проверить, что не выбран тип платежа "Дифференцированный"
        $("#payment-type-2").shouldNotBe(checked);

        // Нажать кнопку «Рассчитать»
        $x("//input[@type='submit']")
                .shouldBe(clickable)
                .click();

        // Ожидаемый результат (тут конечно нужны формулы, но мы будем думать что формулы проверены и верно вычисляют):
        //– Все перечисленные значения появились после нажатия кнопки «Рассчитать»
        //– В блоке результатов отображается Ежемесячный платёж, Начисленные проценты, Долг + проценты

        //— После отображения блока результатов есть таблица, где показана Суммы платежа, Платеж по основному долгу, Платеж по процентам, Остаток долга по месяцам ипотеки
        //– Рассчитанный Ежемесячный платёж составляет 23 160,52 рублей
        $x("//div[contains(@class, 'result-placeholder-monthly_payment')]")
                .shouldBe(visible)
                .shouldHave(text("23 160,52"));
        //— Рассчитанные Начисленные проценты составляют приблизительно 3 158 524,80 рублей (тут конечно нужна формула, но мы будем думать что формула проверена и верно вычисляет)
        $x("//div[contains(@class, 'result-placeholder-interest')]")
                .shouldBe(visible)
                .shouldHave(text("3 158 524,60"));
        //— Рассчитанные Долг + проценты составляют 5 558 524,80 рублей (тут конечно нужна формула, но мы будем думать что формула проверена и верно вычисляет)
        $x("//div[contains(@class, 'result-placeholder-total_paid')]")
                .shouldBe(visible)
                .shouldHave(text("5 558 524,60"));
    }

    @Test
    void test02ByCreditSum() {
        // Предусловия:
        // Открыт калькулятор ипотеки: https://calcus.ru/kalkulyator-ipoteki
        // Страница загружена полностью
        Configuration.pageLoadStrategy = "eager";
        Selenide.open("https://calcus.ru/kalkulyator-ipoteki");

        getWebDriver().manage().window().maximize();
        //Перейти на закладку по сумме кредита

        $x("//a[contains(.,'По сумме кредита'))]").click();

        /*
        x("//a[contains(@class,'js-calc-toggle') and not(contains(@class,'current'))]").click();
        $x("//a[contains(.,'По сумме кредита'))]").click();\
        */


        $x("//button[contains(@class, 'js-accept-cookie')]").click();

        System.out.println("Value" + $x("//*[@name='cost']").getValue());
        $x("//*[@name='credit_sum']").shouldBe(empty);
        $x("//*[@name='period']").shouldBe(empty);
        $x("//*[@name='percent']").shouldBe(empty);

        //Заполнить поле сумма кредита 4000000
        $x("//*[@name='credit_sum']").setValue("4000000");
        //Заполнить поле срок кредита 10
        $x("//*[@name='period']").setValue("10");
        //Заполнить поле процентная ставка 17
        $x("//*[@name='percent']").setValue("17");
        // Убедиться, что выбран тип платежа “Аннуитетный”
        $("#payment-type-1").shouldBe(checked);
        //Нажать кнопку рассчитать
        $x("//input[@type='submit']")
                .shouldBe(clickable)
                .click();

        //Проверить что переплата равна 108,56 %
        $x("//div[contains(@class,'result-placeholder-overpayment')]")
                .shouldBe(visible)
                .shouldHave(text("108,56"));
    }
}