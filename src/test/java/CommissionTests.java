import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CommissionTests {
    @Test
    void test01UsualSum() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("2000");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));
    }
    @Test
    void test02UsualSum() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("100");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("com")).shouldHave(text("10"));
        $(By.name("total")).shouldHave(text("110"));
    }
    @Test
    void test03UsualSum() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("11000");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("com")).shouldHave(text("100"));
        $(By.name("total")).shouldHave(text("11100"));
    }
    @Test
    void test04CommissionAbroad() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("120000");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("error")).shouldHave(text("Вы ввели значение больше 100 тыс. рублей."));
    }
    @Test
    void test05TextInSum() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("qweqewe");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("error")).shouldHave(text("Вы ввели не числовое значение."));
    }
    @Test
    void test06DoubleUsualSum() {
        open("https://slqa.ru/cases/fc/v01/");
        //Ввод
        $(By.name("sum")).setValue("2000");
        //Кнопка
        $(By.name("submit")).click();
        //Проверка комиссии/суммы
        $(By.name("com")).shouldHave(text("20"));
        $(By.name("total")).shouldHave(text("2020"));

        $("input[name=sum]").clear();
        $("input[name=sum]").setValue("500");

        $(By.name("submit")).click();
        $(By.name("com")).shouldHave(text("10"));
        $(By.name("total")).shouldHave(text("510"));

        sleep(5_000);
    }
}
