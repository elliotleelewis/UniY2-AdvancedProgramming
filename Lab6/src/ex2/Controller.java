package ex2;
import java.util.*;
public class Controller
{
	private static HashMap<String, String> phone;
	private static void checkContains(String who)
	{
		if(phone.containsKey(who)) {
			System.out.println("Contains: " + who);
		}
		else {
			System.out.println("Doesn't contain: " + who);
		}
	}
	public static void main(String[] args)
	{
		phone = new HashMap<String, String>();
		phone.put("John", "3453");
		phone.put("Jenny", "3478");
		phone.put("Richard", "3567");
		phone.put("Helen", "4532");
		String who = "Jenny";
		String number = phone.get(who);
		System.out.println(who + " - " + number);
		checkContains("Andrew");
		checkContains("Jenny");
	}
}