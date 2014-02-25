package aloyevets.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

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
					System.out
							.println("MySQLUserDAO.insertUser() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLUserDAO.insertUser() cannot close connection");
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

			statement = connection
					.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());

			resultSet = statement.executeQuery();

			if (resultSet.first()) {

				// Retrieve information from the result set.
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("firstName"));
				user.setSurname(resultSet.getString("surname"));
				user.setPhone(resultSet.getString("phone"));
				int isActivated = resultSet.getInt("isActivated");

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
					System.out
							.println("MySQLUserDAO.findUser() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLUserDAO.findUser() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLUserDAO.findUser() cannot close connection");
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

	@Override
	public Iterable<User> findUnconfirmed() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		ArrayList<User> users = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM users WHERE isActivated = 0");

			while (resultSet.next()) {
				if (users == null) {
					users = new ArrayList<User>();
				}
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setName(resultSet.getString("firstName"));
				user.setSurname(resultSet.getString("surname"));
				user.setPhone(resultSet.getString("phone"));
				users.add(user);
			}

			return users;
		} catch (SQLException e) {
			System.out.println("MySQLUserDAO.findUnconfirmed() error");
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLUserDAO.findUnconfirmed() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLUserDAO.findUnconfirmed() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLUserDAO.findUnconfirmed() cannot close connection");
				}
			}
		}

		return null;
	}

	@Override
	public int activateBatch(Iterable<User> users) {
		Connection connection = null;
		PreparedStatement statement = null;

		if (users != null) {
			try {

				// Get Connection and create PreparedStatement.
				connection = ConnectionPool.getConnection();

				statement = connection
						.prepareStatement("UPDATE users SET isActivated = 1 WHERE id = ?");

				Iterator<User> iterator = users.iterator();

				while (iterator.hasNext()) {
					statement.setInt(1, iterator.next().getId());
					statement.addBatch();
				}

				int[] result = statement.executeBatch();
				int count = 0;

				System.out.println("Update result:");
				for (int i = 0; i < result.length; ++i) {
					if (result[i] == 1) {
						++count;
					} else {
						// TODO: Add exception here.
						System.out.println("Update command #" + i + "failed: "
								+ result[i]);
					}
				}

				return count;

			} catch (SQLException e) {
				System.out.println("MySQLUserDAO.updateBatch() error");
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						System.out
								.println("MySQLUserDAO.updateBatch() cannot close statement");
					}
				}

				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						System.out
								.println("MySQLUserDAO.updateBatch() cannot close connection");
					}
				}
			}
		}

		return 0;
	}

	@Override
	public int deleteBatch(Iterable<User> users) {
		Connection connection = null;
		PreparedStatement statement = null;

		if (users != null) {
			try {

				// Get Connection and create PreparedStatement.
				connection = ConnectionPool.getConnection();

				statement = connection
						.prepareStatement("DELETE FROM users WHERE id = ?");

				Iterator<User> iterator = users.iterator();

				while (iterator.hasNext()) {
					statement.setInt(1, iterator.next().getId());
					statement.addBatch();
				}

				int[] result = statement.executeBatch();
				int count = 0;

				System.out.println("Delete result:");
				for (int i = 0; i < result.length; ++i) {
					if (result[i] == 1) {
						++count;
					} else {
						// TODO: Add exception here.
						System.out.println("Delete command #" + i + "failed: "
								+ result[i]);
					}
				}

				return count;

			} catch (SQLException e) {
				System.out.println("MySQLUserDAO.deleteBatch() error");
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						System.out
								.println("MySQLUserDAO.deleteBatch() cannot close statement");
					}
				}

				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						System.out
								.println("MySQLUserDAO.deleteBatch() cannot close connection");
					}
				}
			}
		}

		return 0;
	}
}