package DateClassCalender;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateClassCalender {
	    public static void main(String[] args) {
	        // To get the current date use Date class
	        Date Dt = new Date();  // Creates a Date object that represents the current date and time.
	        System.out.println("Current Date: " + Dt);  // Prints the current date and time

	        // Get the current hour using Calendar (instead of using the deprecated getHours())
	        System.out.println("Current Hour: " + Dt.getHours());  // This is deprecated, but still works. We can later use Calendar or LocalTime.

	        // To manipulate the date, use GregorianCalendar.
	        // Create a GregorianCalendar object for March 7, 2025 (note that months are zero-based).
	        GregorianCalendar Gc = new GregorianCalendar(2025, 2, 7); // 2 means March (March is 2, not 3)
	        
	        // Add 1 day to the current date.
	        Gc.add(GregorianCalendar.DATE, 1);  // Adds one day to March 7, 2025, making it March 8, 2025.
	        
	        // Update the Date object with the new date.
	        Dt = Gc.getTime();  // Gets the updated date from the GregorianCalendar and assigns it to Dt.
	        
	        // Print the updated date.
	        System.out.println("Updated Date: " + Dt);  // This will print March 8, 2025.
	        
	        Date Dt2 = new Date();
	        Gc.add(GregorianCalendar.DAY_OF_YEAR, 5);
	        Dt2 = Gc.getTime();
	        System.out.println(Dt2);
	        }

}
