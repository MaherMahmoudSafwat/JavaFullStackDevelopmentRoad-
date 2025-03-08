package DateTimeProject;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

public class DateTimeProject {

	public static Scanner Input = new Scanner(System.in);
	public static void main(String[] args)
	{
		while(true)
		{
		System.out.println("Welcome to date and time project please select one of the following:-");
		System.out.println("1-Print Default Date.");
		System.out.println("2-Print Calender.");
		System.out.println("3-Add Date and Print it.");
		System.out.println("4-Subtract Date and Print it");
		System.out.println("5-Change Date Time Zone");
		int UserInputs= Input.nextInt();
		if(UserInputs < 1 || UserInputs > 5)
		{
			System.out.println("The Input is Invalid, Please try again.");
			try {
				System.in.read();//To pause the system.
			} catch (IOException e) {
			}
			continue;
		}
		switch(UserInputs)
		{
		case 1:
			PrintTheSystemDate();
			break;
		case 2:
			PrintTheCalender();
			break;
		case 3:
			AddDateAndPrintIt();
			break;
		case 4:
			SubtractDateAndPrintIt();
			break;
		case 5:
			ChangeDateTimeZone();
			break;
		}
		}
	}
	public static void PrintTheSystemDate()
	{
		LocalDateTime DT = LocalDateTime.now();
		DateTimeFormatter FormatDateString = DateTimeFormatter.ofPattern("YYYY-MM-dd hh-mm-ss a");//a stans for AM and PM.
		String DateTime = DT.format(FormatDateString);
		System.out.println(DateTime);
		try {
			System.in.read();
		} catch (IOException e) {
		}
	}
	public static void PrintTheCalender()
	{
		ZellersCongruence();
	}
	public static void AddDateAndPrintIt()
	{
		System.out.println("Enter number of hours to add it.");
		int NumberOfHours = Input.nextInt();
		LocalDateTime DT = LocalDateTime.now();
		DT = DT.plusHours(NumberOfHours);
		System.out.println(DT);
	}
	public static void SubtractDateAndPrintIt()
	{
		System.out.println("Enter number of hours to Subtract it.");
		int NumberOfHours = Input.nextInt();
		LocalDateTime DT = LocalDateTime.now();
		DT = DT.minusHours(NumberOfHours);
		System.out.println(DT);
	}
	public static void ChangeDateTimeZone()
	{
	    // Get all available time zone IDs as a Set
        Set<String> timeZoneIds = ZoneId.getAvailableZoneIds();
        
        // Convert the Set to an ArrayList
        ArrayList<String> Lists = new ArrayList<>(timeZoneIds);
        
        // Create a ListIterator to iterate over the ArrayList
        ListIterator<String> List = Lists.listIterator();
        
        // Index variable to print the index
        int i = 0;
        
        // Variable to hold the current time zone string
        String S = null;
        
        // Iterate through the list of time zone IDs
        while (List.hasNext()) {
            // Get the next time zone ID
            S = List.next();
            
            // Print the index and time zone ID
            System.out.println((i + 1) + ": " + S);
            
            // Increment the index
            i++;
        }

        // Create a Scanner object to read user input
        Scanner Input = new Scanner(System.in);

        // Prompt user to choose a time zone
        System.out.println("Please choose one of the above time zones:");

        // Read the user input
        S = Input.nextLine();

        try {
            // Convert the string input to a ZoneId object
            ZoneId ZoneArea = ZoneId.of(S);
            
            TimeZone.setDefault(TimeZone.getTimeZone(ZoneArea));
            
            LocalDateTime DT = LocalDateTime.now();
            
            // Print the date and time in the selected time zone
            System.out.println("Current Date and Time in " + S + ": " + DT);
        } catch (Exception e) {
            // Handle invalid time zone input
            System.out.println("Invalid time zone ID. Please check the list and try again.");
        }
	}

	    public static void ZellersCongruence() {
	        String daysOfWeek = "Fri\tSat\tSun\tMon\tTue\tWed\tThu";

	        for (int month = 1; month <= 12; month++) {
	            LocalDate date = LocalDate.of(2025, month, 1);
	            DayOfWeek firstDayOfWeek = date.getDayOfWeek();
	            int daysInMonth = date.lengthOfMonth();

	            System.out.println("Month " + date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
	            System.out.println("-------------------------------------------------------------------------");
	            System.out.println(daysOfWeek);

	            // Print leading tabs for the first day of the month
	            int leadingTabs = (firstDayOfWeek.getValue() + 2) % 7; // Adjust to Fri=0, Thu=6
	            for (int i = 0; i < leadingTabs; i++) {
	                System.out.print("\t");
	            }

	            for (int day = 1; day <= daysInMonth; day++) {
	                System.out.print(day + "\t");
	                if (date.plusDays(day - 1).getDayOfWeek() == DayOfWeek.THURSDAY) {
	                    System.out.println(); // New line after Thursday
	                }
	            }
	            System.out.println("\n-------------------------------------------------------------------------\n");
	        }
	    }
	}












