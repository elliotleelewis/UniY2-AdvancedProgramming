import java.util.Random;
public class Ex3
{
	public static void main(String[] args)
	{
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < 10; i++) {
			System.out.println(random.nextInt(50));
		}
	}
}