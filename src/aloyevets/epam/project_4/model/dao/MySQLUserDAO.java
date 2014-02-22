package aloyevets.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aloyevets.epam.project_4.model.entity.User;

public class MySQLUserDAO implements UserDAO {

	@Override
	public int insertUser(User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement("INSERT INTO users "
					+ "(email, password, firstName, surname,phone) values "
					+ "(?, ?, ?, ?, ?)");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getSurname());
			statement.setString(5, user.getPhone());
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			return -1;
		} finally {
			
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("MySQLUserDAO.insertUser() cannot close statement");
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("MySQLUserDAO.insertUser() cannot close connection");
				}
			}
		}
	}

	@Override
	public User findUser(User user) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		
		try {
			
			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM users WHERE email = ? AND password = ?");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			
			resultSet = statement.executeQuery();

			if (resultSet.first()) {
				
				// Retrieve information from the result set.
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("firstName"));
				user.setSurname(resultSet.getString("surname"));
				user.setPhone(resultSet.getString("phone"));
				int isActivated = Integer.valueOf(
						resultSet.getString("isActivated"));
				
				user.setActivated((isActivated == 0) ? false : true);
				
				return user;
			}
		} catch (SQLException e) {
			System.out.println("MySQLUserDAO.findUser() error");
		} finally {
			
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out.println("MySQLUserDAO.findUser() cannot close resultSet");
				}
			}
			
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("MySQLUserDAO.findUser() cannot close statement");
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("MySQLUserDAO.findUser() cannot close connection");
				}
			}
		}

		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
