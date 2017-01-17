public class PolySolve
{
	double a, b, c;
	public PolySolve(int a, int b, int c) {
		this.a = (double) a;
		this.b = (double) b;
		this.c = (double) c;
	}
	public double[] solve() {
		double[] out = new double[0];
		if(a != 0d){
			double optA = (-b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
			double optB = (-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
			if(!Double.isNaN(optA) && !Double.isNaN(optB)) {
				out = new double[2];
				out[0] = optA;
				out[1] = optB;
			}
			else if(!Double.isNaN(optA)){
				out = new double[1];
				out[0] = optA;
			}
			else if(!Double.isNaN(optB)){
				out = new double[1];
				out[0] = optB;
			}
		}
		else {
			out = new double[1];
			out[0] = -c / b;
		}
		return out;
	}
	public void display() {
		System.out.println("Equation: " + a + "x² "+ (b > 0 ? "+" : "-") + " " + Math.abs(b) + "x "+ (c > 0 ? "+" : "-") + " "+ Math.abs(c) + " = 0");
		double[] solutions = solve();
		if(solutions.length == 2){
			System.out.println("Solutions: " + solutions[0] + " and " + solutions[1]);
		}
		else if(solutions.length == 1){
			System.out.println("Solution: " + solutions[0]);
		}
		else{
			System.out.println("Solution: no roots found");
		}
	}
	public double[] add(PolySolve newEqn) {
		double oldA = a;
		double oldB = b;
		double oldC = c;
		a += newEqn.a;
		b += newEqn.b;
		c += newEqn.c;
		double[] out = solve();
		display();
		a = oldA;
		b = oldB;
		c = oldC;
		return out;
	}
}