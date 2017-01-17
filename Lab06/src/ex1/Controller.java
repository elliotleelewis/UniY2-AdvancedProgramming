package ex1;
import java.util.*;
public class Controller
{
	public static void main(String[] args)
	{
		ArrayList<String> insects = new ArrayList<String>();
		insects.add("Horsefly");
		insects.add("Butterfly");
		insects.add("Dragonfly");
		insects.add("Fly");
		for(int i = 0; i < insects.size(); i++) {
			System.out.println("Insect " + i + ": " + insects.get(i));
		}
	}
}