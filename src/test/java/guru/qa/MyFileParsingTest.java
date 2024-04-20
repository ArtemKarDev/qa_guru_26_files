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
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MyFileParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    private static final ObjectMapper objectMapper = new ObjectMapper();



    public ContainDiffFiles(String res, String fileName) throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream(res)
        )) {
            ZipEntry entry;
            List<String> zipActualList = new ArrayList<>();

            while ((entry = zis.getNextEntry()) != null) {
                zipActualList.add(entry.getName());
            }
            Assertions.assertTrue(zipActualList.contains(fileName));
        }
    }
    @Test
    void zipContainExlFilesTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;
            List<String> zipActualList = new ArrayList<>();

            List<String> zipExpectedList = new ArrayList<>();
            zipExpectedList.add("example.csv");
            zipExpectedList.add("sample-pdf-file.pdf");
            zipExpectedList.add("example.xls");

            while ((entry = zis.getNextEntry()) != null) {
                zipActualList.add(entry.getName());
            }
            Assertions.assertTrue(zipActualList.containsAll(zipExpectedList));
            Assertions.assertTrue(zipExpectedList.containsAll(zipActualList));

        }
    }

    @Test
    void zipContainDiffFilesTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;
            List<String> zipActualList = new ArrayList<>();

            List<String> zipExpectedList = new ArrayList<>();
            zipExpectedList.add("example.csv");
            zipExpectedList.add("sample-pdf-file.pdf");
            zipExpectedList.add("example.xls");

            while ((entry = zis.getNextEntry()) != null) {
                zipActualList.add(entry.getName());
            }
            Assertions.assertTrue(zipActualList.containsAll(zipExpectedList));
            Assertions.assertTrue(zipExpectedList.containsAll(zipActualList));

        }
    }

    @Test
    void exlZipFileTest() throws Exception {
        //InputStream stream = cl.getResourceAsStream("files.zip");
        //ZipFile zf = new ZipFile(stream);
        ZipFile zf = new ZipFile(new File("src/test/resources/files.zip"));

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xls")) {

                    //try (InputStream inputStream = cl.getResourceAsStream("files.zip");) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        Double actual = xls.excel.getSheetAt(0).getRow(0).getCell(0).getNumericCellValue();
                        Assertions.assertEquals(actual, 1);
                    }
                }
            }
        }
    }

    @Test
    void pdfZipFileTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/files.zip"));
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        Assertions.assertEquals("Dainik", pdf.author);
                    }
                }
            }
        }
    }


    @Test
    void csvZipFileTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/files.zip"));
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream is = zf.getInputStream(entry)) {
                        CSVReader csvReader = new CSVReader(new InputStreamReader(is));
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
            }
        }
    }

    @Test
    void jsonFileJacksonParsingTest() throws Exception {
        List<String> actualColors = new ArrayList<String>();
        actualColors.add("red");
        actualColors.add("blue");
        actualColors.add("white");
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("car.json")
        )) {
            Car actual = objectMapper.readValue(reader, Car.class);

            Assertions.assertEquals("Toyota", actual.getMark());
            Assertions.assertEquals(actualColors, actual.getColors());
            Assertions.assertEquals(2020, actual.getYear());
            Assertions.assertEquals(true, actual.getFeatures().getNavigation());

        }

    }


}

