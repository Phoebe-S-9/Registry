//A2
/*
* Phoebe Schulman
* 500973162
*/
import java.util.ArrayList;

public class Student implements Comparable<Student> // Student implements the Comparable interface //2 student objects are compared by their name 
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses; //courses: array list of CreditCourse objects// keeps track of the courses the student finished
  
  /**
   * constructor for a Student name,id, CreditCourses list
   * @param name student name
   * @param id student id
   */
  public Student(String name, String id)
  {
    this.name = name;
    this.id   = id;
    courses   = new ArrayList<CreditCourse>();
  }
  
  /**
	* returns student id
	* @return id
	*/  
  public String getId()
  {
    return id;
  }
  
  /**
	* returns student name
	* @return name
	*/  
  public String getName()
  {
    return name;
  }
  
  /**
   * add a credit course to list of courses for this student
   * @param courseName course name
   * @param courseCode course code
   * @param descr course description
   * @param format course format
   * @param sem  semester
   * @param grade initial grade(0.0)
   */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {    
    CreditCourse creditCourse = new CreditCourse( courseName, courseCode,  descr,  format, sem,  grade); // create a CreditCourse object
    creditCourse.setActive();   //set course to active 
    courses.add(creditCourse); // add to courses arraylist
  }
     
  /**
   * Prints a student transcript. shows all completed (non active) courses for this student //(course code, course name, semester, letter grade) 
   */
  public void printTranscript()
  {
    //System.out.println("Student.transcript: "); //debug
    for (int i = 0; i < courses.size(); i ++){
      CreditCourse c = courses.get(i);
      if (!c.getActive()){  //non active
        System.out.println(c.getCode() +  " " + c.getName() + " " +  c.displayGrade()); //Course.getname -- a course name //Course.getCode -- a course code //Creditcourse.displayGrade
      }
    }
  }
  
  /**
   * Prints all active courses this student is enrolled in //psc
   */
  public void printActiveCourses() {
    //System.out.println("Student.print active courses: "); //debug
    for(int i = 0; i < courses.size(); i ++){ //go through all the courses. if it is active (by the student). then print its info 
      CreditCourse c = courses.get(i); //credit course = that current active course 
      if(c.getActive()){ //CreditCourse.getActive
        System.out.println(c.getDescription()); //Course.getDescription 
      }
    }
  }
  
     
  /**
   * Drop a course (given by courseCode) // Find the credit course in courses arraylist above and remove it// only remove it if it is an active course
   * @param courseCode a course code
   */
  public void removeActiveCourse(String courseCode) 
  {
    for( int k = 0; k < courses.size(); k ++){ //CreditCourse "courses"
      CreditCourse c= courses.get(k);
      if (c.getCode().equalsIgnoreCase(courseCode) && c.getActive()){ //found the active course
        courses.remove(c);
        //System.out.println("removed"); //debug
      }
    }
  }
  
  /**
   * output a string
   * @return String of student id and name
   */
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  
  /**
   * compare name or id is equal
   * @param other
   * @return true (if equal) or false(if not equal)
  */
  // override equals method inherited from superclass Object // if student names are equal *and* student ids are equal (of "this" student and "other" student) then return true // otherwise return false
  public boolean equals(Object other)
  {
    Student referenceVar = (Student) other; //casting Object other to a Student variable
    //System.out.println("referenceVar\t " +  referenceVar.getName() + " " +  referenceVar.getId() + "\nthis\t\t " + this.name + " " + this.id);  //debug

    if(name.equalsIgnoreCase("0")){ //remove student by checking default name "0" and id  
      //System.out.println(this.id.equals( referenceVar.getId())); //debug
      return this.id.equals(referenceVar.getId()); 
    }else{ //add student by checking name and id 
      //System.out.println(this.name.equals(referenceVar.getName()) && this.id.equals( referenceVar.getId())); //debug
      return this.name.equals( referenceVar.getName()) && this.id.equals(referenceVar.getId());  
    }    
  }
  
  //ADDED METHODS---------
  /**
   * pgr: help reach the CreditCourse instance variable "grade" 
   * @param courseCode a course code
   * @return a numeric grade
   */
  public double getStudentGrade(String courseCode){
    //System.out.println("Student.getStudentGrade");  //debug
    for(int j = 0; j < courses.size();j ++){ //look through their acitve courses
      CreditCourse c = courses.get(j);
      if(c.getCode().equalsIgnoreCase(courseCode)){ 
         return c.getCreditGrade(); //CreditCourse.getCreditGrade()
      }
   }
    return 0.0;
  }

  /**
   * sfg: help reach the CreditCourse instance variable "grade"
   * @param courseCode,grade a course code, a grade
   */
  public void sfgHelper(String courseCode, double grade){
    for(int k = 0; k<courses.size(); k ++){ //CreditCourses.courses
      CreditCourse c = courses.get(k);
      if (c.getCode().equalsIgnoreCase(courseCode)){ // search credit course list in student object and find course	// set the grade in credit course and set credit course inactive
        c.setGrade(grade); //CreditCourse.setGrade()
        c.setInactive();
      }
    }
    //System.out.println("(Student) grade set"); //debug
  }

  /**
   * compares names
   * @param other another student object
   * @return number based on the order of strings
   */
  public int compareTo(Student other){ //calls String.compareTo
    if(this.getName().compareTo(other.getName())>=-1){ return 1;
    }else if(this.getName().compareTo(other.getName()) <= 1){ return -1;
    }
    return 0;
  }
}
