package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.model.scholarship.BasicInformation;
import ir.maktab.bours.model.scholarship.Degree;
import ir.maktab.bours.model.scholarship.Scholarship;
import ir.maktab.bours.scholarshipexploringfeature.usecase.DashboardForManagerUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardForManagerUseCaseImpl  implements DashboardForManagerUseCase {
    public void show(){
        DataStore dataStore=new DataStore();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList<Scholarship>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Manager")) {
                // connection
                Connection connection = null;
                try {
                    connection =dataStore.createConnection() ;
                    // query
                    String sql = "select action, count(*) as counter from scholarship_log where action='AcceptBySuperviser'" +
                            "or action='AcceptByManager' or action='RejectByManager'"
                            +" group by action";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        String format="|%1$-20s|%2$-5s\n";
                        System.out.format(format,"Action","Number");
                        System.out.format(format,rs.getString("action")
                                ,rs.getString("counter"));                     }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
