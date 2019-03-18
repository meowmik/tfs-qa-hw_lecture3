package ru.tinkoff.fintech.main;

import ru.tinkoff.fintech.logics.IOParse;
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
		} catch (IOException e){
			System.err.printf("%s\n", e.toString());
			System.exit(-1);
		}

	}
}
