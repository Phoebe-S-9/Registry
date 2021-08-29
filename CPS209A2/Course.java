//A2
/*
* Phoebe Schulman
* 500973162
*/
public class Course //subclasses: CreditCourse and ActiveCourse 
{
	private String code, name, description, format;
	   
	public Course()
	{
		this.code        = "";
		this.name        = "";
		this.description = "";
		this.format      = "";
	}

	/**
	* constructor method to initialze variables
	* @param name course name //"Computer Science II"
	* @param code course code //"CPS209"
	* @param descr course description //"Learn how to write complex programs!"
	* @param fmt course format //"3Lec 2Lab"
	*/
	public Course(String name, String code, String descr, String fmt)
	{
		this.code        = code;
		this.name        = name;
		this.description = descr;
		this.format      = fmt;
	}
	   
	/**
	* returns code
	* @return course code
	*/
	public String getCode()
	{
		return code;
	}
	   
	/**
	* returns name
	* @return course name
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* returns format
	* @return course format
	*/  
	public String getFormat()
	{
		return format;
	}
	   
	/**
	* returns course info
	* @return course code,name, description, format
	*/  
	public String getDescription()
	{
		return code +" - " + name + "\n" + description + "\n" + format;
	}
	

	/**
	* returns specific course info
	* @return course code,name
	*/  
	public String getInfo()
	{
		return code +" - " + name;
	}
	
	/**
	 * convert numeric score to a letter grade string 
	 * @param grade a number
	 * @return a letter grade
	 */
	public static String convertNumericGrade(double grade) {
		String output = "F";
		final double APLUS = 4.15, A = 3.85, AMINUS = 3.5, BPLUS = 3.15, B = 2.85, BMINUS = 2.5, 
		CPLUS = 2.15, C = 1.85, CMINUS = 1.5, DPLUS = 1.15, D = 0.85, DMINUS = 0.35 ; 

		if (grade>= APLUS){ output = "A+";
		}else if (grade>= A){ output = "A";
		}else if (grade>= AMINUS){ output = "A-";
		}else if (grade>= BPLUS){ output = "B+";
		}else if (grade>= B){ output = "B";
		}else if (grade>= BMINUS){ output = "B-";
		}else if (grade>= CPLUS){ output = "C+";
		}else if (grade>= C){ output = "C";
		}else if (grade>= CMINUS){ output = "C-";
		}else if (grade>= DPLUS){ output = "D+";
		}else if (grade>= D){ output = "D";
		}else if (grade>= DMINUS){ output = "D-";
		}
		return "" + output;
	}
}
