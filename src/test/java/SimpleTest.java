import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SimpleTest {
    @Test
    void test01(){
        open("https://slqamsk.github.io/cases/simple-pages/page01.html");
        $(By.tagName("h1")).shouldHave(text("Добро пожаловать на мой сайт"));
        $(By.tagName("p1")).shouldHave(text("HTML"));
        $(By.tagName("a")).shouldHave(text("Яндекс"));
    }

    @Test
    void test02(){
        open("https://slqamsk.github.io/cases/simple-pages/page01.html");
        $(By.tagName("body")).shouldNotHave(text("До свидания!"));
    }
    @Test
    void test03() {
        open("https://slqamsk.github.io/cases/simple-pages/page02.html");
        $(By.id("unique-id-element")).shouldHave(text("Элемент с уникальным ID"));
        $(By.id("log-general")).shouldHave(text("Нажмите любую из кнопок выше..."));
    }

    @Test
    void test04Fillform() {
        open("https://slqamsk.github.io/cases/simple-pages/page02.html");
        $(By.id("log-form1")).shouldHave(text("Лог нажатий появится здесь..."));

        $(By.name("username")).setValue("Дмитрий");
        $(By.name("password")).setValue("123");
        $(By.name("contact")).setValue("pepd@mail.ru");
        $(By.name("submit-btn")).click();

        $(By.id("log-form1")).shouldHave(text("username: Дмитрий"));

    }

    @Test
    void test05() {
        open("https://slqamsk.github.io/cases/simple-pages/page02.html");
        $(By.className("unique-class")).shouldHave(text("Элемент с уникальным классом"));

        $(By.className("repeating-class")).shouldHave(text("Первый элемент с repeating-class"));
    }

    @Test
    void test05Fillform2() {
        open("https://slqamsk.github.io/cases/simple-pages/page02.html");
        $(By.id("log-form2")).shouldHave(text("Лог нажатий появится здесь..."));

        $(By.name("first-name")).setValue("Дмитрий");
        $(By.name("last-name")).setValue("Желтов");
        $(By.name("form-submit")).click();

        $(By.id("log-form2")).shouldHave(text("first-name: Дмитрий"));
        sleep(5_000);
    }


}
