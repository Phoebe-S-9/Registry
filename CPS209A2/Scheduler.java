//A2
/*
* Phoebe Schulman
* 500973162
*/
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.Arrays;
import java.lang.RuntimeException;
import java.util.Map; 
public class Scheduler{ 
		
	private TreeMap<String,ActiveCourse> schedule; //key: course name //value: the active course itself wiht its variables (lectureStart , lectureDuration , lectureDay ) //OR: //private ArrayList<ActiveCourse> schedule;
	//weekdays
	String blank = " ";//= "-"; 
	private String[] mon = {blank,blank,blank,blank,blank,blank,blank,blank,blank}; //time from 0800 to 1600
	private String[] tue = {blank,blank,blank,blank,blank,blank,blank,blank,blank};
	private String[] wed ={blank,blank,blank,blank,blank,blank,blank,blank,blank};
	private String[] thur = {blank,blank,blank,blank,blank,blank,blank,blank,blank};
	private String[] fri = {blank,blank,blank,blank,blank,blank,blank,blank,blank};

	/**
	 * A constructor method that takes a reference to a TreeMap/arraylist of active courses //from Registry courses to main to Scheduler
	 */
	public Scheduler(TreeMap<String,ActiveCourse> courses) { //treemap of (key)course name to (value) the active course //public Scheduler(ArrayList<ActiveCourse>courses){ 
		schedule = courses; //this treempap/arrylist = the registyr treempap/arraylsit of  active courses  	
		//System.out.println("schedule" + schedule);//debug
		//System.out.println(schedule.get("CPS209"));//reference to an active course
	}
	
	/**
	 * SCH: Schedules a course for a certain day, start time and duration
	 * error check before setting the variables lectureDay, lectureStart, lectureDuration of the corresponding ActiveCourse object. throw exceptions to main () 	 
	 •	UnknownCourse exception: the given courseCode cannot not be found
	 •	InvalidDay exception: the string parameter day should be one of "Mon", "Tue", "Wed", "Thur", "Fri".
	 •	InvalidTime exception: the startTime parameter should not be less than 0800 (8 am) and the end time of the lecture (based on the duration parameter) should not be greater than 1700 (5pm).
	 •	InvalidDuration parameter: the lecture duration should be 1, 2 or 3 hours.
	 •	LectureTimeCollision exception : the day, startTime, and duration should be such that it does not create any overlap with another scheduled course. 
	 * @param courseCode
	 * @param day lectureDay
	 * @param startTime lectureStart
	 * @param duration lectureDuration
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws UnknownCourseException,InvalidDayException, InvalidTimeException,	InvalidDurationException, LectureTimeCollisionException { //throws to main  //cant throws on a class - do on meth 			
		if (schedule.get(courseCode.toUpperCase()) == null){ //not found course code
			throw new UnknownCourseException();
		} else if(!(day.equalsIgnoreCase("mon") || day.equalsIgnoreCase("tue") || day.equalsIgnoreCase("wed") || day.equalsIgnoreCase("thur") || day.equalsIgnoreCase("fri") )){ 
			throw new InvalidDayException();
		} else if(!(startTime == 800 || startTime== 900 || startTime== 1000 || startTime== 1100 || startTime== 1200 || startTime== 1300 || startTime== 1400 || startTime== 1500|| startTime== 1600)){ 	
			throw new InvalidTimeException(); 
		}else if (!(1<=duration &&duration<= 3)){ //duration != 1 && duration != 2 && duration != 3
			throw new InvalidDurationException();
		}else if(startTime == 1400 && duration>3 ||startTime == 1500 && duration>2|| startTime == 1600 && duration>1 ){
			throw new InvalidDurationException(); //go off the board
		} else if (!checkCollision(courseCode, day, startTime, duration)){// check if spot open. pretend to place it down. if no spot then error
			throw new LectureTimeCollisionException(); 		
		}
		schedule.get(courseCode.toUpperCase()).setLecture(day, startTime,duration); //a.setLecture //valid. there was no error. so can set lectureStart, lectureDuration, lectureDay (FROM ActiveCourse)
	}
	

	/**
	* csch: deletes a course. Clears the schedule of the given course
	* @param courseCode a course code
	*/	
	public void clearSchedule(String courseCode) { 
		//boolean found = false; //debug
		ActiveCourse a = schedule.get(courseCode.toUpperCase());
		if(a != null && a.getCode().equalsIgnoreCase(courseCode)){ //found course and key equals passed var
			//System.out.println("true"); found = true; //debug
			a.resetLecture(); //resets the lectureDay to "", lectureStart to 0 and lectureDuration to 0 of the ActiveCourse object

			//remove from the board
			String[] weekday={}; 
			for (int j =1; j <=5; j ++){ //all the days 1 to 5
				if (j == 1){ weekday = mon;
				}else if (j == 2){ weekday = tue; 						
				}else if (j == 3){ weekday = wed; 						
				}else if (j == 4){ weekday = thur; 						
				}else if (j == 5){ weekday = fri; 						
				}

				for (int k = 0; k< weekday.length; k++){ //all the elems 1 to 9
					if (weekday[k].equalsIgnoreCase(courseCode)){ //any elemnet = course code
						String blank = " "; //"-";
						weekday[k] = blank; //that elem = blank to reset
					}
				}
			}
		} //else if (!found){System.out.println("no") ;} //do nothing //debug
	}
		

	/**
	 * psch: Prints the entire schedule/ timetable  
	 */
	public void printSchedule(){ 
		String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri"};
		int counter = 0; //go up to 4
		
		for(int i = 0; i < 10;i ++){ //print first row: week days
			if (i %2 ==0) { System.out.print("\t");
			}else { 
				System.out.print(weekdays[counter]);
				counter ++;
			}
		}

		System.out.println();//print other rows
		int lineCount = 0;

		for (int i = 0;i < 9; i ++){ //rows //800 to 1600
			for (int j = 0; j < 11; j ++){  //columns //mon - fri
				if (j%2  !=0){ System.out.print("\t");
				} else{
					if (j ==0) { //time on side
						int num = 800 + lineCount;
						if(num<1000){ System.out.print("0"+num);
						}else{System.out.print(num); 
						}
						lineCount +=100;
					}else{ //find the right course code to print off at right time //print colum1(mon), row by row. then col 2(tue), row by row. etc.
						if (j == 2){ System.out.print(mon[i]);}
						if (j == 4){ System.out.print(tue[i]);}
						if (j == 6){ System.out.print(wed[i]);}
						if (j == 8){ System.out.print(thur[i]);}
						if (j == 10){ System.out.print(fri[i]);}
					}
				}	
			}
			System.out.println("\n----------------------------------------------");
		}
	/*
	/t	/t	Mon		Tue		Wed		Thu		Fri
	0	1 	2 	3 	4 	5 	6 	7 	8	9 	10
	1
	2
	3
	4
	5
	6
	7
	8
	9
	*/
	}

	//ADDED METHODS---------
	/**
	 * check for collision
	 * @param courseCode a course code
	 * @param day a weekday
	 * @param startTime a start time
	 * @param duration a duration of the class
	 * @return true (no collsion, so can place) or false (there was a collsion, so cant be placed)
	 */
	public boolean checkCollision(String courseCode, String day, int startTime, int duration){ 
		String blank = " "; //"-";
		String[] testDay ={blank,blank,blank,blank,blank,blank,blank,blank,blank}, weekDay = {}; 
		int startIndex=0;

		if (day.equalsIgnoreCase("mon")){ testDay =  mon;
		} else if (day.equalsIgnoreCase("tue")){ testDay =  tue;
		}else if (day.equalsIgnoreCase("wed")){ testDay =  wed;
		}else if (day.equalsIgnoreCase("thur")){ testDay =  thur; //thu when printing and thur when typing 
		}else if (day.equalsIgnoreCase("fri")){ testDay =  fri;
		}
		
		//System.out.println("testDay");for(int i =0; i < testDay.length;i++){ System.out.println(testDay[i]); } //debug

		for(int i = 800; i<= 1600; i +=100){ //08:00 to 16:00
			if (startTime ==i){ 
				//System.out.println("i" + i + " startIndex " + startIndex); //debug
				break;
			}
			startIndex++;
		}	

		Set<String> courseSet = schedule.keySet();
		for(int i = startIndex; i < startIndex+duration; i++){ //check if occuiped for the duration 
			for(String j: courseSet){ //j is course code/key //look at all courses
				if(testDay[i].equalsIgnoreCase(schedule.get(j).getCode())) { //if a course code is found  //ActiveCourse.getCode //same as 	//if(testDay[i].equalsIgnoreCase(schedule.get(courseCode.toUpperCase()).getCode())){ 
					if(!testDay[i].equalsIgnoreCase(courseCode)){ // and its not itself //since a course can overlap itself
						//System.out.println("no"); //System.out.println("testDay[i] " + testDay[i]);//debug
						return false; //error
					}
				}
			}
		}
			
		//NOTE: only allow a course to be scheduled for a single block of time during the week. // If it is already scheduled, then just over write the current schedule. 
		clearSchedule(courseCode); //make sure no duplicates //csch: looks at each week day on the board. if found course, clear old time slot. then place new one

		weekDay = testDay; //put the course in weekday array if valid
		for(int i = startIndex; i < startIndex+duration; i++){ 
			weekDay[i] = courseCode;
		}
		
		//System.out.println("weekday"); for(int i =0; i < weekDay.length;i++){ System.out.println(weekDay[i]); } //debug
		
		//change the right instant var
		if (day.equalsIgnoreCase("mon")){ mon = weekDay;
		}else if (day.equalsIgnoreCase("tue")){ tue = weekDay;
		}else if (day.equalsIgnoreCase("wed")){ wed = weekDay;
		}else if (day.equalsIgnoreCase("thur")){ thur = weekDay;//thu when printing  and thur when typing 
		} else if (day.equalsIgnoreCase("fri")){ fri = weekDay;
		}
		return true; //course was added 
	}	
}


//erorr classes defined. for set day and time exceptions
class UnknownCourseException extends RuntimeException{ 
	public UnknownCourseException(){}
}
class InvalidDayException extends RuntimeException{
	public InvalidDayException(){}
}
class InvalidTimeException extends RuntimeException{
	public InvalidTimeException(){}
}
class InvalidDurationException extends RuntimeException{
	public InvalidDurationException(){}
}
class LectureTimeCollisionException extends RuntimeException{
	public LectureTimeCollisionException(){}
}
