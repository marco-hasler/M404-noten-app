package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class thirdSceneController extends Navigator{

    Navigator nav = new Navigator();
    GlobalConstants globCon = new GlobalConstants();

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
    public void navigateToOverview() throws IOException {
        nav.loadScene(btnOverview, globCon.getOverview());
    }

    /**
     * laoding the secondary scene
     * @throws IOException
     */
    public void navigateToAddStudent() throws  IOException {
        nav.loadScene(btnAddStudent, globCon.getAddStud());
    }

    /**
     * loading third scene
     * @throws IOException
     */
    public void navigateToAddGrade() throws  IOException {
        nav.loadScene(btnAddGrade, globCon.getAddGrade());
    }
}
