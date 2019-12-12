package ir.maktab.bours.userfeature.impl;

import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.userfeature.usecase.LogUpUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogUpUseCaseImpl implements LogUpUseCase {
    public void createAccount(User user) {
        DataStore dataStore = new DataStore();

        try {
            Connection connection = dataStore.createConnection();
            String sql = "insert into user values  (?,?,?,null ,?); ";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3,user.getRole());

            ps.setInt(4,user.getNationalId());
            int b=ps.executeUpdate();
            if(b!=0) System.out.println("Your account created successfully");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        // return result
    }
}
