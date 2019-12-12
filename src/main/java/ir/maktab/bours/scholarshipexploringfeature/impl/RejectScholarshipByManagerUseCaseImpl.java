package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.Service;
import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.scholarshipexploringfeature.usecase.RejectScholarshipByManagerUseCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class RejectScholarshipByManagerUseCaseImpl implements RejectScholarshipByManagerUseCase {
    public void updateStatus(int idScholarship) {
        DataStore dataStore = new DataStore();
        Connection connection = null;
        Statement stmt = null;
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Manager")) {

                // insert into  scholarship log
                String sql = "insert into scholarship_log(action,date,user_id,fk_scholarship)" +
                        " values('RejectScholarshipByManager'," +
                        "now()," +
                        "" + loginUser.getId() + "" +
                        "," + idScholarship + ")";
                // update status
                String sql1 = "update scholarship set status='RejectedByManager'" +
                        " where scholar_id=" + idScholarship+" and status='AcceptedBySuperviser' ";
                try {
                    connection = dataStore.createConnection();
                    stmt = connection.createStatement();
                    if (stmt.execute(sql1)) {
                        stmt.execute(sql);
                        System.out.println("status updated for scholar with id=" + idScholarship);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}