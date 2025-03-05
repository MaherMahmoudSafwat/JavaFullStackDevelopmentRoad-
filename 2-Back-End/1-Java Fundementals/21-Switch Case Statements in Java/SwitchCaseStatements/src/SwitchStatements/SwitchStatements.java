package SwitchStatements;

public class SwitchStatements {
	public static void main(String[] args) {
		int month = 2;
        String monthString;
        switch (month) {
        
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            //Default Statement is Optional.
            //You can put default in any place inside your code Switch Case Statements.
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);

	}

}
