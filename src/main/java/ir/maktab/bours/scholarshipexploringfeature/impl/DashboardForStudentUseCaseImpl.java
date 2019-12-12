package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.usecase.DashboardForStudentUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardForStudentUseCaseImpl implements DashboardForStudentUseCase {
    @Override
    public void show() {
        DataStore dataStore=new DataStore();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList<Scholarship>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Manager")) {
                // connection
                Connection connection = null;
                ResultSet rs;
                try {
                    connection =dataStore.createConnection() ;
                    // query
                    String sql = "select action , date  from scholarship_log scl" +
                            "join scholarship sc on scl.fk_scholarship=sc.scholar_id where national_id= "+loginUser.getNationalId()
                            +" group by date";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        System.out.println("------\tdetails for my scholarship\t------");
                        String format="|%1$-20s|%2$-5s\n";
                        System.out.format(format,"Action","date");
                        System.out.format(format,rs.getString("action")
                                ,rs.getString("date"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
