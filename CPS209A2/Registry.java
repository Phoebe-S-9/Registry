//A2
/*
* Phoebe Schulman
* 500973162
*/

import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class Registry {
   //Registry students: list of ALL students registered at university //Registry courses: list of ALL active courses at uni
   private TreeMap <String, Student> students = new TreeMap<String, Student>(); //Use the student id (string) as the key 
   private TreeMap<String, ActiveCourse> courses = new TreeMap<String, ActiveCourse>(); //use the course code (string) as the key //this courses passed to main. passed to schdualer
   
   /**
	* constructor. Tries to initialize Student objects from a file. Initialize courses. enroll dif students in a course.
	* @throws IOException if file not found
	* @throws BadDataException if each student not have a name and id
    */
   public Registry() throws IOException, BadDataException{ 
		Student s1 = null, s2 = null, s3 = null, s4 = null, s5 = null, s6 = null; // Add some students 
		Scanner input = new Scanner (new File("students.txt")); //student names and ids are read from students.txt
		int counter = 1; //initialize the first 6 names
		
		try{ 
			while(input.hasNextLine()){ //while theres more lines in file
				String line = readLine(input); //throws an error when missing a name or id
				//System.out.println(line);//debug
				
				String studName = "", studId = "";
				for(int i=0; i < line.length(); i ++){
					if(Character.isLetter(line.charAt(i))){ //add name letter by letter
						studName+= line.charAt(i);
					}else if (Character.isDigit(line.charAt(i))){ //add id num by num
						studId += line.charAt(i); 
					}
				}
				
				//afte read a student name and id from the file, create a Student object and add it to the TreeMap/ ArrayList 
				Student s = new Student(studName,studId); 
				students.put(studId, s); //key is id. value is the student
				
				Student currentS = students.get(studId); //set a specific student (s1 to s6) so they can be enrolled in thier right courses	
				if(counter ==1){ s1 = currentS; 
				}else if(counter ==2){ s2 = currentS;
				}else if(counter ==3){ s3 = currentS;
				}else if(counter ==4){ s4 = currentS;
				}else if(counter ==5){ s5 = currentS;
				}else if(counter ==6){ s6 = currentS;
				}
				//System.out.println("sn " + studName); System.out.println("si " + studId); System.out.println("s " + s); //debug
				counter ++; 
			}
			//System.out.println("students " + students);//debug
		
			/*
			Set<String> studIds = students.keySet();
			ArrayList<String> result = new ArrayList<>();
			for(String anId: studIds){
				result.add(students.get(anId)); //error: .add(string)
			}
			Collections.sort(result);// sort the students alphabetically (after settting them) 
			*/

			ArrayList<Student> list = new ArrayList<Student>();

			// Add some active courses with students
			String courseName = "Computer Science II";
			String courseCode = "CPS209";
			String descr = "Learn how to write complex programs!";
			String format = "3Lec 2Lab";
			list.add(s2); list.add(s3); list.add(s4);
			courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list)); 

			// Add course to student list of courses
			s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			s3.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 

			// CPS511
			list.clear();
			courseName = "Computer Graphics";
			courseCode = "CPS511";
			descr = "Learn how to write cool graphics programs";
			format = "3Lec";
			list.add(s1); list.add(s5); list.add(s6);
			courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"F2020",list)); 
			s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			s5.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			s6.addCourse(courseName,courseCode,descr,format,"W2020", 0);

			// CPS643
			list.clear();
			courseName = "Virtual Reality";
			courseCode = "CPS643";
			descr = "Learn how to write extremely cool virtual reality programs";
			format = "3Lec 2Lab";
			list.add(s1); list.add(s2); list.add(s4); list.add(s6);
			courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list)); 
			s1.addCourse(courseName,courseCode,descr,format,"W2020", 0);  
			s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
			s6.addCourse(courseName,courseCode,descr,format,"W2020", 0); 

			// CPS706
			courseName = "Computer Networks";
			courseCode = "CPS706";
			descr = "Learn about Computer Networking";
			format = "3Lec 1Lab";
			courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
			
			// CPS616
			courseName = "Algorithms";
			courseCode = "CPS616";
			descr = "Learn about Algorithms";
			format = "3Lec 1Lab";
			courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list)); 
			//System.out.println("courses " + courses); //debug
		}finally{
			input.close(); //close file scanner
		}
	}
   
	/**
	 * reg: Add new student to the students treemap (for Registry) if student is not already in registry 
	 * @param name student name
	 * @param id student id
	 * @return if student added (true) or not (false)
	 */
   public boolean addNewStudent(String name, String id) { 
	   	Student stud = new Student(name, id); // Create student object
		if(students.put(id, stud)== null){ return true; } // .put(id, stud) outputs null or the value overridden 
		return false; //not added. but overide itself
   }

	/**
	* del: Remove student from registry 
	* @param studentId student id
	* @return true(can remove student) or false (cant remove student)
    */
   public boolean removeStudent(String studentId) { 
		Student stud = new Student("0", studentId); //0 is a default name (that cant be entered)
		if(students.remove(studentId) != null) { return true; } //found and removed a student in students 
		return false; 
   }
   
   /**
	* L: Print all registered students
    */
   public void printAllStudents() {
		Set<String> studSet = students.keySet(); 
		for(String i: studSet){ //i is an id/key
			Student s = students.get(i); 
			System.out.println("ID: " + s.getId() + " Name: " + s.getName() ); 
			//System.out.println(i + " " + s); //debug
		}
   }
   
   /**
	* addc:add student to the active course
	* @param studentId,courseCode student's id and a course code
    */
   public void addCourse(String studentId, String courseCode) { 
		ActiveCourse a = courses.get(courseCode.toUpperCase()); //a = courses.get("cps209"); //a is null //VS //a = courses.get("CPS209"); // a is not null
		Student s = students.get(studentId);
		//System.out.println("a" + a); System.out.println("s" + s);//debug

		if(a != null && s != null){ //found course and student in Registry 
			for(int i = 0; i <a.getActiveCourseStudents().size(); i++ ){ //check if a particular student is enrolled in course code //look thru the ActiveCourse students arraylist 
				Student sCheck = a.getActiveCourseStudents().get(i); 
				if(sCheck.getId().equalsIgnoreCase(studentId)){ //student found
					System.out.println("already in course");
					return;
				}
			}
			
			//else add them 
			a.getActiveCourseStudents().add(s);
			s.addCourse(a.getName(), a.getCode(), a.getDescription(), a.getFormat(), a.getSemester(), 0.0);
			//System.out.println("Student added"); //debug
			return;	
		}
		//System.out.println("neither"); //debug
   }
   
   /**
	* dropc: drop student from the active course
	* @param studentId,courseCode a studentId and a course code
    */
   public void dropCourse(String studentId, String courseCode) { 
		if(courses.get(courseCode.toUpperCase())!= null) { //if found,drop the course
			courses.get(courseCode.toUpperCase()).dropcHelper(studentId,courseCode);
			//System.out.println("yes"); //debug
			return ;
		}
		//System.out.println("no");//debug
   }
	
   
   /**
	* pac:Print all active courses
    */
   public void printActiveCourses(){  
	   Set<String> courseSet = courses.keySet();
	   for(String c: courseSet){
		   String v = courses.get(c).getDescription();
		   System.out.println(v + "\n");
	   }
   }
   
   /**
	* pcl: Print the list of students in an active course
	* @param courseCode a course code
    */
   public void printClassList(String courseCode) {
	   //System.out.println("\nreg.printClassList"); //debug
	   goingThroughCoursesArray(4, courseCode);
   }
   
   /**
	* scn: find a course and sort class list by student name
	* @param courseCode a course code
    */
   public void sortCourseByName(String courseCode) {
		goingThroughCoursesArray(1,courseCode);
   }
   
   /**
	* sci: find a course and sort class list by student name
	* @param courseCode a course code
    */
   public void sortCourseById(String courseCode) {  
		goingThroughCoursesArray(2,courseCode);
   }
   
   /**
	* pgr: find a course and print student names and grades
	* @param courseCode a course code
    */
   public void printGrades(String courseCode) {
		goingThroughCoursesArray(3,courseCode);
   }
   
   /**
	* psc: print all active courses of a student
	* @param studentId a student Id
    */
   public void printStudentCourses(String studentId) {	
		PSCAndPST(1,studentId);
   }
   
   /**
	* pst: print all completed courses and grades of a student
	* @param studentId a student Id
    */
   public void printStudentTranscript(String studentId) {
		PSCAndPST(2,studentId);
   }

   /**
	* sfg: set the final grade of a student
	* @param courseCode a course code
	* @param studentId a student Id
	* @param grade a numeric grade
    */
   public void setFinalGrade(String courseCode, String studentId, double grade){ 
		Student s = students.get(studentId);
		if(courses.get(courseCode.toUpperCase()) != null && s != null){ //found the course and student
			s.sfgHelper(courseCode,grade); //set the grade using CreditCourse courses
			//System.out.println("sfg yes"); //debug
			return;
		}
		//System.out.println("sfg no");//debug
   }

   	//ADDED METHODS---------------
	/**
	 * go thorugh the Registry "courses" arraylist (its now a treemap)
	 * @param num a number to indicate the method to run: 1= scn, 2 = sci, 3 = pgr, 4  =pcl
	 * @param courseCode a course code
	 */
	private void goingThroughCoursesArray(int num, String courseCode ){  
		ActiveCourse a = courses.get(courseCode.toUpperCase()); //find course 
		//System.out.println("a " + a); //debug
		if(a == null){ 
			System.out.println("course code not found"); //debug
			return; 
		}

		if(num ==1){ a.sortByName(); 
		} else if(num ==2){ a.sortById(); 
		} else if(num ==3){ a.printGrades(courseCode); 
		} else if (num ==4){ a.printClassList(); //print class list of the specific course
		}
	}

	/**
	 * go thorugh the Registry "students" tree map
	 * @param num a number to indicate the method to run: 1 = psc, 2 = pst
	 * @param studentId a student Id
	 */
	private void PSCAndPST(int num, String studentId ){ 
		Student stud = students.get(studentId);
		if(stud !=null){ //found student
			if(num ==1){ stud.printActiveCourses(); 
			}else if(num ==2){ stud.printTranscript(); 
			}
		}
	}

	//------------A2 METHODS
	/**
	 * allows main() to access Registry courses 
	 * @return treemap of all the courses
	 */
	public TreeMap<String, ActiveCourse> getRegistryCourses(){ //public ArrayList<ActiveCourse> getRegistryCourses(){ 
		return courses; 
	}

	/**
	 * helper method that reads a file one line at a time
	 * @param input a file
	 * @return a line in the file 
	 * @throws BadDataException if a line doesnt contain the student name and id
	 */
	public String readLine(Scanner input) throws BadDataException{ 		
		String line = "";

		if(!input.hasNext()){ //check if theres a name (first word)
			//System.out.println("throwing"); //debug 
			throw new BadDataException(); //no name
		}
		line = input.next(); //get name

		if(!input.hasNext()){ //check if theres an id (second word)
			//System.out.println("throwing"); //debug 
			throw new BadDataException(); //no id 
		}
		return line+= input.next(); //get id
	}
}

/**
 * defines the execption for a file with incorrect input. expects a name and id
 */
class BadDataException extends IOException{ 
	public BadDataException(){}
}
