package SwitchStatements;

public class SwitchEnumsCaseStatements {
	 public enum Day
	 {
	     SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
	     THURSDAY, FRIDAY, SATURDAY;
	 } 
	public static void main(String[] args) {
	
			  String str="SUNDAY";
			// 'Day' is an enum type that represents a fixed set of constants, such as MONDAY, TUESDAY, etc.
			// 'str' is a String variable, which contains the name of one of these constants as text.

			// Day.valueOf(str) is a method that converts the string value 'str' into the corresponding enum constant from the 'Day' enum.
			// It looks for an enum constant in 'Day' whose name exactly matches the string 'str' (case-sensitive).

			// If 'str' matches an enum constant (e.g., "MONDAY"), it returns the corresponding enum constant (e.g., Day.MONDAY).
			// The string 'str' must match the exact name of the enum constant, including case (e.g., "MONDAY" is valid, but "monday" is not).

			// If there is no match, meaning 'str' does not exactly match any of the enum constant names, 
			// the method throws an IllegalArgumentException.

			// The valueOf() method is commonly used to convert a string into an enum constant, typically when you want to map 
			// user input or external data to a specific enum constant dynamically.

			  switch (Day.valueOf(str))
			  {
			      case MONDAY: 
			       System.out.println("Day is Monday");
			       break;
			       
			      case TUESDAY:
			       System.out.println("Day is Tuesday");
			       break;
			       
			      case WEDNESDAY:
			       System.out.println("Day is Wednesday");
			       break;
			       
			      case THURSDAY:
			       System.out.println("Day is Thursday");
			       break;
			       
			      case FRIDAY:
			       System.out.println("Day is Friday");
			       break;
			       
			      case SATURDAY:
			       System.out.println("Day is Saturday");
			       break;
			       
			      case SUNDAY:
			       System.out.println("Day is Sunday");
			       break; 
			  }
	}
}
