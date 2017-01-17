package ex3;
import java.util.*;
public class Controller
{
	private static void printList(List<String> list)
	{
		for(String s: list) {
			System.out.println(s);
		}
		System.out.println();
	}
	public static void main(String[] args)
	{
		// Step 1
		String[] flyArray = {"Dragonfly", "Horsefly", "Greenfly", "Fruitfly", "Housefly"};
		List<String> flyList = Arrays.asList(flyArray);
		// Step 2
		System.out.println("FlyList:");
		printList(flyList);
		// Step 3
		List<String> colorList = new ArrayList<String>();
		colorList.add("Red");
		colorList.add("Green");
		colorList.add("Blue");
		colorList.add("Yellow");
		colorList.add("Green");
		System.out.println("ColorList:");
		printList(colorList);
		// Step 4
		Collections.sort(flyList);
		System.out.println("FlyList Sorted:");
		printList(flyList);
		// Step 5
		int index = Collections.binarySearch(flyList, "Greenfly");
		System.out.println("Greenfly Index: " + index + System.getProperty("line.separator"));
		// Step 6
		Collections.reverse(flyList);
		System.out.println("FlyList Reversed:");
		printList(flyList);
		// Step 7
		Collections.sort(colorList, Collections.reverseOrder());
		System.out.println("ColorList Reversed:");
		printList(colorList);
		// Step 8
		System.out.println("Green Frequency: " + Collections.frequency(colorList, "Green") + System.getProperty("line.separator"));
		// Step 9
		Collections.shuffle(colorList);
		System.out.println("ColorList Shuffled:");
		printList(colorList);
		// Step 10
	}
}