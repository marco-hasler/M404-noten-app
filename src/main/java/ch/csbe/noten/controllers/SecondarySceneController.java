package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import ch.csbe.noten.Modul;
import ch.csbe.noten.Student;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    GlobalConstants globCon = new GlobalConstants();
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
             * save the id of the selected student
             */
            scmBox.setConverter(new StringConverter<Student>() {
                @Override
                public String toString(Student student) {
                    return student.getFirstName() + student.getLastName();
                }

                @Override
                public Student fromString(String s) {
                    return scmBox.getItems().stream().filter(ap ->
                            ap.getFirstName().equals(s)).findFirst().orElse(null);
                }
            });
            /**
             * listener for the scmBox combobox which catches the value of the scmBox
             * and update the modulToSaveGrade variable which is needed to save the grade in the
             */
            scmBox.valueProperty().addListener((obs, oldval, newval) -> {
                if (newval != null)
                    System.out.println("selected student: " + newval.getFirstName() + newval.getLastName()
                    + "with id: " + newval.getId());
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
                    System.out.println("selected modul: " + newval.getModulNr()
                    + "modul id: " + newval.getModulId());
                modulToSaveGrade = new Modul(newval.getModulNr(), newval.getModulId());
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
     * saves the grade to the db if all datas are given from the view
     * @throws IOException
     * @throws SQLException
     */
    public void saveGradeToDb() throws  IOException, SQLException {
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
        }else if(modulToSaveGrade == null){
            alert.setTitle("Kein Modul ausgewählt");
            alert.setContentText("Bitte ein Modul auswählen");
            alert.showAndWait();
        }else {
            dbConn.saveGradeToDb(studentToSaveGrade, modulToSaveGrade, parsedGrade);
            System.out.println("grade = " + grade);
            System.out.println("student id = " + studentToSaveGrade.getId() + "modul id= " + modulToSaveGrade.getModulId());
        }


    }


}
