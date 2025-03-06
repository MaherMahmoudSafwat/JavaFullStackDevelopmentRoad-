package Methods;

public class MethodsOverloading {

	public static void main(String[] args) 
	{
		System.out.println(Sum(1,2));
		System.out.println(Sum(3,3.5));
		System.out.println(Sum(4,5,7));
		extracted();
	}
	//Method Refactor, you go to refactor menu then extract menu then choose your name of your method then press ok.
	private static void extracted() {
		System.out.println(Sum(1,2));
		System.out.println(Sum(3,3.5));
		System.out.println(Sum(4,5,7));
	}
	//Method Overloading
	public static int Sum(int Number1,int Number2)
	{
		return Number1+Number2;
	}
	public static int Sum(int Number1,double Number2)
	{
		return Number1+(int)Number2;
	}
	public static int Sum(int Number1,int Number2,int Number3)
	{
		return Number1+Number2+Number3;
	}
}
