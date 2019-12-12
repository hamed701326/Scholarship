package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.model.scholarship.BasicInformation;
import ir.maktab.bours.model.scholarship.Degree;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.usecase.FindScholarshipByManagerUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindScholarshipByManagerUseCaseImpl implements FindScholarshipByManagerUseCase {
    public List<Scholarship> listScholarships() {
        DataStore dataStore=new DataStore();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList<Scholarship>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Supervisor")) {
                // connection
                Connection connection = null;
                try {
                    connection =dataStore.createConnection() ;
                    // query
                    String sql = "select * from scholarship where status = 'AcceptedBySuperviser' ";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        Scholarship scholarship = new Scholarship(rs.getString("status"),
                                new BasicInformation(
                                        rs.getString("first_name"),
                                        rs.getString("last_name"),
                                        rs.getLong("national_id")
                                ),
                                new Degree(
                                        rs.getString("last_university"),
                                        rs.getString("last_grade"),
                                        rs.getDouble("gp"),
                                        rs.getString("last_field")
                                ),
                                new Degree(
                                        rs.getString("destination_university"),
                                        rs.getString("destination_grade"),
                                        null,
                                        rs.getString("destination_field")
                                ));
                        result.add(scholarship);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
