package ru.tinkoff.fintech.logics;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;

public class Logic {

	private static GregorianCalendar randomDob(){
		Random random = new Random();
		GregorianCalendar dob = new GregorianCalendar();
		int year = 1930 + random.nextInt(2001 - 1930);
		dob.set(GregorianCalendar.YEAR, year);
		int dayOfYear = 1 + random.nextInt(dob.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
		dob.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
		return dob;
	}

	public static String[] formatDob (){
		String[] pair = new String[2];
		GregorianCalendar dob = Logic.randomDob();
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		fmt.setCalendar(dob);
		int ageOfDob = new GregorianCalendar().getTime().getYear() - dob.getTime().getYear();
		pair[0] =  fmt.format(dob.getTime());
		pair[1] = String.valueOf(ageOfDob);
		return pair;
	}

}
