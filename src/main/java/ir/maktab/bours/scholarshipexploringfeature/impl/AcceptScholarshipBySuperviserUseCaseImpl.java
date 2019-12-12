package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.scholarshipexploringfeature.usecase.AcceptScholarshipBySuperviserUseCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcceptScholarshipBySuperviserUseCaseImpl implements AcceptScholarshipBySuperviserUseCase {
    public void updateScholarship(int idScholarship) {
        DataStore dataStore=new DataStore();
        Connection connection=null;
        Statement stmt=null;
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Supervisor")) {

                // insert into  scholarship log
                String sql = "insert into scholarship_log(action,date,user_id,fk_scholarship)" +
                        " values('AcceptScholarshipBySuperviser'," +
                        "now()," +
                        "" + loginUser.getId() + "" +
                        "," + idScholarship + ")";
                // update status
                String sql1 = "update scholarship set status='AcceptedBySuperviser'" +
                        " where scholar_id=" + idScholarship;
                try {
                    connection = dataStore.createConnection();
                    stmt = connection.createStatement();
                    if (stmt.execute(sql))
                        System.out.println("Accept Scholarship by Superviser inserted into scholarship log.");
                    if (stmt.execute(sql1)) System.out.println("status updated for scholar with id=" + idScholarship);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
