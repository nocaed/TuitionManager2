package sample;
/**
 * Class for students attending under out of state tuition.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

public class Outstate extends Student {
    private boolean   tristate;               // Holds whether the student is from the tristate area or not
    private final int PRICE_PER_CREDIT = 756; // Holds the price per credit taken

    /**
     * Constructor for Outstate, calls Student superconstructor and determines if the student is from
     * the tristate.
     * @author Thomas Brewer
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credit Student's credits taken this semester
     * @param state Character that represents if the student is from the tristate area 'T' or not
     */
    public Outstate(String fname, String lname, int credit, char state) {
        super(fname, lname, credit);
        tristate = state == 'T';
    }

    /**
     * Calculates the tuition to be paid by the student.
     * @author Thomas Brewer
     * @return The balance that needs to be paid by the student
     */
    @Override
    public int tuitionDue() {
        // Calculates the university fee based on the student's full/part time status
        int universityFee = credit >= 12 ? FULL_TIME : PART_TIME;
        // Gives a $200 discount to the student per every credit if the student is from the tristate and is full time
        int tuitionDiscountPerCredit = credit >= 12 && tristate ? 200 : 0;
        return credit * (PRICE_PER_CREDIT - tuitionDiscountPerCredit) + universityFee;
    }

    @Override
    /**
     * @author Thomas Brewer
     * @return The Student toString plus a statement stating if the student is from the tristate
     */
    public String toString() {
        return super.toString() + " is " + (tristate ? "" : "not") + " a tristate student";
    }

    public static void main(String[] args) {
        Outstate student = new Outstate("Good", "Man", 12, 'F');
        System.out.println("Good's tuition due: $" + student.tuitionDue());
    }
}
