package ScannerInput;

import java.util.Scanner;

public class ScannerInput {
	 
	public static void main(String[] args) {
		
		// Create a Scanner object 'in' to read input from the console (System.in).
		// 'Scanner' is a class used to handle input from different sources.
		// 'in' is the variable name used to reference the Scanner object.
		// 'new' is used to instantiate a new Scanner object.
		// 'System.in' is the input stream that connects to the console, 
		//allowing the program to read user input from the keyboard.

		Scanner in = new Scanner(System.in);
		System.out.print("Enter your name :");
		String s = in.nextLine();
		System.out.println(s);
		System.out.print("Enter your age :");
		int num = in.nextInt();
		System.out.println(num);
		in.close();
	}
}
