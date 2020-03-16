package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;

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
        valTxt.setDisable(false);
        inRad.setSelected(true);
        outRad.setSelected(false);
        intRad.setSelected(false);
        fundingChk.setDisable(false);
        triChk.setDisable(true);
        triChk.setSelected(false);
        exchangeChk.setDisable(true);
        exchangeChk.setSelected(false);
    }
    @FXML
    private void outstateSelect() {
        valTxt.setDisable(true);
        valTxt.setText("");
        outRad.setSelected(true);
        inRad.setSelected(false);
        intRad.setSelected(false);
        fundingChk.setDisable(true);
        fundingChk.setSelected(false);
        triChk.setDisable(false);
        exchangeChk.setDisable(true);
        exchangeChk.setSelected(false);
    }
    @FXML
    private void internationalSelect() {
        valTxt.setDisable(true);
        valTxt.setText("");
        intRad.setSelected(true);
        outRad.setSelected(false);
        inRad.setSelected(false);
        fundingChk.setDisable(true);
        fundingChk.setSelected(false);
        triChk.setDisable(true);
        triChk.setSelected(false);
        exchangeChk.setDisable(false);
    }

    @FXML
    private void addClick() {
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
        String fname;
        String lname;
        int credits;
        try {
            fname = fnameTxt.getText();
            lname = lnameTxt.getText();
            credits = Integer.parseInt(creditsTxt.getText());
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
}
