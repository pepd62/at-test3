import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class AuthorizationTests {
    @Test
    void test01LoginSuccess(){
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $(By.id("username")).setValue("error_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("loginButton")).click();
        $(By.id("message")).shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
    }

    @Test
    void test02LoginWrongPassword(){
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $(By.id("username")).setValue("error_user");
        $(By.id("password")).setValue("123");
        $(By.id("loginButton")).click();
        $(By.id("message")).shouldHave(text("Invalid username or password."));
    }
    @Test
    void test03WrongLogin(){
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $(By.id("username")).setValue("dima_zheltov");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("loginButton")).click();
        $(By.id("message")).shouldHave(text("Invalid username or password."));
    }
    @Test
    //Открывает тест через Chrome, выдаёт ошибку по совместимости. Но логика теста по Devtools кажется верной.
    void test04ExternalForm(){
        open("https://authenticationtest.com/simpleFormAuth/");
        $(By.id("email")).setValue("simpleForm@authenticationtest.com");
        $(By.id("password")).setValue("pa$$w0rd");
        $(By.className("btn btn-lg btn-success")).click();
    }
}
