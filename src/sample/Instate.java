package sample;
/**
 * Class for students attending under in state tuition.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

public class Instate extends Student {
    private int       funds;                  // Holds the funds that this Student is awarded
    private final int PRICE_PER_CREDIT = 433; // Holds the price per credit for this Student's tuition

    /**
     * Constructor for Instate, calls Student constructor and then determines funds to award
     * the Instate Student.
     * @author Thomas Brewer
     * @param fname Instate's first name
     * @param lname Instate's last name
     * @param credit Instate's amount of credits taken this semester
     * @param funds The amount of funds awarded to this Instate
     */
    public Instate(String fname, String lname, int credit, int funds) {
        super(fname, lname, credit);
        // Awards the student no funds if they are not a full time student
        this.funds = credit >= 12 ? funds : 0;
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
        return credit * PRICE_PER_CREDIT - funds + universityFee;
    }

    @Override
    /**
     * @author Thomas Brewer
     * @return The Student toString plus the amount of funds the instate student has
     */
    public String toString() {
        return super.toString() + " has " + funds + " funds.";
    }

    public static void main(String[] args) {
        Instate wilson = new Instate("Wilson", "Long", 8, 1000);
        System.out.println("Wilson's tuition due: $" + wilson.tuitionDue());
    }
}
