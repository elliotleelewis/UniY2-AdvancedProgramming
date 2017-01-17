package ex1;
import com.google.gson.*;

import java.util.ArrayList;
/**
 * Copyright Elliot Lewis © 2017
 */
public class Controller
{
	// Java to JSON
	public static void main(String[] args)
	{
		Gson gson = new Gson();
		ArrayList<Contact> contacts = new ArrayList<>();
		Contact elliot = new Contact("Elliot Lewis", "elliot@email.com");
		contacts.add(elliot);
		Contact kaleem = new Contact("Mohammed Kaleem", "kaleem@email.com");
		contacts.add(kaleem);
		Contact alan = new Contact("Alan Crispin", "alan@email.com");
		contacts.add(alan);
		Contact keeley = new Contact("Keeley Crockket", "keeley@email.com");
		contacts.add(keeley);
		String json = gson.toJson(contacts);
		System.out.println(json);
	}
}