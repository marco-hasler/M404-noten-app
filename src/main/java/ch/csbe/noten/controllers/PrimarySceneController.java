package ch.csbe.noten.controllers;

import ch.csbe.noten.GlobalConstants;
import ch.csbe.noten.Grade;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private int counter = 0;
    private Double amountGrades = 0.0;

    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnAddGrade;
    @FXML
    private TableView<Grade> averageGrade;
    @FXML
    private TableColumn <Grade, Double> averageGradeCol;
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
    @FXML
    private BarChart <String, Double> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;


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
        counter = 0;
        amountGrades = 0.0;
        ObservableList<Grade> obGrade = dbCOnn.getInnerJoinFromDb();
        XYChart.Series<String, Double> gradesForBar = new XYChart.Series<>();
        //this part saves the grades into the barchart and also is needed for our average grades section
        obGrade.forEach(grade -> {
            counter ++; //<--- neded for calculating the avaerage of grades
            amountGrades += grade.getGrade(); //<-- stores the amount of grades saved in the db
            gradesForBar.getData().add(new XYChart.Data<>(grade.getFirstName() + " "
                    + grade.getLastName(), grade.getGrade()));
        });
        //stores grades in barchart
        barChart.getData().add(gradesForBar);

        //defines which Grade propertie are stored in the cols
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("lastName"));
        modulTableCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("modul"));
        gradeTableCol.setCellValueFactory(new PropertyValueFactory<Grade, Double>("grade"));
        gradeTableView.setItems(obGrade);

        //shows average
        calculateAverage(counter, amountGrades);

    }

    /**
     * calculates and set the value in the "Noten Durchschnit" table view
     * @param count
     * @param amount
     */
    public void calculateAverage(int count, Double amount ){
        ObservableList<Grade> tmpGradeObj = FXCollections.observableArrayList();
        Double a = amount / count;
        Grade gradeToSubmit = new Grade("o", "o", a, "0");
        tmpGradeObj.add(gradeToSubmit);
        averageGradeCol.setCellValueFactory(new PropertyValueFactory<Grade, Double>("grade"));
        averageGrade.setItems(tmpGradeObj);
    }
}
