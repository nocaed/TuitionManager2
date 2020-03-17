package sample;

import java.net.URL;
import java.util.ResourceBundle;

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
        // TODO display error message if student is null
        students.add(genStudent());
    }

    @FXML
    private void removeClick() {
        // TODO remove a student from studentList
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
            int credits = Integer.parseInt(creditsTxt.getText());
            if (inRad.isSelected()) {
                int funds = fundingChk.isSelected() ? Integer.parseInt(valTxt.getText()) : 0;
                studentToAdd = new Instate(fname, lname, credits, funds);
            }
            else if (outRad.isSelected()) {
                studentToAdd = new Outstate(fname, lname, credits, triChk.isSelected() ? 'T' : 'F');
            }
            else {
                studentToAdd = new International(fname, lname, credits, exchangeChk.isSelected() ? 'T' : 'F');
            }
        }
        // TODO a ton of specific exception handling
        catch(Exception e) {
            outputTextArea.setText("Error");
        }
        return studentToAdd;
    }

    private void toggleValTxt(boolean disabled) {
        valTxt.setDisable(disabled);
        if(disabled)
            valTxt.setText("");
    }
}
