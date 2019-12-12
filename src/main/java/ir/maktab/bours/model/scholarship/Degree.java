package ir.maktab.bours.model.scholarship;

public class Degree{
    private String university;
    private String  grade;
    private Double gp;
    private String field;

    public Degree(String university, String grade, Double gp, String field) {
        this.university = university;
        this.grade = grade;
        this.gp = gp;
        this.field = field;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getGp() {
        return gp;
    }

    public void setGp(double gp) {
        this.gp = gp;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
