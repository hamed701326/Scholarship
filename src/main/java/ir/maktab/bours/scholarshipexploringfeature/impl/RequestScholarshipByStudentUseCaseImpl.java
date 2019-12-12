package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.Service;
import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.usecase.RequestScholarshipByStudentUseCase;

import java.sql.*;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {


    public void setScholarship(Scholarship scholarship) {
        DataStore dataStore=new DataStore();
        Connection connection=null;
        PreparedStatement ps =null;
        String sql="insert into scholarship values (?,?,?,?,?" +
                ",?,?,?,?,?,null,'RequestScholarshipByStudent')";

        try{
            connection=dataStore.createConnection();
            ps =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,scholarship.getBasicInformation().getNationalId());
            ps.setString(2,scholarship.getBasicInformation().getName());
            ps.setString(3,scholarship.getBasicInformation().getLastName());
            ps.setString(4,scholarship.getLastDegree().getUniversity());
            ps.setString(5,scholarship.getLastDegree().getGrade());
            ps.setString(6,scholarship.getLastDegree().getField());
            ps.setDouble(7,scholarship.getLastDegree().getGp());
            ps.setString(8,scholarship.getAcceptDegree().getUniversity());
            ps.setString(9,scholarship.getAcceptDegree().getGrade());
            ps.setString(10,scholarship.getAcceptDegree().getField());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
