package Methods;

public class Methods {
public static void main(String[]args)
{
	//Methods With no Arguments.
	DoSomething();
	//Methods With Arguments.
	PrintTheString("I Love Java");
	//-----------------------------
	int Number = ReturnNumber();
	System.out.println(Number);
	System.out.println(ReturnNumberWithParameters(Number));
	
}
//**Static inner classes**: 
//Static nested classes do not need an instance of the outer class to be instantiated. They can be used when the inner 
//class does not need access to the outer class's instance variables or methods.
//This helps in logically grouping classes together without needing to create instances of the outer class.
public static void DoSomething()
{
	System.out.println("My name is Maher Mahmoud Safwat");
}
public static void PrintTheString(String t)
{
	System.out.println(t);
}
public static int ReturnNumber()
{
	return 1;
}
public static int ReturnNumberWithParameters(int Number)
{
	return Number;
}
}
