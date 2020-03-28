package sample;
/**
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * This class controls all events that occur within the GUI.
 * @author Thomas Brewer
 */
public class Controller {
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources; // Holds all resources in the GUI
    @FXML
    private TextField fnameTxt,   // Input field for first name
                      lnameTxt,   // Input field for last name
                      creditsTxt, // Input field for credits
                      valTxt;     // Input field for funding
    @FXML
    private Button addBtn,    // Button that adds a student to the list
                   removeBtn, // Button that removes a student from the list
                   printBtn;  // Button that prints all students in the list
    @FXML
    private RadioButton inRad,  // Radio button for instate students
                        outRad, // Radio button for outstate students
                        intRad; // Radio button for international students
    @FXML
    private CheckBox fundingChk,  // Checkbox for instate funding
                     triChk,      // Checkbox for tristate outstate students
                     exchangeChk; // Checkbox for exchange international students
    @FXML
    private TextArea outputTextArea; // Text area that displays error messages and prints the StudentList

    private StudentList students; // Holds all students

    /**
     * Constructor for Controller, initializes students.
     * @author Thomas Brewer
     */
    public Controller() {
        students = new StudentList();
    }

    @FXML
    /**
     * Handles selection of inRad.
     * @author Thomas Brewer
     */
    private void instateSelect() {
        // Select only inRad
        inRad.setSelected(true);
        outRad.setSelected(false);
        intRad.setSelected(false);
        // Disable funding entry
        toggleValTxt(true);
        // Enable only funding checkbox
        fundingChk.setDisable(false);
        triChk.setDisable(true);
        triChk.setSelected(false);
        exchangeChk.setDisable(true);
        exchangeChk.setSelected(false);
    }
    @FXML
    /**
     * Handles selection of outRad.
     * @author Thomas Brewer
     */
    private void outstateSelect() {
        // Select only outRad
        outRad.setSelected(true);
        inRad.setSelected(false);
        intRad.setSelected(false);
        // Disable funding entry
        toggleValTxt(true);
        // Enable only tristate checkbox
        fundingChk.setDisable(true);
        fundingChk.setSelected(false);
        triChk.setDisable(false);
        exchangeChk.setDisable(true);
        exchangeChk.setSelected(false);
    }
    @FXML
    /**
     * Handles selection of intRad.
     * @author Thomas Brewer
     */
    private void internationalSelect() {
        // Select only intRad
        intRad.setSelected(true);
        outRad.setSelected(false);
        inRad.setSelected(false);
        // Disable funding entry
        toggleValTxt(true);
        // Enable only exchange checkbox
        fundingChk.setDisable(true);
        fundingChk.setSelected(false);
        triChk.setDisable(true);
        triChk.setSelected(false);
        exchangeChk.setDisable(false);
    }

    @FXML
    /**
     * Handles toggle of funding entry.
     * @author Thomas Brewer
     */
    private void fundsCheck() {
        // If funding checkbox is not disabled...
        if(!fundingChk.isDisabled()) {
            // If funding checkbox has been selected...
            if(fundingChk.isSelected())
                // Enable funding entry
                toggleValTxt(false);
            else
                // Otherwise, disable funding entry
                toggleValTxt(true);
        }
    }

    @FXML
    /**
     * Handles add button press.
     * @author Thomas Brewer
     */
    private void addClick() {
        // Add a Student if all input is valid
        Student student = genStudent();
        if(student != null)
            students.add(student);
    }

    @FXML
    /**
     * Handles remove button press.
     * @author Thomas Brewer
     */
    private void removeClick() {
        try {
            boolean found = false;
            // Remove a Student if all input is valid
            Student student = genStudent();
            if(student != null)
                found = students.remove(student);

            if (!found)
                throw new Exception("Student not found.");

        } catch (Exception e) {
            printGenericException(e);
        }
    }

    @FXML
    /**
     * Handles print button press
     * @author Thomas Brewer
     */
    private void printClick() {
        // Set output box to the contents of students
        outputTextArea.setText(students.toString());
    }

    /**
     * Generates a new Student object.
     * @author Thomas Brewer
     * @return A Student subclass based on user input, or a null reference if an exception has occurred
     */
    private Student genStudent() {
        Student studentToAdd = null;
        try {
            // Set the student's first name, last name, and number of credits
            String fname = fnameTxt.getText();
            String lname = lnameTxt.getText();
            int credits = Integer.parseInt(creditsTxt.getText());
            // Validates the input for the student's credentials
            validateBaseInput(fname, lname, credits);
            if (credits < 1) {
                throw new Exception("Error: Credits must be at least 1.");
            }
            // Generate a Student based on the selected radio button
            studentToAdd = inRad.isSelected() ? genInstate(fname, lname, credits) : outRad.isSelected() ? genOutstate(fname, lname, credits) : genInternational(fname, lname, credits);
        }
        // Catches an error in parsing credits
        catch(Exception e) {
            studentToAdd = null;
            printGenericException(e);
        }
        return studentToAdd;
    }

    /**
     * Enables/disables the funding entry text box.
     * @author Thomas Brewer
     * @param disabled True if valTxt is being disabled, false if valTxt is being enabled
     */
    private void toggleValTxt(boolean disabled) {
        valTxt.setDisable(disabled);
        if(disabled)
            valTxt.setText("");
    }

    /**
     * Generates an Instate student.
     * @author Thomas Brewer
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credits Student's number of credits
     * @return A new Instate student, or a null reference if input is not valid
     */
    private Instate genInstate(String fname, String lname, int credits) {
        Instate student = null;
        try {
            // Parse the amount of funding
            int funds = fundingChk.isSelected() ? Integer.parseInt(valTxt.getText()) : 0;
            // Throw an exception if the student's funds are negative
            if (funds < 0)
                throw new Exception("Error, the number of funds must be 0 or greater.");
            if (credits < 1) {
                throw new Exception("Error: Credits must be at least 1.");
            }
            if (credits < 7 && funds > 0) {
                throw new Exception("Student must be full time to receive funding.");
            }
            student = new Instate(fname, lname, credits, funds);
        }
        // Catches an integer parsing error
        catch(NumberFormatException nfe) {
            student = null;
            printNumberFormatException(nfe);
        }
        // Catches negative funding
        catch(Exception e) {
            student = null;
            printGenericException(e);
        }
        return student;
    }

    /**
     * Generates a new Outstate student.
     * @author Thomas Brewer
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credits Student's number of credits
     * @return A new Outstate student
     */
    private Outstate genOutstate(String fname, String lname, int credits) {
        return new Outstate(fname, lname, credits, triChk.isSelected() ? 'T' : 'F');
    }

    /**
     * Generates a new International student.
     * @author Thomas Brewer
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credits Student's number of credits
     * @return A new International student, or a null reference if input is not valid
     */
    private International genInternational(String fname, String lname, int credits) {
        International student = null;
        try {
            // Throw an exception if the International student has less than 9 credits
            if (credits < 9)
                throw new Exception("Error, international students must have at least 9 credits.");
            student = new International(fname, lname, credits, exchangeChk.isSelected() ? 'T' : 'F');
        }
        // Catch the aforementioned error
        catch(Exception e) {
            student = null;
            printGenericException(e);
        }
        return student;
    }

    /**
     * Validates the student's credentials.
     * @author Thomas Brewer
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credits Student's number of credits
     */
    private void validateBaseInput(String fname, String lname, int credits) {
        try {
            // Throw an exception if first name or last name are empty strings
            if (fname.equals("") || lname.equals(""))
                throw new Exception("Error, the student must have a first and last name.");
            // Throw an exception if the student has negative or zero credits
            if (credits < 1)
                throw new Exception("Error, the number of credits must be 1 or greater.");
        }
        // Catch any of the aforementioned errors
        catch(Exception e) {
            printGenericException(e);
        }
    }

    /**
     * Prints an error message telling the user which input threw an error during parsing.
     * @author Thomas Brewer
     * @param nfe The parsing exception that occurred
     */
    private void printNumberFormatException(NumberFormatException nfe) {
        outputTextArea.setText("Error," + nfe.toString().split(":")[2] + " is not an integer.");
    }

    /**
     * Prints an error message telling the user which bad input occurred.
     * @author Thomas Brewer
     * @param e The exception caused by invalid input
     */
    private void printGenericException(Exception e) {
        outputTextArea.setText(e.toString().split(":")[1].trim());
    }
}