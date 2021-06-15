package ch.csbe.noten;

public class Student {
    private String firstName;
    private String lastName;
    private int id;

    public Student(String firstNameParam, String lastNameParam, int idParam){
        this.firstName = firstNameParam;
        this.lastName = lastNameParam;
        this.id = idParam;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }
}
