package webd4201.altons;

import java.text.DecimalFormat;

/**
 * Mark.java
 * This is the mark class that represents an individual student's grade.
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
public class Mark {
    // Class constants
    /**
     * the minimum allowable GPA
     */
    public final static float MINIMUM_GPA = (float) 0.0;
    /**
     * the maximum allowable GPA
     */
    public final static float MAXIMUM_GPA = (float) 5.0;
    /**
     * the format to be used for storing GPA values
     */
    public final static DecimalFormat GPA = new DecimalFormat("0.0");

    // Instance attributes
    /**
     * the course code the mark corresponds to
     */
    private String courseCode;
    /**
     * the course name the mark corresponds to
     */
    private String courseName;
    /**
     * the result of the grade
     */
    private int result;
    /**
     * the weighting of the GPA achieved
     */
    private float gpaWeighting;

    // Constructor
    /**
     * Parameterized constructor - creates a new mark instance
     * @param courseCode
     *                  the course code the mark corresponds to
     * @param courseName
     *                  the name of the course the mark corresponds to
     * @param result
     *                  the result of the mark
     * @param gpaWeighting
     *                  the GPA weighting of the mark
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting) {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setResult(result);
        setGpaWeighting(gpaWeighting);
    }

    // Mutators and Accessors
    /**
     * Returns the course code
     *
     * @return courseCode The course code that the grade is for
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the course code for the mark
     *
     * @param courseCode
     *                  The new course code
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Returns the name of the course
     *
     * @return courseName The name of the course the grade is for
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name
     *
     * @param courseName
     *          the name of the course the grade is for
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the result of the mark as an integer representing the accomplished percentage
     *
     * @return result
     *          the percentage grade the student achieved
     */
    public int getResult() {
        return result;
    }

    /**
     * Sets the result of the grade
     *
     * @param result
     *          the percentage grade the student achieved
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Returns the gpa weighting of the grade
     *
     * @return Formatted gpa weight rounded to 1 decimal place
     */
    public String getGpaWeighting() {
        return GPA.format(gpaWeighting);
    }

    /**
     * Sets the gpa weighting of a grade
     *
     * @param gpaWeighting
     *          the weighting of the grade
     */
    public void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }

    // Class methods
    /**
     * Returns a string summarizing the mark details
     *
     * @return A formatted string providing the mark details
     */
    public String toString() {
        return getCourseCode() + "\t" + String.format("%-35s", getCourseName()) + getResult() + "\t\t" + getGpaWeighting();
    }
}
