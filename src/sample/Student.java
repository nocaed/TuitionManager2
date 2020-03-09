package sample;
/**
 * Container class containing data related to all students.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

public abstract class Student implements Comparable {
    private String      fname;            // Holds this Student's first name
    private String      lname;            // Holds this Student's last name
    protected int       credit;           // Holds the amount of credits that this Student is taking this semester
    protected final int PART_TIME = 846;  // Holds the university fee for part time students
    protected final int FULL_TIME = 1441; // Holds the university fee for full time students
    private final int   MAX_CREDITS = 15; // Holds the max amount of billable credit hours


    /**
     * Constructor for Student, initializes the fname, lname, and credit attributes in an instance of Student.
     * @author Thomas Brewer
     * @param fname The first name of the student
     * @param lname The last name of the student
     * @param credit The amount of credits the student is taking this semester
     */
    public Student(String fname, String lname, int credit) {
        this.fname = fname;
        this.lname = lname;
        this.credit = credit > MAX_CREDITS ? MAX_CREDITS : credit;
    }

    /**
     * Compares this instance of Student to another instance of Student.
     * @author Thomas Brewer
     * @param obj The object that this instance is being compared with
     * @return 0 if the instances are equal, 1 if this instance is greater, -1 if obj is greater
     */
    public int compareTo(Object obj) {
        // Checks if obj is an instance of Student and throws an Exception if it isn't
        if(!(obj instanceof Student))
            throw new IllegalArgumentException("Parameter 'obj' must be of type 'Student'.");
        // Otherwise, cast obj to type Student and sum the return values of comparing fname and lname
        Student otherStudent = (Student)obj;
        int result = Math.abs(fname.compareTo(otherStudent.fname)) + Math.abs(lname.compareTo(otherStudent.lname));
        return result;
    }

    @Override
    /**
     * Overridden toString implementation for Student.
     * @author Thomas Brewer
     * @return A string containing all properties of this instance of Student
     */
    public String toString() {
        return fname + " " + lname + " " + credit;
    }

    /**
     * Calculates the Student's tuition for this semester.
     * @author Thomas Brewer
     * @return The Student's semester tuition
     */
    public abstract int tuitionDue();
}
