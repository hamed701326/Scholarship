package ir.maktab.bours.model.scholarship;

public class BasicInformation{
    private String name;
    private String lastName;
    private int nationalId;

    public BasicInformation(String name, String lastName, int nationalId) {
        this.name = name;
        this.lastName = lastName;
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }
}
