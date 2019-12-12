package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.Service;
import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.usecase.RequestScholarshipByStudentUseCase;

import java.sql.*;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {


    public void setScholarship(Scholarship scholarship) {
        DataStore dataStore = new DataStore();
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement ps1=null;
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {
                String sql = "insert into scholarship values (?,?,?,?,?" +
                        ",?,?,?,?,?,null,'RequestByStudent')";
                String sql1 = "insert into scholarship_log values" +
                        " (null,'RequestByStudent',now(),?,?)";

                try {
                    connection = dataStore.createConnection();
                    ps1=connection.prepareStatement(sql1);
                    ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, scholarship.getBasicInformation().getNationalId());
                    ps.setString(2, scholarship.getBasicInformation().getName());
                    ps.setString(3, scholarship.getBasicInformation().getLastName());
                    ps.setString(4, scholarship.getLastDegree().getUniversity());
                    ps.setString(5, scholarship.getLastDegree().getGrade());
                    ps.setString(6, scholarship.getLastDegree().getField());
                    ps.setDouble(7, scholarship.getLastDegree().getGp());
                    ps.setString(8, scholarship.getAcceptDegree().getUniversity());
                    ps.setString(9, scholarship.getAcceptDegree().getGrade());
                    ps.setString(10, scholarship.getAcceptDegree().getField());
                    if (ps.executeUpdate() != 0) {
                        System.out.println("Request Scholarship is done.");
                        ResultSet rs=ps.getGeneratedKeys();
                        if(rs.next()) {
                            ps1.setInt(1,loginUser.getId() );
                            ps1.setInt(2, rs.getInt(1));
                        }
                        if(ps1.executeUpdate()!=0){
                            System.out.println("Request Scholarship insert into log.");
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        ps.close();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
