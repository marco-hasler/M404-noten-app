package ch.csbe.noten;


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
        DbConnecttionClass dbConnecttionClass = new DbConnecttionClass();
        dbConnecttionClass.jdbcConnection();
        dbConnecttionClass.getSchuelerFromDb();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/primaryScene.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
