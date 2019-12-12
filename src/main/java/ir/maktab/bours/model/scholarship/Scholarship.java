package ir.maktab.bours.model.scholarship;

public class Scholarship {
    private int id;
    private String status;
    private BasicInformation basicInformation;
    private Degree lastDegree;
    private Degree acceptDegree;

    public Scholarship(String status, BasicInformation basicInformation, Degree lastDegree, Degree acceptDegree) {
        this.status = status;
        this.basicInformation = basicInformation;
        this.lastDegree = lastDegree;
        this.acceptDegree = acceptDegree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public Degree getLastDegree() {
        return lastDegree;
    }

    public void setLastDegree(Degree lastDegree) {
        this.lastDegree = lastDegree;
    }

    public Degree getAcceptDegree() {
        return acceptDegree;
    }

    public void setAcceptDegree(Degree acceptDegree) {
        this.acceptDegree = acceptDegree;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "status='" + status + '\'' +
                ", basicInformation=" + basicInformation +
                ", lastDegree=" + lastDegree +
                ", acceptDegree=" + acceptDegree +
                '}';
    }
}

