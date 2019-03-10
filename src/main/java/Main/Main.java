package Main;

import Logics.IOParse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public  static void main(String[] args) throws IOException {

        String path = "resources/";

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Users");
        ArrayList<User> users = new ArrayList<>();
        int rowNum = 0;

        try {
            users = IOParse.fillData(path);

            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(IOParse.localeRUS("Имя"));
            row.createCell(1).setCellValue(IOParse.localeRUS("Фамилия"));
            row.createCell(2).setCellValue(IOParse.localeRUS("Отчество"));
            row.createCell(3).setCellValue(IOParse.localeRUS("Возраст"));
            row.createCell(4).setCellValue(IOParse.localeRUS("Пол"));
            row.createCell(5).setCellValue(IOParse.localeRUS("Дата рождения"));
            row.createCell(6).setCellValue(IOParse.localeRUS("Инн"));
            row.createCell(7).setCellValue(IOParse.localeRUS("Почтовый индекс"));
            row.createCell(8).setCellValue(IOParse.localeRUS("Страна"));
            row.createCell(9).setCellValue(IOParse.localeRUS("Область"));
            row.createCell(10).setCellValue(IOParse.localeRUS("Город"));
            row.createCell(11).setCellValue(IOParse.localeRUS("Улица"));
            row.createCell(12).setCellValue(IOParse.localeRUS("Дом"));
            row.createCell(13).setCellValue(IOParse.localeRUS("Квартира"));
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(-1);
        }

        for (User user : users){
            IOParse.writeIntoFiles(sheet, ++rowNum, user);
        }
        try {;
            FileOutputStream out = new FileOutputStream(new File(path + "file.xls"));
            workbook.write(out);
        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        }
    }
}
