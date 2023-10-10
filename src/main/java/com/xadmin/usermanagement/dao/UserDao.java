package com.xadmin.usermanagement.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.bean.Users;

public class UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/users?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kiet0865272879";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_USER_SQL = "INSERT INTO users (UserName, Email, MobilePhone, IsActive, DateCreated) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE UserID = ?";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE UserID = ?";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE users SET UserName = ?, Email = ?, MobilePhone = ?, IsActive = ?, DateCreated = ? WHERE UserID = ?";

    public UserDao() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }

    public void insertUser(Users user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobilePhone());
            preparedStatement.setBoolean(4, user.isIsActive());
            preparedStatement.setString(5, user.getDateCreated());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Users selectUserById(int id) {
        Users user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<Users> selectAllUsers() {
        List<Users> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users user = mapUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean updateUser(Users user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobilePhone());
            preparedStatement.setBoolean(4, user.isIsActive());
            preparedStatement.setString(5, user.getDateCreated());
            preparedStatement.setInt(6, user.getUserID());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    private Users mapUser(ResultSet resultSet) throws SQLException {
        int userID = resultSet.getInt("UserID");
        String userName = resultSet.getString("UserName");
        String email = resultSet.getString("Email");
        String mobilePhone = resultSet.getString("MobilePhone");
        boolean isActive = resultSet.getBoolean("IsActive");
        String dateCreated = resultSet.getString("DateCreated");
        return new Users(userID, userName, email, mobilePhone, isActive, dateCreated);
    }

    private void printSQLException(SQLException ex) {
        ex.printStackTrace();
        // Handle the SQLException as needed, e.g., log or throw it
    }
}
