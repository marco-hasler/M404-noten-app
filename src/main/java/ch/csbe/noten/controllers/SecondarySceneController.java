package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import ch.csbe.noten.Modul;
import ch.csbe.noten.Student;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecondarySceneController implements Initializable {

    Navigator nav = new Navigator();
    GlobalConstants globCon = new GlobalConstants();
    DbConnecttionClass dbConn = new DbConnecttionClass();

    /**
     * if controller is loaded this will be trigered to get all students from the db
     * @throws SQLException
     */



    @FXML
    Button btnAddStudent;
    @FXML
    Button btnOverview;
    @FXML
    Button btnAddGrade;
    @FXML
    public ComboBox<String> scmBox;
    @FXML
    public ComboBox<Modul> modulPicker;
    ObservableList<Student> studentsList;
    ObservableList<Modul> modulList ;

    /**
     * initialize this secondarySceneController with the needed data from the DB for the view
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            studentsList = dbConn.getSchuelerFromDb();
            studentsList.forEach(student -> {
                scmBox.getItems().add(student.getFirstName() + " " + student.getLastName());
            });
            modulList = dbConn.getModulsFromDb();
            modulPicker.setItems(modulList);
            /**
             * let us show the name of the modul in the view
             */
            modulPicker.setConverter(new StringConverter<Modul>() {
                @Override
                public String toString(Modul modul) {
                    return modul.getModulNr();
                }

                @Override
                public Modul fromString(String s) {
                    return modulPicker.getItems().stream().filter(ap ->
                            ap.getModulNr().equals(s)).findFirst().orElse(null);
                }
            });

            /**
             * listener for the modelpicker combobox which catches the value of the modulpicker
             */
            modulPicker.valueProperty().addListener((obs, oldval, newval) -> {
                if (newval != null)
                    System.out.println("selected modul: " + newval.getModulNr()
                    + "modul id: " + newval.getModulId());
            });
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    /**
     * loading  primary scene
     * @throws IOException
     */
    public void navigateToOverview() throws IOException, SQLException {
        System.out.println(btnOverview);
        nav.loadScene(btnOverview, globCon.getOverview());
    }

    /**
     * laoding secondary scene
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
     * saves the grade for the selected user and modul
     * @throws IOException
     * @throws SQLException
     */
    public void saveGradeToDb() throws  IOException, SQLException {

    }


}
