package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import ch.csbe.noten.Grade;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class PrimarySceneController extends Navigator implements Initializable {

    private Navigator nav = new Navigator();
    private GlobalConstants globCon = new GlobalConstants();
    private DbConnecttionClass dbCOnn = new DbConnecttionClass();

    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnAddGrade;
    @FXML
    private TableView<Grade> gradeTableView;
    @FXML
    private TableColumn<Grade, String> firstNameCol;
    @FXML
    private TableColumn<Grade, String> lastNameCol;
    @FXML
    private TableColumn<Grade, Double> gradeTableCol;
    @FXML
    private TableColumn<Grade, String> modulTableCol;



    /**
     * loading the primary scene
     * @throws IOException
     */
    public void navigateToOverview() throws IOException, SQLException {
        nav.loadScene(btnOverview, globCon.getOverview());
    }

    /**
     * laoding the secondary scene
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
     * will be called if the scene is loaded, its needed to get the innerjoin query from the db which will show
     * all the gardes of the students
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("primary init");
        ObservableList<Grade> obGrade = dbCOnn.getInnerJoinFromDb();
        obGrade.forEach(grade -> {
            System.out.println(grade.getFirstName());
        });
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("lastName"));
        modulTableCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("modul"));
        gradeTableCol.setCellValueFactory(new PropertyValueFactory<Grade, Double>("grade"));

        gradeTableView.setItems(obGrade);
    }
}
