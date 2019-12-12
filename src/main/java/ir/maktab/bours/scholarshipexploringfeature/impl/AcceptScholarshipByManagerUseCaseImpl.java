package ir.maktab.bours.scholarshipexploringfeature.impl;

import ir.maktab.bours.core.annotations.Service;
import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.scholarshipexploringfeature.usecase.AcceptScholarshipByManagerUseCase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class AcceptScholarshipByManagerUseCaseImpl implements AcceptScholarshipByManagerUseCase {

    public void updateScholarship(int idScholarship) {
        DataStore dataStore=new DataStore();
        Connection connection=null;
        Statement stmt=null;
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Manager")) {

                // insert into  scholarship log
                String sql = "insert into scholarship_log(action,date,user_id,fk_scholarship)" +
                        " values('AcceptByManager'," +
                        "now()," +
                        "" + loginUser.getId() + "" +
                        "," + idScholarship + ")";
                // update status
                String sql1 = "update scholarship set status='AcceptByManager'" +
                        " where scholar_id=" + idScholarship;
                try {
                    connection = dataStore.createConnection();
                    stmt = connection.createStatement();
                    if (stmt.executeUpdate(sql)!=0)
                        System.out.println("Accept Scholarship by Manager inserted into scholarship log.");
                    if (stmt.executeUpdate(sql1)!=0) System.out.println("status updated for scholar with id=" + idScholarship);
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
