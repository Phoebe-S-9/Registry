//A2
/*
* Phoebe Schulman
* 500973162
*/
//march 2020 //cps 209 assginment 2

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class StudentRegistrySimulator {
  	public static void main(String[] args){
	
		Registry registry; //catch any exceptions thrown. Print an appropriate message if exception thrown
		try{ //Catch Exceptions in main()
			registry = new Registry();
			
		}catch (BadDataException bf){ //BadDataException go first since exend IOException //not need excpetion class in main
			System.out.println("Bad File Format students.txt");
			return;
		} catch(IOException fnf){
			System.out.println("students.txt File Not Found"); 
			return;
		} 
		//System.out.println("registry" + registry);//debug

		TreeMap <String, ActiveCourse> registryCourses =registry.getRegistryCourses(); //ArrayList<ActiveCourse> registryCourses = registry.getRegistryCourses();
		//System.out.println("registryCourses " + registryCourses); //debug
			
		Scheduler sch = new Scheduler(registryCourses); // create a Scheduler object and pass in the courses arraylist/treemap //public Scheduler(TreeMap<String,ActiveCourse> courses) //public Scheduler(ArrayList<ActiveCourse>courses)
	
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
	
		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;
			
			Scanner commandLine = new Scanner(inputLine);
			String command = commandLine.next();
			
			if (command == null || command.equals("")) continue;
			
			else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) {
				registry.printAllStudents(); 

			} else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
				return;

			else if (command.equalsIgnoreCase("REG")) { //reg JohnBoy 11111 // register a student
				String testName = "",testID = "";
				
				if(commandLine.hasNext()){ testName = commandLine.next();} 
				if(commandLine.hasNext()){ testID = commandLine.next(); } 
				
				if(testID.equalsIgnoreCase("")){
					System.out.print("");
				}else if (isStringOnlyAlphabet(testName) && isNumeric(testID)){ 
					boolean outputTrue = registry.addNewStudent(testName, testID );
					if(!outputTrue){
						System.out.println("Student " + testName + " already registered");
					} 
					
				} else if (!isStringOnlyAlphabet(testName)) { 
					System.out.println("Invalid characters in name " + testName);
				}else { 
					System.out.println("Invalid characters in ID " + testID);
				}
				
				
			} else if (command.equalsIgnoreCase("DEL")) { //del 11111 // deletes a student from the registry
				String testID = ""; 
				if(commandLine.hasNext()){ testID = commandLine.next();}

				if (isNumeric(testID)){
					boolean outputTrue = registry.removeStudent(testID);
					if(!outputTrue){
						System.out.println("Student " + testID + " was not registered");		
					} 
					
				} else{
					System.out.println("Invalid characters in ID " + testID);
				}
					
				
			} else if (command.equalsIgnoreCase("ADDC")) { //addc 11111 cps209// adds a student to an active course
				String testID = "", testCourse = "";
				if(commandLine.hasNext()){ testID = commandLine.next();} 
				if(commandLine.hasNext()){ testCourse = commandLine.next();} 

				if (isNumeric(testID)&& !testCourse.equalsIgnoreCase("")){
					registry.addCourse(testID, testCourse); 
				} else if (!isNumeric(testID)){
					System.out.println("Invalid characters in ID " + testID);
				}

				
			} else if (command.equalsIgnoreCase("DROPC")) {	//dropc 11111 cps209 //drops a student from an active course
				String testID = "", testCourse = "";
				if(commandLine.hasNext()){ testID = commandLine.next();} 
				if(commandLine.hasNext()){ testCourse = commandLine.next();} 

				if (isNumeric(testID)&& !testCourse.equalsIgnoreCase("")){
					registry.dropCourse(testID, testCourse); 
				} else if (!isNumeric(testID)){
					System.out.println("Invalid characters in ID " + testID);
				}
				
			} else if (command.equalsIgnoreCase("PAC")) { //PAC // prints all active courses
				registry.printActiveCourses(); 				
	
			}else if (command.equalsIgnoreCase("PCL")){ //PCL cps209 //prints class list (students) for a specific active course
				String testCourse = "";
				if(commandLine.hasNext()){ 
					testCourse = commandLine.next();
					registry.printClassList(testCourse);
				}
				
			} else if (command.equalsIgnoreCase("PGR")){ //pgr cps209 // print id, name, grade of all students in a specific active course
				String testCourse = "";
				if(commandLine.hasNext()){ 
					testCourse = commandLine.next();
					registry.printGrades(testCourse); 
				}
			} else if (command.equalsIgnoreCase("PSC")) {//PSC 11111 //prints all credit courses for a student				
				String testID = "";
				if(commandLine.hasNext()){ testID = commandLine.next(); }
				if(isNumeric(testID)){
					registry.printStudentCourses(testID);
				} else{ 
					System.out.println("Invalid characters in ID " + testID);
				}
				
			
			} else if (command.equalsIgnoreCase("PST")){ //pst 11111 // print student transcript (all completed courses and grades of a student)
				String testID = "";
				if(commandLine.hasNext()){ testID = commandLine.next(); }
				if(isNumeric(testID)){
					registry.printStudentTranscript(testID);
				} else{ 
					System.out.println("Invalid characters in ID " + testID);
				}
			  
			}else if (command.equalsIgnoreCase("SFG")) { //sfg cps209 11111 99 //Set final grade of a student in a course
				String testCourse = "", testID="";
				double testGrade=-9.0; //defualt value

				if(commandLine.hasNext()){ testCourse = commandLine.next(); }
				if(commandLine.hasNext()){ testID = commandLine.next(); }
				if(commandLine.hasNextDouble()){ testGrade = commandLine.nextDouble();}

				if (isNumeric(testID)&& testGrade != -9.0){ 
					registry.setFinalGrade(testCourse, testID, testGrade );
				} else if(testGrade < 0 ||testGrade> 100 ){ 
					System.out.println("Invalid grade");
				}else{
					System.out.println("Invalid characters in ID " + testID);
				}
						
		  } else if (command.equalsIgnoreCase("SCN")){ //SCN cps209 //sort list of students in a course by student name (alphabetically) 
				if(commandLine.hasNext()){ 
					String testCourse = commandLine.next(); 
					registry.sortCourseByName(testCourse);
				}
				
				
			} else if (command.equalsIgnoreCase("SCI")){ //SCI cps209 //sort list of students in a course by student id (low to high) 
				if(commandLine.hasNext()){ 
					String testCourse = commandLine.next();
					registry.sortCourseById(testCourse);
				}

			//new commands------------------------------
			}else if (command.equalsIgnoreCase("SCH")){ //sch cps209 Mon 900 3 //set day and time 
				String courseCode= "", day = "";

				try{ //catch set day and time errors in main
					courseCode = commandLine.next();
					day = commandLine.next();
					int startTime = commandLine.nextInt(); 
					int duration = commandLine.nextInt();

					sch.setDayAndTime(courseCode,day,startTime,duration);
				
				}catch(UnknownCourseException invCourse) {
					System.out.println("Unkonwn course: "+ courseCode);  
				}catch (InvalidDayException invDay){
					System.out.println("Invalid Lecture Day");
				}catch (InvalidTimeException invT){	
					System.out.println("Invalid Lecture Start Time"); 
				}catch( InvalidDurationException invDur){
					System.out.println("Invalid Lecture Duration");
				}catch (LectureTimeCollisionException ltc){
					System.out.println("Lecture Time Collision");
				}
				

			}else if (command.equalsIgnoreCase("csch")){ //csch cps209 //clearSchedule: deletes a course
				if (commandLine.hasNext()){
					String courseCode = commandLine.next();
					sch.clearSchedule(courseCode);
				}

			}else if (command.equalsIgnoreCase("psch")){ //psch //printSchedule
				sch.printSchedule();

			} else{ //commands that are not recognized 
				System.out.println("not a valid command");
			}
			System.out.print("\n>");
		}
	}
  
	/** 
	* check if string str contains only alphabetic characters
	* @param str is string
	* @return true(only letters) or false (not all letters)
	*/
	private static boolean isStringOnlyAlphabet(String str) { 
		if (str.length() ==0) { return false; }
		for (int i = 0; i <str.length(); i++){
			if(!Character.isLetter(str.charAt(i))){ return false; } 
		}
		return true;
	} 
  
	/**
	 * check if string str contains only numeric characters
	 * @param str
	 * @return true(only numbers) or false (not all numbers or id not 5 chars long)
	 */
	public static boolean isNumeric(String str){ 		
		if (str.length() ==0){ return false; }
		if(str.length() != 5){ System.out.println("too long or short. 5 nums long"); return false;}
		for (int i = 0; i <str.length(); i++){
			if(!Character.isDigit(str.charAt(i))){ return false; } 
		}
		return true;
	}
}

//============================
/* 
cd C:\Users\Phoebe\Desktop\ASSIGN-2-cps209\A2-MY CODE
javac StudentRegistrySimulator.java
java StudentRegistrySimulator
*/
//============================
