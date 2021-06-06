package ch.csbe.noten.controllers;

import javafx.fxml.FXML;
import ch.csbe.noten.GlobalConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class PrimarySceneController extends Navigator {

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
