package Main;

public class User {

	//todo: названия поменять в countries/states/cities (house/room)
	private String name = "";
	private String surname = "";
	private String patronymic = "";
	private int age;
	private String gender = "";
	private String dob = "";
	private long inn;
	private int index;
	private String countries = "";
	private String states = "";
	private String cities = "";
	private String streets = "";
	private int house;
	private int room;

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getDOB() {
		return dob;
	}

	public long getINN() {
		return inn;
	}

	public int getIndex() {
		return index;
	}

	public String getCountries() {
		return countries;
	}

	public String getStates() {
		return states;
	}

	public String getCities() {
		return cities;
	}

	public String getStreets() {
		return streets;
	}

	public int getHouse() {
		return house;
	}

	public int getRoom() {
		return room;
	}

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