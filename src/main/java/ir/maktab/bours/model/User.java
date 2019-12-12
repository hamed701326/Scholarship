package ir.maktab.bours.model;

public class User {
    private String userName;
    private String passWord;
    private String role;
    private int id;
    private int nationalId;
    public User(){}
    public User(String userName, String passWord, String role, int id,int nationalId) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.id = id;
        this.nationalId=nationalId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
