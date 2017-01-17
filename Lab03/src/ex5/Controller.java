package ex5;
import java.util.Random;
public class Controller
{
	public static void main(String[] args)
	{
		Random random = new Random();
		DrawingEntity[] array = new DrawingEntity[5];
		for(int i = 0; i < array.length; i++) {
			switch(random.nextInt(4)) {
				case 0:
					array[i] = new DrawingEntity();
					break;
				case 1:
					array[i] = new Line();
					break;
				case 2:
					array[i] = new Circle();
					break;
				case 3:
					array[i] = new Box();
					break;
			}
		}
		for(DrawingEntity ent: array) {
			ent.display();
		}
	}
}