package ch.csbe.noten;

import javafx.collections.ObservableList;

public class GlobalConstants {

    /**
     * Navigation
     */
    /*needed for loading overview scene*/
    private String overview = "overview";
    /*needed for loading add grade scene */
    private String addGrade = "addGrade";
    /*needed for loading add student scene*/
    private String addStud = "addStud";

    public String getOverview() {
        return overview;
    }

    public String getAddGrade() {
        return addGrade;
    }

    public String getAddStud() {
        return addStud;
    }

    /**
     * Global constants for storing informations
     */

    private ObservableList<Student> studentList;
}
