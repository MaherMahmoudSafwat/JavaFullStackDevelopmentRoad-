package StringsAndCharacters;

public class StringMethods {

	public static void main(String[] args) {
		String m = "Muhammed Essa Hameed";
	     
	     System.out.println("length :"+m.length());
			
	     int postion = m.indexOf("Essa");
	     System.out.println("postion :"+postion);
	     String pos = m.substring(9);
	     System.out.println(pos);
	     String n = "Ali                  ";
	     System.out.println(n.length());
	     String b = n.trim();
	     System.out.println(b.length()); 
	}

}
