public class Ex7
{
	public static void main(String[] args)
	{
		PolySolve eqn = new PolySolve(0, 10, 5);
		eqn.display();
		PolySolve eqn2 = new PolySolve(3, -14, -10);
		eqn.add(eqn2);
	}
}