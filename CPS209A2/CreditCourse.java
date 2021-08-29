//A2
/*
* Phoebe Schulman
* 500973162
*/

public class CreditCourse extends Course //CreditCourse extends class Course (all variables and methods inherited from  Course) //Class Student has the list of CreditCourse objects
{
	//variables semester, grade (initially 0), active (initially true)	
	private String semester;
	private double grade;
	private boolean active;

	/**
	* constructor method that call the super class constructor to initialze variables
	* @param name course name
	* @param code course code
	* @param descr course description
	* @param fmt course format
	* @param semester semester
	* @param grade initial grade(0.0)
	*/
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade) 
	{
		super(name, code, descr, fmt); //will initlize these
		active= true;
		this.semester = semester;
		this.grade = grade;		
	}
	
	/**
	 * returns active
	 * @return active status
	 */
	public boolean getActive()
	{
		return active;
	}
	
	/**
	 * makes active true
	 */
	public void setActive()
	{
		active = true;
	}

	/**
	 * makes active false
	 */
	public void setInactive()
	{
		active = false;
	}
	
	/**
	 * print out this course info, the semester, and letter grades achieved by a student
	 * @return string of course info 
	 */
	public String displayGrade() 
	{
		//System.out.println("CreditCourse.displayGrade"); //debug
		return "" +semester + " Grade " + super.convertNumericGrade(grade); 
	}

	//ADDED METHODS---------
	/**
	 * access CreditCourse grade variable
	 * @return numeric grade value
	 */
	public double getCreditGrade(){ 
		//System.out.println("getCreditGrade (Credit ) "); //debug 
		return grade;
	}
	
	/**
	 * help set a final grade for a CreditCourse
	*/ 
	public void setGrade(double score){
		//System.out.println("score is " + score);//debug
		grade = score;
	}
}