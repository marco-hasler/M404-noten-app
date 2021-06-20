package ch.csbe.noten.controllers;

import ch.csbe.noten.Modul;
import ch.csbe.noten.Student;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecondarySceneController implements Initializable {

    Navigator nav = new Navigator();
    DbConnecttionClass dbConn = new DbConnecttionClass();
    private Student studentToSaveGrade;
    private Modul modulToSaveGrade;
    private Alert alert = new Alert(Alert.AlertType.WARNING);




    @FXML
    Button btnAddStudent;
    @FXML
    Button btnOverview;
    @FXML
    Button btnAddGrade;
    @FXML
    Button saveGradeToDbBtn;
    @FXML
    TextField gradeInputField;
    @FXML
    public ComboBox<Student> scmBox;
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
            scmBox.setItems(studentsList);
            /**
             * this allows us to display the name of the student in the combox but also let us
             * saves the id of the selected student with the second @override
             */
            scmBox.setConverter(new StringConverter<Student>() {
                @Override
                public String toString(Student student) {
                    return student.getFirstName() + " " + student.getLastName();
                }

                @Override
                public Student fromString(String s) {
                    return scmBox.getItems().stream().filter(ap ->
                            ap.getFirstName().equals(s)).findFirst().orElse(null);
                }
            });
            /**
             * listener for the scmBox combobox which catches the value of the scmBox
             * and update the modulToSaveGrade variable which is needed to save the grade in the db
             */
            scmBox.valueProperty().addListener((obs, oldval, newval) -> {
                if (newval != null)
                studentToSaveGrade = new Student(newval.getFirstName(), newval.getLastName(), newval.getId());
            });
            modulList = dbConn.getModulsFromDb();
            modulPicker.setItems(modulList);
            /**
             * let us show the name of the modul in the view inside the combobox
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
             * and update the student variable which is needed to save the grade in the db
             */
            modulPicker.valueProperty().addListener((obs, oldval, newval) -> {
                if (newval != null)
                modulToSaveGrade = new Modul(newval.getModulNr(), newval.getModulId());
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    /**
     * wil use the navigator Class to load other scenes
     * @param event button which is pressed in the view
     */
    public void navigateToOtherScene(ActionEvent event) throws IOException, SQLException {
        if (event.getSource().equals(btnOverview)){
            nav.loadOverviewScene(btnOverview);
        }else if(event.getSource().equals(btnAddStudent)){
            nav.loadAddStudentScene(btnAddStudent);
        }else if(event.getSource().equals(btnAddGrade)){
            nav.loadAddGrade(btnAddGrade);
        }else {
            System.out.println("No button submitted in PrimarySceneController navigate func");
        }
    }

    /**
     * saves the grade to the db if all datas are given from the view
     * @throws IOException
     * @throws SQLException
     */
    public void saveGradeToDb() throws  IOException, SQLException {
        try{
            String grade = gradeInputField.getText();
            Double parsedGrade = Double.parseDouble(grade);
            if (gradeInputField.getText() == ""){
                alert.setTitle("Keine Note angegeben");
                alert.setContentText("Bitte eine Note eingeben");
                alert.showAndWait();
            }else if(studentToSaveGrade == null) {
                alert.setTitle("Kein Schüler ausgewählt");
                alert.setContentText("Bitte einem Schüler auswählen");
                alert.showAndWait();
            }else if(modulToSaveGrade == null) {
                alert.setTitle("Kein Modul ausgewählt");
                alert.setContentText("Bitte ein Modul auswählen");
                alert.showAndWait();
            }else if (parsedGrade > 6 || parsedGrade <1){
                alert.setTitle("Ungültiger Wert");
                alert.setContentText("Nur Noten zwischen 1 und 6 erlaubt.");
                alert.showAndWait();
            }else {
                dbConn.saveGradeToDb(studentToSaveGrade, modulToSaveGrade, parsedGrade);
                gradeInputField.clear();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }




    }


}
