package Boolean;
import java.lang.Boolean;
public class bool {
	public static void main(String[] args) {
		
		boolean m1 = true;
		boolean m2 = false;
		
		System.out.println("value of m1 :"+ m1);
		System.out.println("value of m2 :"+ m2);

        boolean m3 = !m1;
        System.out.println("value of m3 :"+ m3);
        
        int a = 0;
        boolean m4 = (a != 0);
        System.out.println("value of m4 :"+ m4);
        
        String v = "true";
        boolean m5 = Boolean.parseBoolean(v);
        System.out.println("value of m5 :"+ m5);
        
        v = "ghghghh";
        m5 = Boolean.parseBoolean(v);
        System.out.println("value of m5 :"+ m5);

	}
}
