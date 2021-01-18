import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the courses ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	TreeMap<String,ActiveCourse> schedule;
		
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	}
	
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		// see assignment doc
	}
	
	
	
	public void clearSchedule(String courseCode)
	{
		// see Assignment doc
	}
		
	public void printSchedule()
	{
		// see assignment doc
	}
	
}

