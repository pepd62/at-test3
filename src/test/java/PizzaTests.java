import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class PizzaTests {
    @Test
    void test01Order() {
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $(By.id("name")).setValue("Дмитрий");
        $(By.id("email")).setValue("test@mai.com");
        $(By.id("pizza2")).click();
        $(By.id("pizza4")).click();
        $(By.className("order-btn")).click();
        $x("//li[.='Пепперони']").shouldHave(text("Пепперони"));
        $x("//li[.='Четыре сыра']").shouldHave(text("Четыре сыра"));
    }
}
