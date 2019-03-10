import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static class User {

        private String name;
        private String surname;
        private String patronymic;
        private int age;
        private String gender;
        private String dob;
        private long inn;
        private int index;
        private String countries;
        private String states;
        private String cities;
        private String streets;
        private int house;
        private int room;

        public User() {
        }

        public User(String name, String surname, String patronymic, int age, String gender, String  dob, long inn,
                    int index, String countries, String states, String cities, String streets, int house, int room) {

            this.name = name;
            this.surname = surname;
            this.patronymic = patronymic;
            this.age = age;
            this.gender = gender;
            this.dob = dob;
            this.inn = inn;
            this.index = index;
            this.countries = countries;
            this.states = states;
            this.cities = cities;
            this.streets = streets;
            this.house = house;
            this.room = room;
        }
    }

    private static ArrayList<String> fileIntoArray(String filename) throws IOException {
        Scanner scanner = new Scanner(new FileReader(filename));
        ArrayList<String> strings = new ArrayList<>();
        while (scanner.hasNext()) {
            strings.add(scanner.nextLine());
        }
        scanner.close();
        return strings;
    }

    public  static void main(String[] args) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Users");
        List<User> userList = fillData() ;
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
        for (User user : userList){
            createSheetHeader(sheet, ++rowNum, user);
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("./data/file.xls"));
            workbook.write(out);
        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    private static GregorianCalendar randomDob(){
        Random random = new Random();
        GregorianCalendar dob = new GregorianCalendar();
        int year = 1900 + random.nextInt(2001 - 1900);
        dob.set(dob.YEAR, year);
        int dayOfYear = 1 + random.nextInt(dob.getActualMaximum(dob.DAY_OF_YEAR));
        dob.set(dob.DAY_OF_YEAR, dayOfYear);
        return dob;
    }

    private static List<User> fillData() throws IOException {
        List<User> dataModels = new ArrayList<User>();
        Random random = new Random();
        int rand = 1 + random.nextInt(30);
        for (int i = 0; i < rand; i++) {
            int gen = 1 + random.nextInt(2);
            String name;
            String surname;
            String patronymic;
            String gender;
            if (gen == 1) {
                ArrayList<String> names = fileIntoArray("./data/fnames.txt");
                name = names.get(random.nextInt(names.size()));
                ArrayList<String> surnames = fileIntoArray("./data/fsurname.txt");
                surname = surnames.get(random.nextInt(surnames.size()));
                ArrayList<String> patronymics = fileIntoArray("./data/fpatronymic.txt");
                patronymic = patronymics.get(random.nextInt(patronymics.size()));
                gender = "Ж";
            }
            else {
                ArrayList<String> names = fileIntoArray("./data/mname.txt");
                name = names.get(random.nextInt(names.size()));
                ArrayList<String> surnames = fileIntoArray("./data/msurname.txt");
                surname = surnames.get(random.nextInt(surnames.size()));
                ArrayList<String> patronymics = fileIntoArray("./data/mpatronymic.txt");
                patronymic = patronymics.get(random.nextInt(patronymics.size()));
                gender = "М";
            }
            ArrayList<String> countries = fileIntoArray("./data/countries.txt");
            String country = countries.get(random.nextInt(countries.size()));
            ArrayList<String> states = fileIntoArray("./data/states.txt");
            String state = states.get(random.nextInt(states.size()));
            ArrayList<String> cities = fileIntoArray("./data/cities.txt");
            String city = cities.get(random.nextInt(cities.size()));
            ArrayList<String> streets = fileIntoArray("./data/streets.txt");
            String street = streets.get(random.nextInt(streets.size()));

            long ten = (long)Math.pow(10,8);
            int ran = 10 + random.nextInt(89);
            int nnum = 100000 + random.nextInt(999999-100000);
            long inn = 7700*ten + nnum*100 + ran;

            int index = 100000 +random.nextInt( 99999);
            int house = 1 + random.nextInt(99);
            int room = 1 + random.nextInt(399);

            GregorianCalendar dob = randomDob();
            int ageOfDob = new GregorianCalendar().getTime().getYear() - dob.getTime().getYear();
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
			fmt.setCalendar(dob);
			String formatDob = fmt.format(dob.getTime());

            User user = new User(name, surname, patronymic, ageOfDob, gender, formatDob, inn, index,
                    country, state, city, street, house, room);
            dataModels.add(user);
        }
        return dataModels;
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, User dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.name);
        row.createCell(1).setCellValue(dataModel.surname);
        row.createCell(2).setCellValue(dataModel.patronymic);
        row.createCell(3).setCellValue(dataModel.age);
        row.createCell(4).setCellValue(dataModel.gender);
        row.createCell(5).setCellValue(dataModel.dob);
        row.createCell(6).setCellValue(dataModel.inn);
        row.createCell(7).setCellValue(dataModel.index);
        row.createCell(8).setCellValue(dataModel.countries);
        row.createCell(9).setCellValue(dataModel.states);
        row.createCell(10).setCellValue(dataModel.cities);
        row.createCell(11).setCellValue(dataModel.streets);
        row.createCell(12).setCellValue(dataModel.house);
        row.createCell(13).setCellValue(dataModel.room);
    }

}
