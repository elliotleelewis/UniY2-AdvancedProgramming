package ex2;
import com.google.gson.*;
/**
 * Copyright Elliot Lewis © 2017
 */
public class ParsingController
{
	// JSON to Java
	public static void main(String[] args)
	{
		JsonParser parser = new JsonParser();
		String json = "[{\"name\":\"Mohammed Kaleem\",\"email\":\"kaleem@email.com\"},{\"name\":\"Alan Crispin\",\"email\":\"alan@email.com\"},{\"name\":\"Keeley Crockket\",\"email\":\"keeley@email.com\"}]";
		System.out.println(json);
		JsonArray contacts = parser.parse(json).getAsJsonArray();
		System.out.println("JSON Array Size: " + contacts.size());
		for(JsonElement element : contacts) {
			JsonObject contact = element.getAsJsonObject();
			String name = contact.get("name").getAsString();
			String email = contact.get("email").getAsString();
			System.out.println("Name: " + name + "\tEmail: " + email);
		}
	}
}