package ir.maktab.bours.userfeature.impl;

import ir.maktab.bours.core.annotations.configuration.DataStore;
import ir.maktab.bours.core.share.AuthenticationService;
import ir.maktab.bours.model.User;
import ir.maktab.bours.userfeature.usecase.LoginUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUseCaseImpl implements LoginUseCase {
    public User login(String username, String password) {
        // get connection
        DataStore dataStore=new DataStore();
        try {
            Connection connection = dataStore.createConnection();
            String sql = "select * from user where " +
                    " user_name = ? and password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getInt("id"),
                        resultSet.getInt("national_id")
                );
                AuthenticationService.getInstance().setLoginUser(user);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return result
        return null;
    }
}
