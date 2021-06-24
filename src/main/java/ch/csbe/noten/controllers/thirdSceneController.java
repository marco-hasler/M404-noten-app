package ch.csbe.noten.controllers;

import ch.csbe.noten.db.DbConnecttionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class thirdSceneController extends Navigator{

    Navigator nav = new Navigator();
    DbConnecttionClass dbConn = new DbConnecttionClass();
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnAddGrade;
    @FXML
    private TextField inputFirstName;
    @FXML
    private TextField inputLastName;

    /**
     * wil use the navigator Class to load other scenes
     * @param event button which is pressed in the view
     */
    public void navigateToOtherScene(ActionEvent event) throws IOException, SQLException {
        if (event.getSource().equals(btnOverview)){
            nav.loadOverviewScene(btnOverview);
        }else if(event.getSource().equals(btnAddStudent)){
            nav.loadAddStudentScene(btnAddStudent);
        }else if(event.getSource().equals(btnAddGrade)){
            nav.loadAddGrade(btnAddGrade);
        }else {
            System.out.println("No button submitted in PrimarySceneController navigate func");
        }
    }

    public void addStudentToDb() throws SQLException {

        if(inputFirstName.getText() != "" && inputLastName.getText() != ""){
            String firstname = inputFirstName.getText();
            String lastName = inputLastName.getText();
            dbConn.addStudentToDb(firstname, lastName);
            inputFirstName.clear();
            inputLastName.clear();
        }else {
            alert.setTitle("Formular nicht komplett ausgefüllt");
            alert.setContentText("Bitte Vor und Nchname angeben");
            alert.showAndWait();
            System.out.println("keine daten eingegeben");
        }

    }
}
