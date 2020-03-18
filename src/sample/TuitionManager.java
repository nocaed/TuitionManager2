import java.util.Scanner;
/**
 * The tuition manager class performs the bulk of interaction with the user.  All I/O is handled in this class.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

public class TuitionManager {
    Scanner stdin = new Scanner(System.in);
    StudentList cs213 = new StudentList();

    /**
     * Begins a dialogue with the user, accepting commands and giving responses.
     */
    public void run() {
        String input;
        String[] params;
        boolean done = false;

        while(!done) {
            input = stdin.nextLine();
            params = input.split("\\s+");
            char command = input.charAt(0);
            switch(command) {
                case 'I': case 'O': case 'N':
                    add(input, command);
                    break;
                case 'R':
                    remove(input);
                    break;
                case 'P':
                    print();
                    break;
                case 'Q':
                    done = true;
                    System.out.println("Program terminated");
                    break;
                default:
                    System.out.println("Sorry, command '" + command + "' is not recognized!\n" +
                                        "Please use one of the following commands: [I, O, N, R, P, Q]");
                    break;
            }

        }
    }

    /**
     * Adds a new student to the running student list.
     * @param member String containing info on the student to add
     * @param studentType A character representing the type of student to add: In-state, Out-of-state, or international.
     */
    private void add(String member, char studentType) {
        switch (studentType) {
            case 'I': {
                String[] studentInfo = member.split(" ");

                String fName = studentInfo[1];
                String lName = studentInfo[2];
                int credits = Integer.parseInt(studentInfo[3]);
                if (credits <= 0) {
                    System.out.println("Please re-enter student info with a positive number of credits.");
                    return;
                }
                int funding = Integer.parseInt(studentInfo[4]);

                if (credits < 12) {
                    funding = 0;
                }

                Student student = new Instate(fName, lName, credits, funding);

                cs213.add(student);
                break;
            }
            case 'O': {
                String[] studentInfo = member.split(" ");

                String fName = studentInfo[1];
                String lName = studentInfo[2];
                int credits = Integer.parseInt(studentInfo[3]);
                if (credits <= 0) {
                    System.out.println("Please re-enter student info with a positive number of credits.");
                    return;
                }
                char triState = studentInfo[4].charAt(0);

                Student student = new Outstate(fName, lName, credits, triState);

                cs213.add(student);
                break;
            }
            case 'N': {
                String[] studentInfo = member.split(" ");

                String fName = studentInfo[1];
                String lName = studentInfo[2];
                int credits = Integer.parseInt(studentInfo[3]);
                if (credits <= 0) {
                    System.out.println("Please re-enter student info with a positive number of credits.");
                    return;
                }
                char exchangeStudent = studentInfo[4].charAt(0);

                Student student = new International(fName, lName, credits, exchangeStudent);

                cs213.add(student);
                break;
            }
            default:
                break;
        }
    }

    /**
     * Removes a given student from the running student list.
     * @param member String containing info on the member to remove.
     */
    private void remove(String member) {
        String[] studentInfo = member.split(" ");

        String fName = studentInfo[1];
        String lName = studentInfo[2];

        Student studentToRemove = new Instate(fName, lName, 0, 0);

        cs213.remove(studentToRemove);
    }

    /**
     * Prints the running student list to the user.
     */
    private void print() {
        cs213.print();
    }
}