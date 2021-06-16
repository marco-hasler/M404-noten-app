package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class thirdSceneController extends Navigator{

    Navigator nav = new Navigator();
    GlobalConstants globCon = new GlobalConstants();
    DbConnecttionClass dbConn = new DbConnecttionClass();

    @FXML
    Button btnAddStudent;
    @FXML
    Button btnOverview;
    @FXML
    Button btnAddGrade;
    @FXML
    TextField inputFirstName;
    @FXML
    TextField inputLastName;

    /**
     * loading the primary scene
     * @throws IOException
     */
    public void navigateToOverview() throws IOException, SQLException {
        nav.loadScene(btnOverview, globCon.getOverview());
    }

    /**
     * laoding the secondary scene
     * @throws IOException
     */
    public void navigateToAddStudent() throws IOException, SQLException {
        nav.loadScene(btnAddStudent, globCon.getAddStud());
    }

    /**
     * loading third scene
     * @throws IOException
     */
    public void navigateToAddGrade() throws IOException, SQLException {
        nav.loadScene(btnAddGrade, globCon.getAddGrade());
    }

    public void addStudentToDb() throws SQLException {
        String firstname = inputFirstName.getText();
        String lastName = inputLastName.getText();
        dbConn.addStudentToDb(firstname, lastName);
        inputFirstName.clear();
        inputLastName.clear();
    }
}
