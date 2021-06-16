package ch.csbe.noten;

public class Grade {
    private String firstName;
    private String lastName;
    private Double grade;
    private String modul;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Grade(String firstName, String lastName, Double grade, String modul) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.modul = modul;
    }

    public Double getGrade() {
        return grade;
    }

    public String getModul() {
        return modul;
    }


}
