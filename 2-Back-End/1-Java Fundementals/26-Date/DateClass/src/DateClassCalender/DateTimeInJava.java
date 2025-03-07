package DateClassCalender;
import java.time.LocalDate;         // For handling dates (year, month, day)
import java.time.LocalTime;         // For handling time (hour, minute, second)
import java.time.LocalDateTime;     // For handling both date and time
import java.time.format.DateTimeFormatter;  // For formatting dates and times
import java.time.ZoneId;            // For working with different time zones
import java.time.ZonedDateTime;     // For handling dates and times with time zones
import java.time.Duration;          // For handling durations
import java.time.Period;            // For handling periods (years, months, days)
import java.util.TimeZone;          //For handling Default Time Zone
public class DateTimeInJava 
{
	public static void main(String[]args)
	{
		LocalDate Date = LocalDate.now();
		System.out.println("Date is : " + Date);
		LocalTime Time = LocalTime.now();
		System.out.println("Time is : " + Time);
		LocalDateTime DateTime = LocalDateTime.now();
		System.out.println("Local Date and Time is " + DateTime);
		//DateTimeFormat is a reference refers to object static method in DateTimeFormatter.
		//Since method classes which has static methods don't need to use the new keyword.
		DateTimeFormatter DateTimeFormat = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:SS");
		String FormattedDate = DateTime.format(DateTimeFormat);
		System.out.println(FormattedDate); 
		
		ZoneId ZoneArea = ZoneId.systemDefault();
		System.out.println(ZoneArea);
	    Time = LocalTime.now();
		ZoneArea = ZoneId.of("America/New_York"); 
	    TimeZone ChangeCurrentTimeZone = TimeZone.getTimeZone(ZoneArea);
	    Time = LocalTime.now();
	    System.out.println(Time);
	    
	    ZonedDateTime DT = ZonedDateTime.now(ZoneArea);
	    Time = DT.toLocalTime();
	    System.out.println("Time in America New_York is : "+ Time);
	    
	    Duration D = Duration.ofHours(Time.getHour());
	    System.out.println(D);
	    System.out.println(D.plus(D));
	    
	    Period P = Period.of(2025, 7,3);
	    System.out.println(P);
	    
	    }
}
