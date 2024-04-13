package guru.qa;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SelenideFilesTest {

    @Test
    void downloadFileTest(){
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $(".react-blod-header-audit-and-raw-actions [href*='main/README.md']").download();

    }
}
