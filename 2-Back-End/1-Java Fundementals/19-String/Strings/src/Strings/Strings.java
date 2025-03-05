package Strings;

import java.util.Date;
public class Strings 
{
	public static void main(String[] args) {
		
		char c = 'z';
		boolean bool = true;
		byte b = 127;
		short s = 32000;
		int i = 2000000;
		long l = 16500000L;
		float f = 1234245.435234f;
		double d = 112312312331.34d;
		
		String S = i + "s";//This is Allowed.
		
		System.out.println(c);
		System.out.println(bool);
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(f);
		System.out.println(d);
		System.out.println("The value of s : "+ s);
		System.out.println(s+"The value of s : ");
		System.out.println(2+2+"The value of s : ");
		
		Date nDate = new Date();
		System.out.println("the Date is : "+nDate);

	}
}
