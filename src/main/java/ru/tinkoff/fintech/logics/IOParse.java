package ru.tinkoff.fintech.logics;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ru.tinkoff.fintech.user.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;



public class IOParse {

	public static void writeToFile(ArrayList<User> users, String path)throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Users");
		int rowNum = 0;
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

		for (User user : users){
			IOParse.writeIntoFiles(sheet, ++rowNum, user);
		}
			FileOutputStream out = new FileOutputStream(new File(path + "file.xls"));
			workbook.write(out);
			System.out.println(path + "file.xls");

		workbook.close();
	}

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

	private static ArrayList<User> generateOffline(String dataPath) throws IOException {
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
				String[] pair = Logic.formatDob();
				String formatDob = pair[0];
				int ageOfDob = Integer.parseInt(pair[1]);

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

	public static ArrayList<User> fillData(String dataPath, String urlStr) throws IOException {
		try{
			ArrayList<User> users = generateOnline(urlStr);
			System.out.println("Есть соединение");
			return users;
		} catch (IOException e) {
			System.out.println("Нет соединения");
			return generateOffline(dataPath);
		}
	}

	private static void writeIntoFiles(HSSFSheet sheet, int rowNum, User dataModel){
		Row row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue(localeRUS(dataModel.getName()));
		row.createCell(1).setCellValue(localeRUS(dataModel.getSurname()));
		row.createCell(2).setCellValue(localeRUS(dataModel.getPatronymic()));
		row.createCell(3).setCellValue(dataModel.getAge());
		row.createCell(4).setCellValue(localeRUS(dataModel.getGender()));
		row.createCell(5).setCellValue(localeRUS(dataModel.getDOB()));
		row.createCell(6).setCellValue(dataModel.getINN());
		row.createCell(7).setCellValue(dataModel.getIndex());
		row.createCell(8).setCellValue(localeRUS(dataModel.getCountries()));
		row.createCell(9).setCellValue(localeRUS(dataModel.getStates()));
		row.createCell(10).setCellValue(localeRUS(dataModel.getCities()));
		row.createCell(11).setCellValue(localeRUS(dataModel.getStreets()));
		row.createCell(12).setCellValue(dataModel.getHouse());
		row.createCell(13).setCellValue(dataModel.getRoom());
	}

	private static String localeRUS(String string) {
		return new String(string.getBytes(), StandardCharsets.UTF_8);
	}

	private static ArrayList<User> generateOnline(String urlStr) throws IOException{
		return jsonToUsers(httpRequest(urlStr));
	}

	private static String httpRequest(String urlStr) throws IOException {
		Random random = new Random();
		urlStr += "?results=" + (1+random.nextInt(30));

		URL url = new URL(urlStr);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responseStr = response.toString();
		return responseStr.substring(responseStr.indexOf("[{")+2,responseStr.lastIndexOf("}]"));

	}

	private static ArrayList<User> jsonToUsers(String response){
		String[] userStr = response.split("},\\{");
		ArrayList<User> users = new ArrayList<>();
		for (int i = 0; i < userStr.length; i++){
			users.add(mapToUser(jsonToMap(userStr[i])));
		}
		return users;
	}

	private static HashMap<String, String> jsonToMap(String userStr){
		HashMap<String, String> hashMap = new HashMap<>();
		String[] fields = userStr.split(",");
		for(int i = 0; i < fields.length; i++ ){
			String[] pair = strToPair(fields[i]);
			hashMap.put(pair[0],pair[1]);
		}
		return hashMap;
	}

	private static User mapToUser(HashMap<String, String> hashMap){
		User user = new User();
		user.setGender(hashMap.get("gender"));
		user.setCities(hashMap.get("city"));
		user.setCountries(hashMap.get("country"));
		user.setHouse(Integer.parseInt(hashMap.get("house")));
		user.setName(hashMap.get("name"));
		user.setPatronymic(hashMap.get("patronymic"));
		user.setSurname(hashMap.get("surname"));
		user.setIndex(Integer.parseInt(hashMap.get("index")));
		user.setRoom(Integer.parseInt(hashMap.get("room")));
		user.setStates(hashMap.get("state"));
		user.setInn(Long.parseLong(hashMap.get("inn")));
		user.setStreets(hashMap.get("street"));
		String[] pair = Logic.formatDob();
		user.setDob(pair[0]);
		user.setAge(Integer.parseInt(pair[1]));
		return user;
	}

	private static String[] strToPair (String field) {
		String[] pair = field.split(":");
		pair[0] = pair[0].substring(1, pair[0].length() - 1);
		pair[1] = pair[1].substring(1, pair[1].length() - 1);
		return pair;
	}
}
