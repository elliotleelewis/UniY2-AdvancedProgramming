public class Ex6
{
	public static void main(String[] args)
	{
		PolySolve eqn = new PolySolve(2, -3, -4);
		double[] solutions = eqn.solve();
		if(solutions.length == 2){
			System.out.println("Solutions: " + solutions[0] + " and " + solutions[1]);
		}
		else{
			System.out.println("Solution: no roots found");
		}
	}
}