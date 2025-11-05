package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import guru.qa.model.Car;
import guru.qa.model.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void pdfFilesParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded =
                $("[href*='junit-user-guide-6.0.1.pdf']").download();

        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
    }

    @Test
    void xlsFileParsingTest() throws Exception {
        open("https://excelvba.ru/programmes/Teachers");
        File downloaded =
                $("[href*='files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);
        System.out.println("");
        String actualValue = xls.excel.getSheetAt(2).getRow(3).getCell(1).getStringCellValue();
        Integer len = actualValue.length();
        System.out.println(actualValue);
        //Assertions.assertTrue(actualValue.contains("Преподаватель"));
        Assertions.assertTrue(len.equals(13));

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
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("example.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }


    }

    @Test
    @Disabled
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("glossary.json")
        )) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);
            System.out.println(actual.get("title").getAsString());
            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(345, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary.json").getAsJsonObject();
            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("SGML", inner.get("Acronym").getAsString());

        }
    }

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("glossary.json")
        )) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(345, actual.getId());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("SGML", actual.getGlossary().getAcronym());
            Assertions.assertEquals("SG", actual.getGlossaryOptions().get(0).getTerm());

        }
    }



}
