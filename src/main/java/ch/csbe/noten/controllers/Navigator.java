package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public  class Navigator {
    @FXML
    GlobalConstants globalCon = new GlobalConstants();


    //TODO use button id
    public void loadScene(Button button, String scene) throws IOException, SQLException {
        System.out.println("id from button" + button.getId());
        System.out.println("scene = " + scene);
        if (scene == globalCon.getOverview()){
            System.out.println("trigerin loadoverview");
            loadOverviewScene(button);
        }else if ( scene == globalCon.getAddGrade()){
            System.out.println("trigerin loaddaddgrade in navigator");
            loadAddGrade(button);
        }else if ( scene == globalCon.getAddStud()){
            loadAddStudentScene(button);
        }
    }

    /**
     * will load the primary scene after called from a scene
     * @param btn this param is needed for binding it to the button id from the scene
     * @throws IOException
     */
    public void  loadOverviewScene(Button btn) throws IOException {
        System.out.println("loading overview scene in navigator");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/primaryScene.fxml"));
        loader.setController(new PrimarySceneController());
        Parent root = loader.load();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * will load the secondary scene after called from a scene
     * @param btn this param is needed for binding it to the button id from the scene
     * @throws IOException
     */
    public void loadAddStudentScene(Button btn) throws IOException {
        System.out.println("loadaddstundent in navigator");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/thirdScene.fxml"));
        loader.setController(new thirdSceneController());
        Parent root = loader.load();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * will load the secondary scene after called from a scene
     * @param btn this param is needed for binding it to the button id from the scene
     * @throws IOException
     */
    public void loadAddGrade(Button btn) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/secondaryScene.fxml"));
        loader.setController(new SecondarySceneController());
        Parent root = loader.load();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));

    }


}
