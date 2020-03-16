package sample;
import java.text.NumberFormat;

/**
 * Class for managing a dynamic array holding a collection of students.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

public class StudentList {
    private int       GROW_SIZE = 4;  // The size that the students array will be expanded by
    private int       NOT_FOUND = -1; // The return value in case a Student is not found in students
    private Student[] students;       // Contains all Students inputted by the user
    private int       numStudents;    // Holds the number of students in students

    /**
     * Constructor for StudentList, initializes students to an empty Student array of size of 4 and numStudents to 0.
     * @author Thomas Brewer
     */
    public StudentList() {
        students = new Student[GROW_SIZE];
        numStudents = 0;
    }

    /**
     * Checks if students is full.
     * @author Thomas Brewer
     * @return If the array has no more free space left
     */
    private boolean isFull() {
        return numStudents == students.length;
    }

    /**
     * Grows the array by GROW_SIZE amount.
     * @author Thomas Brewer
     */
    private void grow() {
        // New temporary array that will replace students
        Student[] temp = new Student[GROW_SIZE + numStudents];
        // Copies all elements in students over to temp
        for(int i = 0; i < numStudents; i++) {
            temp[i] = students[i];
        }
        students = temp;
    }

    /**
     * Finds a target Student in students.
     * @author Thomas Brewer
     * @param s The target Student to find
     * @return The index of the target Student
     */
    private int find(Student s) {
        // Searches for the target
        for(int i = 0; i < numStudents; i++) {
            if(students[i].compareTo(s) == 0)
                return i;
        }
        // Returns NOT_FOUND if the target is not found
        return NOT_FOUND;
    }

    /**
     * Adds a Student to students.
     * @author Thomas Brewer
     * @param s The target to add to the array
     */
    public void add(Student s) {
        if (this.find(s) > -1) {
            System.out.println("Cannot add a student already in the list.");
            return;
        }
        // Grows students if it is full
        if(isFull())
            grow();
        // Adds the new student to the end of the array
        students[numStudents] = s;
        numStudents++;
    }

    /**
     * Removes a target student in students.
     * @author Thomas Brewer
     * @param s The target Student to remove
     * @return True if the Student was in students, False if it was not
     */
    public boolean remove(Student s) {
        // Searches for s
        int searchIndex = find(s);
        // Returns false if s is not found
        if(searchIndex == NOT_FOUND) {
            System.out.println("Student not found.");
            return false;
        }

        // Puts the end of students into the target removal index, and removes said reference at the end of students
        students[searchIndex] = students[numStudents - 1];
        students[numStudents - 1] = null;
        // Decrements the number of students
        numStudents--;
        // Returns true since a Student was found
        return true;
    }

    /**
     * Prints every Student in students
     * @author Thomas Brewer
     */
    public void print() {
        if (this.numStudents == 0) {
            System.out.println("No students added yet.");
            return;
        }

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        for(Student student : students) {
            if (student != null) {
                System.out.println(student + " tuition due: $" + myFormat.format(student.tuitionDue()));
            }
        }
    }

    @Override
    /**
     * Generates a formatted list of Students.
     * @author Thomas Brewer
     * @return A String of formatted Students separated by newlines
     */
    public String toString() {
        if (this.numStudents == 0) {
            return "No students added yet.";
        }

        String studentStr = "";
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        for(Student student : students) {
            if (student != null) {
                studentStr += student + " tuition due: $" + myFormat.format(student.tuitionDue()) + "\n";
            }
        }
        return studentStr;
    }
}
