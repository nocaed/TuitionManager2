package sample;
/**
 * Class for students attending under international tuition.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

public class International extends Student {
    private boolean   exchange;               // Holds whether the student is an exchange student or not
    private final int PRICE_PER_CREDIT = 945; // Holds the price per credit
    private final int STUDENT_FEE = 350;      // Holds the international student fee

    /**
     *
     * @author Thomas Brewer
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credit Student's number of credits being taken
     * @param exchange Character that tells if the student is an exchange student 'T' or not
     */
    public International(String fname, String lname, int credit, char exchange) {
        super(fname, lname, credit);
        this.exchange = exchange == 'T';
    }

    /**
     * Calculates the tuition to be paid by the student.
     * @author Thomas Brewer
     * @return The balance that needs to be paid by the student
     */
    @Override
    public int tuitionDue() {
        // Returns the international student fee + the full time university fee if the student is an exchange student
        // Otherwise, returns the international student fee + the student's enrollment status and the credit cost
        return STUDENT_FEE + (exchange ? FULL_TIME : (credit >= 12 ? FULL_TIME : PART_TIME) + credit * PRICE_PER_CREDIT);
    }

    @Override
    /**
     * @author Thomas Brewer
     * @return Returns the super toString plus a statement showing if the student is an exchange student
     */
    public String toString() {
        return super.toString() + (exchange ? " is " : " is not ") + "an exchange student.";
    }

    public static void main(String[] args) {
        International student = new International("David", "Lee", 12, 'F');
        System.out.println("David's tuition due: $" + student.tuitionDue());
    }
}
