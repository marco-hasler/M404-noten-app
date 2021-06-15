package ch.csbe.noten.db;

import ch.csbe.noten.Modul;
import ch.csbe.noten.Student;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DbConnecttionClass {
    /**
     * This will connect us to the mysql db
     * @throws SQLException allows us to use try and catch
     */

    static Connection con = null;
    static Statement statement = null;

    /**
     * establish mysql connection
     * @throws SQLException
     */
    public void jdbcConnection() throws SQLException{
        String query = "select * from schueler";

        //whill check if the packages are correctly imported
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/noten?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "CsBe12345");
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * --------------------------------------------------Module Section---------------------------------------------
     */

    /**
     * gets all modules from db
     * @return modules
     * @throws SQLException
     */
    public ObservableList<Modul> getModulsFromDb() throws SQLException {
        String query = "SELECT * from modul";
        String modules = null;
        ObservableList<Modul> modulList = FXCollections.observableArrayList();
         try {
             statement = con.createStatement();
             ResultSet result = statement.executeQuery(query);
             Modul modul;
             while (result.next()) {
                 modul = new Modul(result.getString("modulname"), result.getInt("idmodul") );
                 modulList.add(modul);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return modulList;
    }


    /**
     * --------------------------------------------------Student Section---------------------------------------------
     */

    /**
     * Returns all studendts from db
     * @return Students
     * @throws SQLException
     */
    public  ObservableList<Student> getSchuelerFromDb() throws SQLException {
        String query = "SELECT * from schueler";
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        try {
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            Student student;
            while (result.next()) {
                student = new Student(result.getString("name"),result.getString("last_name"), result.getInt("idschueler"));
                studentList.add(student);
                System.out.println("schueler aus db");
                System.out.println(student.getFirstName() + student.getLastName());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }



    /**
     * --------------------------------------------------Grade Section---------------------------------------------
     */

    /**
     * Get to db for all shool grades
     * @return school grades
     * @throws SQLException
     */
    public double getGradeFromDb() throws SQLException {
        String query = "SELECT * from noten";
        double grades = 0;
        try {
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                grades = result.getDouble("note");
                System.out.println(grades);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    /**
     * stores a student in the db
     * @param firstName
     * @param lastName
     * @throws SQLException
     */
    public void addStudentToDb(String firstName, String lastName) throws SQLException {
        String query = "INSERT INTO schueler (name, last_name) VALUES (?, ?)";
        String stud = "";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate();
            //https://youtu.be/kpnnXit2br0?t=775
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
