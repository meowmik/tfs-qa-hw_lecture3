package Logics;

import Main.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class IOParse {

	private static String getRandomFromFile(String filePath) throws IOException {
		Random random = new Random();
		Scanner scanner = new Scanner(new FileReader(filePath));
		ArrayList<String> array = new ArrayList<>();
		while (scanner.hasNext()) {
			array.add(scanner.nextLine());
		}
		scanner.close();
		return array.get(random.nextInt(array.size()));
	}

	public static ArrayList<User> fillData(String dataPath) throws IOException {
		if (dataPath.charAt(dataPath.length() - 1) != '/')
			dataPath += "/";

		ArrayList<User> dataModels = new ArrayList<>();
		Random random = new Random();
		int rand = 1 + random.nextInt(30);
		try {
			for (int i = 0; i < rand; i++) {
				int gen = 1 + random.nextInt(2);
				String name;
				String surname;
				String patronymic;
				String gender;

				if (gen == 1) {
					name = getRandomFromFile(dataPath + "fnames.txt");
					surname = getRandomFromFile(dataPath + "fsurname.txt");
					patronymic = getRandomFromFile(dataPath + "fpatronymic.txt");
					gender = "Ж";
				} else {
					name = getRandomFromFile(dataPath + "mname.txt");
					surname = getRandomFromFile(dataPath + "msurname.txt");
					patronymic = getRandomFromFile(dataPath + "mpatronymic.txt");
					gender = "М";
				}
				String country = getRandomFromFile(dataPath + "countries.txt");
				String state = getRandomFromFile(dataPath + "states.txt");
				String city = getRandomFromFile(dataPath + "cities.txt");
				String street = getRandomFromFile(dataPath + "streets.txt");

				long ten = (long) Math.pow(10, 8);
				int ran = 10 + random.nextInt(89);
				int nnum = 100000 + random.nextInt(999999 - 100000);
				long inn = 7700 * ten + nnum * 100 + ran;

				int index = 100000 + random.nextInt(99999);
				int house = 1 + random.nextInt(99);
				int room = 1 + random.nextInt(399);

				GregorianCalendar dob = Logic.randomDob();
				int ageOfDob = new GregorianCalendar().getTime().getYear() - dob.getTime().getYear();
				SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
				fmt.setCalendar(dob);
				String formatDob = fmt.format(dob.getTime());

				User user = new User(name, surname, patronymic, ageOfDob, gender, formatDob, inn, index,
						country, state, city, street, house, room);
				dataModels.add(user);
			}
		} catch (IOException e) {
			System.out.println("IOException from fillData: ");
			throw e;
		}
		return dataModels;
	}

	public static void writeIntoFiles(HSSFSheet sheet, int rowNum, User dataModel) throws IOException {
		Row row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue(IOParse.localeRUS(dataModel.getName()));
		row.createCell(1).setCellValue(IOParse.localeRUS(dataModel.getSurname()));
		row.createCell(2).setCellValue(IOParse.localeRUS(dataModel.getPatronymic()));
		row.createCell(3).setCellValue(dataModel.getAge());
		row.createCell(4).setCellValue(IOParse.localeRUS(dataModel.getGender()));
		row.createCell(5).setCellValue(IOParse.localeRUS(dataModel.getDOB()));
		row.createCell(6).setCellValue(dataModel.getINN());
		row.createCell(7).setCellValue(dataModel.getIndex());
		row.createCell(8).setCellValue(IOParse.localeRUS(dataModel.getCountries()));
		row.createCell(9).setCellValue(IOParse.localeRUS(dataModel.getStates()));
		row.createCell(10).setCellValue(IOParse.localeRUS(dataModel.getCities()));
		row.createCell(11).setCellValue(IOParse.localeRUS(dataModel.getStreets()));
		row.createCell(12).setCellValue(dataModel.getHouse());
		row.createCell(13).setCellValue(dataModel.getRoom());
	}

	public static String localeRUS(String string) {
		//todo: в общем, все перепробовал, не распознает большую 'И'
		return new String(string.getBytes(), StandardCharsets.UTF_8);
	}

}
