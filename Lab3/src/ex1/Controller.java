package ex1;
public class Controller
{
	public static void main(String[] args)
	{
		try{
			Student homeStud1 = new Student("Alan", 1);
			System.out.println(homeStud1.getName() + " number " + homeStud1.getNum());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			Student homeStud2 = new Student("Jenny", 2);
			System.out.println(homeStud2.getName() + " number " + homeStud2.getNum());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			Student homeStud3 = new Student("Jane", 0);
			System.out.println(homeStud3.getName() + " number " + homeStud3.getNum());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			Student homeStud4 = new Student("James", 10001);
			System.out.println(homeStud4.getName() + " number " + homeStud4.getNum());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			OverseasStudent overStud1 = new OverseasStudent("Pierre", 1235, "France");
			System.out.print(overStud1.getName() + " number " + overStud1.getNum() + " ");
			System.out.println("Country " + overStud1.getCountry());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			OverseasStudent overStud2 = new OverseasStudent("Klaus", 1234, "Germany");
			System.out.print(overStud2.getName() + " number " + overStud2.getNum() + " ");
			System.out.println("Country " + overStud2.getCountry());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}