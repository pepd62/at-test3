import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CommissionTests {
    @Test
    void test01UsualSum() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));
    }
}
