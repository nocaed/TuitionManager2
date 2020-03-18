package sample;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    @FXML
    private TextField fnameTxt,
                      lnameTxt,
                      creditsTxt,
                      valTxt;
    @FXML
    private Button addBtn,
                   removeBtn,
                   printBtn;
    @FXML
    private RadioButton inRad,
                        outRad,
                        intRad;
    @FXML
    private CheckBox fundingChk,
                     triChk,
                     exchangeChk;
    @FXML
    private TextArea outputTextArea;

    private StudentList students;

    public Controller() {
        students = new StudentList();
    }

    @FXML
    private void instateSelect() {
        inRad.setSelected(true);
        outRad.setSelected(false);
        intRad.setSelected(false);
        toggleValTxt(true);
        fundingChk.setDisable(false);
        triChk.setDisable(true);
        triChk.setSelected(false);
        exchangeChk.setDisable(true);
        exchangeChk.setSelected(false);
    }
    @FXML
    private void outstateSelect() {
        outRad.setSelected(true);
        inRad.setSelected(false);
        intRad.setSelected(false);
        toggleValTxt(true);
        fundingChk.setDisable(true);
        fundingChk.setSelected(false);
        triChk.setDisable(false);
        exchangeChk.setDisable(true);
        exchangeChk.setSelected(false);
    }
    @FXML
    private void internationalSelect() {
        intRad.setSelected(true);
        outRad.setSelected(false);
        inRad.setSelected(false);
        toggleValTxt(true);
        fundingChk.setDisable(true);
        fundingChk.setSelected(false);
        triChk.setDisable(true);
        triChk.setSelected(false);
        exchangeChk.setDisable(false);
    }

    @FXML
    private void fundsCheck() {
        if(!fundingChk.isDisabled()) {
            if(fundingChk.isSelected())
                toggleValTxt(false);
            else
                toggleValTxt(true);
        }
    }

    @FXML
    private void addClick() {
        Student student = genStudent();
        if(student != null)
            students.add(student);
    }

    @FXML
    private void removeClick() {
        Student student = genStudent();
        if(student != null)
            students.remove(student);
    }

    @FXML
    private void printClick() {
        outputTextArea.setText(students.toString());
    }

    private Student genStudent() {
        Student studentToAdd = null;
        try {
            String fname = fnameTxt.getText();
            String lname = lnameTxt.getText();
            if(fname.equals("") || lname.equals(""))
                throw new Exception("Error, the student must have a first and last name.");
            int credits = Integer.parseInt(creditsTxt.getText());
            if(credits < 1)
                throw new Exception("Error, the number of credits must be 1 or greater.");
            if (inRad.isSelected()) {
                int funds = fundingChk.isSelected() ? Integer.parseInt(valTxt.getText()) : 0;
                if(funds < 0)
                    throw new Exception("Error, the number of funds must be 0 or greater.");
                studentToAdd = new Instate(fname, lname, credits, funds);
            }
            else if (outRad.isSelected()) {
                studentToAdd = new Outstate(fname, lname, credits, triChk.isSelected() ? 'T' : 'F');
            }
            else {
                if(credits < 9)
                    throw new Exception("Error, international students must have at least 9 credits.");
                studentToAdd = new International(fname, lname, credits, exchangeChk.isSelected() ? 'T' : 'F');
            }
        }
        catch(NumberFormatException nfe) {
            outputTextArea.setText("Error," + nfe.toString().split(":")[2] + " is not an integer.");
        }
        catch(Exception e) {
            outputTextArea.setText(e.toString().split(":")[1].trim());
        }
        return studentToAdd;
    }

    private void toggleValTxt(boolean disabled) {
        valTxt.setDisable(disabled);
        if(disabled)
            valTxt.setText("");
    }
}
