package com.xadmin.usernamagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usernamagement.bean.Users;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:2301/userdb?useSSL=false";
	private String UserName = "Tuandaca";
	private String Password = "Tuandaca23";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	private static final String Insert_Users_SQL = "INSERT INTO users"
			+ "(name, email, phoneNumber, isActive, dateCreated) VALUES(?,?,?,?,?);";

	private static final String Select_User_by_ID = "SELECT * FROM users WHERE id = ?;";

	private static final String Select_All_Users = "SELECT * FROM users;";

	private static final String Delete_User_by_ID = "DELETE FROM users WHERE id = ?;";

	private static final String Update_User_by_ID = "UPDATE users SET name = ?, email = ?, "
			+ "phoneNumber = ?, isActive = ?, dateCreated = ? WHERE id = ?;";

	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, UserName, Password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// INSERT
	public void insertUser(Users user) throws SQLException {
		System.out.println(Insert_Users_SQL);
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(Insert_Users_SQL)) {
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPhoneNumber());
			String isActive = user.isActive() ? "true" : "false"; 
			prepareStatement.setString(4, isActive);
			prepareStatement.setString(5, user.getDateCreated());
			System.out.println(prepareStatement);
			prepareStatement.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	//SELECT USER BY ID
	public Users selectUserById(int id) {
		Users user = null;
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(Select_User_by_ID);){
			prepareStatement.setInt(1, id);
			System.out.println(prepareStatement);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				Boolean isActive = rs.getBoolean("íActive");
				String dateCreated = rs.getString("dateCreated");
				user = new Users(id, name, email, phoneNumber, isActive, dateCreated);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return user;
	}
	
	public List<Users> selectAllUsers() {
		List<Users> users = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(Select_All_Users);){
			
			System.out.println(prepareStatement);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				Boolean isActive = rs.getBoolean("íActive");
				String dateCreated = rs.getString("dateCreated");
				users.add(new Users(id, name, email, phoneNumber, isActive, dateCreated));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return users;
	}
	
	//UPDATE USER
	public boolean updateUser(Users user) throws SQLException{
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(Update_User_by_ID);) {
			System.out.println("UPDATE user: " + prepareStatement);
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPhoneNumber());
			String isActive = user.isActive() ? "true" : "false";
			prepareStatement.setString(4, isActive);
			prepareStatement.setString(5, user.getDateCreated());
			prepareStatement.setInt(6, user.getId());
			
			rowUpdated = prepareStatement.executeUpdate() > 0;
		}
		
		return rowUpdated;
	}
	
	//DELETE USER BY ID
	public boolean deleteUser(int id) throws SQLException{
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(Delete_User_by_ID);){
			prepareStatement.setInt(1, id);
			rowDeleted = prepareStatement.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		for(Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}	
	}
	
	

}
