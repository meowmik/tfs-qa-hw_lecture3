package Logics;

import java.util.GregorianCalendar;
import java.util.Random;

public class Logic {

	public static GregorianCalendar randomDob(){
		Random random = new Random();
		GregorianCalendar dob = new GregorianCalendar();
		int year = 1930 + random.nextInt(2001 - 1930);
		dob.set(GregorianCalendar.YEAR, year);
		int dayOfYear = 1 + random.nextInt(dob.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
		dob.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
		return dob;
	}

}
