package Main;

import Logics.IOParse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.*;
import java.util.*;

public class Main {

    public  static void main(String[] args) throws IOException {

        String path = "resources/";

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Users");
        ArrayList<User> users = new ArrayList<>();
        try {
            users = IOParse.fillData(path);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(-1);
        }
        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Имя");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Отчество");
        row.createCell(3).setCellValue("Возраст");
        row.createCell(4).setCellValue("Пол");
        row.createCell(5).setCellValue("Дата рождения");
        row.createCell(6).setCellValue("Инн");
        row.createCell(7).setCellValue("Почтовый индекс");
        row.createCell(8).setCellValue("Страна");
        row.createCell(9).setCellValue("Область");
        row.createCell(10).setCellValue("Город");
        row.createCell(11).setCellValue("Улица");
        row.createCell(12).setCellValue("Дом");
        row.createCell(13).setCellValue("Квартира");
        for (User user : users){
            IOParse.writeIntoFiles(sheet, ++rowNum, user);
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(path + "file.xls"));
            workbook.write(out);
        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        }
    }
}
