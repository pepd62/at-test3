import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;

public class SimpleXPathTests {
    @Test
    void testPageH1(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p").shouldHave(text("Это обычный параграф текста."));
    }
    @Test
    void testSpecialParagraph(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='special-paragraph']").shouldHave(text("Этот параграф особенный - он единственный на странице с таким классом."));
    }
    @Test
    void testSpecialParagraph2(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='info-text']").shouldHave(text("Это первый информационный текст."));
    }
    @Test
    void testInfoTextsCount(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//p[@class='info-text']").shouldHave(text("Это первый информационный текст."));
        $x("//p[@class='info-text']").shouldHave(text("Это второй информационный текст."));
        $x("//p[@class='info-text']").shouldHave(text("Это третий информационный текст."));
    }
    @Test
    void testExternalLinks(){
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//a[@class='external-link']").shouldHave(text("Внешняя ссылка (Example)"));
        $x("//a[@class='external-link'][2]").shouldHave(text("Внешняя ссылка (Google)"));
    }
    @Test
    void testNotUniqueClass1(){
        open("https://slqa.ru/cases/xPathSimpleForm/");
        $(withText("Москва")).shouldHave(text("250 единиц"));
        $x("//*[contains(., 'Питер')]").shouldHave(text("180 единиц"));
        $x("//*[starts-with(.,'Казахстан')]").shouldHave(text("площадь 2 724 902"));
    }
}
