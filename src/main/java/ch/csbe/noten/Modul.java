package ch.csbe.noten;

public class Modul {
    private String modulNr;
    private int id;

    public Modul(String modulNrParam, int modulId){
        this.id = modulId;
        this.modulNr = modulNrParam;
    }

    public String getModulNr() {
        return modulNr;
    }

    public int getModulId(){ return id;}

}
