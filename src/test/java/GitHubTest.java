import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class GitHubTest {
    @Test
    void test01(){
        open("https://github.com/pepd62/at-java-study.git");
        $("body").shouldHave(text("gradle/wrapper"));
    }
}
