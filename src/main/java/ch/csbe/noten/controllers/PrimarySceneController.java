package ch.csbe.noten.controllers;

import ch.csbe.noten.Grade;
import ch.csbe.noten.db.DbConnecttionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;


public class PrimarySceneController extends Navigator implements Initializable {

    private Navigator nav = new Navigator();
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
    private Label gradeCount;
    @FXML
    private Label averageGrade;
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
     * wil use the navigator Class to load other scenes
     * @param event button which is pressed in the view.
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
            System.out.println("grade: " + grade.getGrade());
            counter ++; //<--- neded for calculating the avaerage of grades
            amountGrades += grade.getGrade(); //<-- stores the amount of grades saved in the db
            gradesForBar.getData().add(new XYChart.Data<>(grade.getFirstName() + " "
                    + grade.getLastName(), grade.getGrade()));
            gradesForBar.setName("Schüler");
        });

        Collections.sort(gradesForBar.getData(), new Comparator<XYChart.Data<String, Double>>() {
            @Override
            public int compare(XYChart.Data<String, Double> o1, XYChart.Data<String, Double> o2) {
                return o1.getYValue().compareTo(o2.getYValue());
            }
        });

        //stores grades in barchart
        barChart.getData().add(gradesForBar);
        //FXCollections.sort(gradesForBar.getData(), comp2);
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
        double a = amount / count;
        Double roundedDouble = Math.round(a * 100.0) / 100.0;
        String showAverage = Double.toString(roundedDouble);
        averageGrade.setText(showAverage);
        gradeCount.setText(Integer.toString(count));
    }

}
