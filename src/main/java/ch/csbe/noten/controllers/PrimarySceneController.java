package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class PrimarySceneController extends Navigator implements Initializable {

    Navigator nav = new Navigator();
    GlobalConstants globCon = new GlobalConstants();
    DbConnecttionClass dbCOnn = new DbConnecttionClass();

    @FXML
    Button btnAddStudent;
    @FXML
    Button btnOverview;
    @FXML
    Button btnAddGrade;



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

    /**
     * will be called if the scene is loaded, its needed to get the innerjoin query from the db which will show
     * all the gardes of the students
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(dbCOnn.getInnerJoinFromDb());
    }
}
