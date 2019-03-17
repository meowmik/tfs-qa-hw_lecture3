package ru.tinkoff.fintech.main;

import ru.tinkoff.fintech.logics.IOParse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ru.tinkoff.fintech.user.User;

import java.io.*;
import java.util.*;

public class Main {


	public  static void main(String[] args){

		System.out.println("Старт");

		String path = "resources/";

		try{
			ArrayList<User> users = IOParse.fillData(path, "https://randomapi.com/api/e347c42dae4d565b8c3146f181720075");
			IOParse.writeToFile(users, path);
		} catch (Exception e){
			System.err.printf("%s\n", e.toString());
			System.exit(-1);
		}

	}
}
