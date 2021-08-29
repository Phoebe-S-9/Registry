//A2
/*
* Phoebe Schulman
* 500973162
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course 
public class ActiveCourse extends Course{ // ActiveCourse extends class Course (all variables and methods inherited from  Course)
	private ArrayList<Student> students; //this students arraylist for a specific course
   private String             semester;
   
   //These variables will be set/used by the class Scheduler
   private String lectureDay;
   private int lectureStart, lectureDuration;
   
   /**
   * constructor // call super class constructor to initialize inherited variables // make sure to *copy* students array list being passed in into new arraylist of students // see class Registry to see how an ActiveCourse object is created and used
   * @param name course name
   * @param code course code
   * @param descr course description
   * @param fmt course format
   * @param semester semester
   * @param studentsPassed Registry students array list. (differnt for each course)
   */
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> studentsPassed) {
      super(name,code,descr,fmt); //initalize them

      this.students = new ArrayList<Student>();// empty 
      for(int i= 0;i <studentsPassed.size();i ++){
         this.students.add(studentsPassed.get(i));//copy array list 
      }
      this.semester = semester;
      lectureStart = 0; 
      lectureDuration = 0;
      lectureDay = "";
   }
   
   /**
    * returns a semester
    * @return semester
    */
   public String getSemester() {
	   return semester;
   }
   
   /**
    * Prints the students in this course (name and student id)
    */
   public void printClassList() { 
      //System.out.println("printClassList (AC) ");  //debug
      for(int i = 0; i < this.students.size(); i ++){ 
         System.out.println(this.students.get(i)); 
      }      
   }
   
   /**
    * Prints the numeric grade of each student (name and student id) in this course 
    * @param courseCode a course code
    */
   public void printGrades(String courseCode) { 
      //System.out.println("printGrades (AC) ");  //debug
      for (int i = 0; i < this.students.size(); i ++){ //go through all ActiveCourse students
         Student s = students.get(i); 
         System.out.println(s.getId() + " " + s.getName() + " " + s.getStudentGrade(courseCode)); //Student.getStudentGrade calls CreditCourse.getCreditGrade to display grade number
      } 
   }
    
   //UNUSED METHOD 
   //public double getGrade(String studentId) { ; }// Returns the numeric grade in this course for this student // If student not found in course, return 0 // Search the student's list of credit courses to find the course code that matches this active course//Student.getGrade() // return the grade stored in the credit course object
   
   /**
    * overrides Course.getDescription() to return info for a course
    * @return a String containing the course information, semester, and the number of students enrolled in the course
    */
   public String getDescription() {
      //System.out.println("ActiveCourse.getDescription ");//debug
	   return super.getDescription() + " " + semester  + " Enrolment " + this.students.size(); //super.getDescription() = return code +" - " + name + "\n" + description + "\n" + format;
   }
    
   /**
    * Sort the students in the course by name 
    */
   public void sortByName() { 
      //System.out.println("ActiveCourse.sortByName ");  //debug
      //System.out.println("Students rn " + students);  //debug
      Collections.sort(students, new NameComparator()); //uses a private Comparator class 
      //System.out.println("Students after " + students);  //debug
   }
   
   /**
    * compares two Student objects based on student name (alpahbetical) 
    */
   private class NameComparator implements Comparator <Student> { //this class implements the Comparator interface
      /**
       * @param s1,s2 2 students for comparing
       * @return an int based on the order of names
       */
      public int compare(Student s1, Student s2){
         return s1.compareTo(s2); //Student.compareTo()
      } 	
   }
   
   /**
    * Sort the students in the course by student id using Collections.sort() 
    */
   public void sortById() {
      //System.out.println("ActiveCourse.sortById "); //debug
      //System.out.println("Students rn  " + students);  //debug
      Collections.sort(students, new IdComparator()); //uses a private Comparator class 
      //System.out.println("Students after  " + students);  //debug
   }
   
   /**
    * compare two Student objects based on student id (lowest to highest) 
    */
   private class IdComparator implements Comparator <Student> { //implement the Comparator interface
       /**
       * @param s1,s2 2 students for comparing
       * @return an int based on the order of ids
       */
   	public int compare(Student s1, Student s2){ 
         if(s1.getId().compareTo(s2.getId()) <= -1){  return -1;
         }else if (s1.getId().compareTo(s2.getId()) >= 1) { return 1;
         }
         return 0; 
      }
   }

   //ADDED METHODS-------------
   /**
    * drop a course by removeing a student from the ActiveCourse students arraylist
    * @param studentId a student Id
    * @param courseCode a course code
    * @return true (if removed) or false (not removed)
    */
   public boolean dropcHelper(String studentId, String courseCode){    
      for(int j= 0; j<this.students.size(); j++ ){ // Find the student in the list of students for this course
         Student stud = this.students.get(j);
         if(stud.getId().equalsIgnoreCase(studentId)){ // If student found 
            this.students.remove(j); //remove the student from the active course
            stud.removeActiveCourse(courseCode); // remove the credit course from the student's list of credit courses //Student.removeActiveCourse
            return true;
         }
      }
      return false;
   }
   
   /**
    * access the ActiveCourse students arraylist
    * @return students list
    */
   public ArrayList<Student> getActiveCourseStudents(){
      return this.students;
   }

   //A2---------
   /**
    * sets lectureStart, lectureDuration, lectureDay from Scheduler.setDayAndTime()
    * @param day,startTime,duration a weekday, start time, and how long class is 
    */
   public void setLecture(String day,int startTime,int duration) {
      lectureDay = day;
      lectureStart = startTime;
      lectureDuration = duration;
      //System.out.println(lectureDay + " " + lectureStart + " " + lectureDuration); //debug
   }

    /**
    * resets lectureStart, lectureDuration, lectureDay from Scheduler.clearSchedule()
    */
   public void resetLecture(){
      lectureDay = ""; 
      lectureStart = 0;
      lectureDuration = 0; 
      //System.out.println(lectureDay + " " + lectureStart + " " + lectureDuration); //debug
   }

   /**
    * access private vars
    * @return lectureStart
    */
   public int getLectureStart(){
      return lectureStart;
   }
   
   /**
    * access private vars
    * @return lectureDuration
    */
   public int getLectureDuration(){
      return lectureDuration;
   }
   
   /**
   * access private vars
   * @return lectureDay
   */
   public String getLectureDay(){
      return lectureDay;
   }

   
}

