package Characters;

public class Characters {
public static void main(String[] args) {
		

		char a1 = '1';
		char a2 = '2';
		char a3 = '3';
		char dollar = '\u0024';
		System.out.print(dollar);
		dollar = '$';
		//`\u0024` is the Unicode escape sequence for the dollar sign character '$' (ASCII value 36).
		System.out.print(dollar);
		System.out.print(a1);
		System.out.print(a2);
		System.out.println(a3);
		
		char v1 = 'a';
		char v2 = 'b';
		char v3 = 'c';
		System.out.print(v1);
		System.out.print(v2);
		System.out.println(v3);
		
	    //Character a ='b';This is allowed
		System.out.print(Character.toUpperCase(v1));
		System.out.print(Character.toUpperCase(v2));
		System.out.println(Character.toUpperCase(v3)); 
		// `\u0024` is the Unicode escape sequence for the dollar sign character '$' (ASCII value 36).
		System.out.println("Muhammed  Essa ");
		System.out.println("Muhammed \" Essa\"");
		System.out.println("Muhammed \' Essa\'");
		System.out.println("Muhammed \t Essa");
		System.out.println("Muhammed \n Essa");
		System.out.println("Muhammed \f Essa");
	    System.out.println("Muhammed \\ Essa");
	    System.out.println("Muhammed \r Essa");
	}
}
