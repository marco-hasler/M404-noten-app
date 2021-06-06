package ch.csbe.noten;


import ch.csbe.noten.controllers.PrimarySceneController;
import ch.csbe.noten.controllers.SecondarySceneController;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Starter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        //connection for mysql db
        DbConnecttionClass dbConnecttionClass = new DbConnecttionClass();

        //db connection test dummies
        dbConnecttionClass.jdbcConnection();
        dbConnecttionClass.getSchuelerFromDb();

        //loading primary scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/primaryScene.fxml"));
        loader.setController(new PrimarySceneController());

        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
