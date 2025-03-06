package StringsAndCharacters;

public class StringsAndCharacters {

public static void main(String[] args) {
		

		String s1 = "Muhammed Essa";
		
		String s3 = new String("MUHAMMED Essa");
		System.out.println(s3);
        if(s1.equalsIgnoreCase(s3)){
        	System.out.println("They Match !");
        }else{
        	System.out.println("No Match !!");
        }
        char[] ch = s1.toCharArray();
        for (char c : ch) {
        	System.out.println(c);
		}
	}
}
