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
        outRad.setSelected(false);
        intRad.setSelected(false);
        fundingChk.setDisable(false);
        triChk.setDisable(true);
        exchangeChk.setDisable(true);
    }
    @FXML
    private void outstateSelect() {
        inRad.setSelected(false);
        intRad.setSelected(false);
        fundingChk.setDisable(true);
        triChk.setDisable(false);
        exchangeChk.setDisable(true);
    }
    @FXML
    private void internationalSelect() {
        outRad.setSelected(false);
        inRad.setSelected(false);
        fundingChk.setDisable(true);
        triChk.setDisable(true);
        exchangeChk.setDisable(false);
    }

    @FXML
    private void addClick() {
        // TODO add a student to studentlist
    }

    @FXML
    private void removeClick() {
        // TODO remove a student from studentList
    }

    @FXML
    private void printClick() {
        // TODO print the studentlist
    }
}
