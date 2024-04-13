package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static javax.print.DocFlavor.INPUT_STREAM.PDF;

public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    @Test
    void pdfFilesParsingTest() throws Exception{
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded =
                $("[href*='junit-user-guide-5.10.2.pdf']").download();

            PDF pdf = new PDF(downloaded);
            Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein",pdf.author);
    }

    @Test
    void xlsFileParsingTest() throws Exception{
        open("https://excelvba.ru/programmes/Teachers");
        File downloaded =
                $("[href*='files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);

        String actualValue = xls.excel.getSheetAt(2).getRow(3).getCell(1).getStringCellValue();
        Assertions.assertTrue(actualValue.contains("Преподаватель"));

    }

    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"Selenide", "https://selenide.org"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"JUnit 5", "https://junit.org"},
                    data.get(1)
            );
        }
    }

    @Test
    void zipFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.zip");
             ZipInputStream zis = new ZipInputStream(is)){
            ZipEntry entry;
        }


    }

}
