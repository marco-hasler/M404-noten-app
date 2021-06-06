package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondarySceneController {

    Navigator nav = new Navigator();
    GlobalConstants globCon = new GlobalConstants();

    @FXML
    Button btnAddStudent;
    @FXML
    Button btnOverview;
    @FXML
    Button btnAddGrade;

    /**
     * loading  primary scene
     * @throws IOException
     */
    public void navigateToOverview() throws IOException {
        System.out.println(btnOverview);
        nav.loadScene(btnOverview, globCon.getOverview());
    }

    /**
     * laoding secondary scene
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
