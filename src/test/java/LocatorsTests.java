import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LocatorsTests {
    @Test
    void testByIdExamTickets() {
        open("http://92.51.36.108:7777/sl.qa/exam_tickets/");
        //Поиск CSS-селектор
        $("#quantity_of_tickets").clear();
        //Поиск специфичный метод By.id
        $(By.id("quantity_of_tickets")).sendKeys("10");
        /*Либо второй вариант
         $("#quantity_of_tickets").setValue("10");\
         */
    }

    @Test
    void testByName() {
        open("https://slqa.ru/cases/fc/v01/");
        $("[name=sum").setValue("100");
        $(By.name("sum")).type("12345678901234567890");
    }

    @Test
    void test01_success() {
        open("https://ru.wikipedia.org/wiki/Selenium");
        $(By.className("mw-page-title-main")).shouldHave(text("Selenium"));


    }
}