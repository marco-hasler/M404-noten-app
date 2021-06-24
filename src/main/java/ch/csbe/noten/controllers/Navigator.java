package ch.csbe.noten.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public  class Navigator {

    /**
     * will load the primary scene after called from a scene
     * @param btn this param is needed for binding it to the button id from the scene
     * @throws IOException triggered if error
     */
    public void  loadOverviewScene(Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/primaryScene.fxml"));
        loader.setController(new PrimarySceneController());
        Parent root = loader.load();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * will load the secondary scene after called from a scene
     * @param btn this param is needed for binding it to the button id from the scene
     * @throws IOException triggered if error
     */
    public void loadAddStudentScene(Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/thirdScene.fxml"));
        loader.setController(new thirdSceneController());
        Parent root = loader.load();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * will load the secondary scene after called from a scene
     * @param btn this param is needed for binding it to the button id from the scene
     * @throws IOException triggered if error
     */
    public void loadAddGrade(Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/secondaryScene.fxml"));
        loader.setController(new SecondarySceneController());
        Parent root = loader.load();
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(root));
    }


}
