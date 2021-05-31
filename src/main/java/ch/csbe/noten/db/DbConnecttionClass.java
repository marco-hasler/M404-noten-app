package ch.csbe.noten.db;

import com.mysql.cj.protocol.Resultset;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DbConnecttionClass {
    /**
     * This will connect us to the mysql db
     * @throws SQLException allows us to use try and catch
     */

    static Connection con = null;
    static Statement statement = null;
    public void jdbcConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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
     * gets all modules from db
     * @return modules
     * @throws SQLException
     */
    public String getModulsFromDb() throws SQLException {
        String query = "SELECT * from modul";
        String modules = null;
         try {
             statement = con.createStatement();
             ResultSet result = statement.executeQuery(query);
             while (result.next()) {
                 modules = result.getString("modulname");
                 System.out.println(modules);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return modules;
    }

    /**
     * Returns all studendts from db
     * @return Students
     * @throws SQLException
     */
    public  String getSchuelerFromDb() throws SQLException {
        String query = "SELECT * from schueler";
        String schueler = null;
        try {
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                schueler = result.getString("name");
                System.out.println(schueler);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schueler;
    }

    /**
     * Get to db for all shool grades
     * @return school grades
     * @throws SQLException
     */
    public  double getGradeFromDb() throws SQLException {
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

}
