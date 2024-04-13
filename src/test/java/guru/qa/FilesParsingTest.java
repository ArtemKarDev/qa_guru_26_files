package guru.qa;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTest {

    @Test
    void pdfFilesParsingTest() throws Exception{
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded =
                $("[href*='junit-user-guide-5.10.1.pdf']").download();
        try (InputStream is = FileInputStream(downloaded)){

        }
    }
}
