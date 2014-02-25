package aloyevets.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aloyevets.epam.project_4.model.entity.Train;

public class MySQLTrainDAO implements TrainDAO {

	@Override
	public Train findTrain(Train train) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM trains WHERE routeID = ? AND date = ?");
			
			statement.setString(1, train.getRouteID());
			statement.setDate(2, train.getDate());

			resultSet = statement.executeQuery();

			if (resultSet.first()) {
				Train resTrain = new Train();
				
				// Retrieve information from the result set.
				resTrain.setId(resultSet.getInt("id"));
				resTrain.setRouteID(resultSet.getString("routeID"));
				resTrain.setDate(resultSet.getDate("date"));
				resTrain.setSuiteReserved(resultSet.getInt("suiteReserved"));
				resTrain.setCoupeReserved(resultSet.getInt("coupeReserved"));
				resTrain.setBerthReserved(resultSet.getInt("berthReserved"));
				
				return resTrain;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("MySQLTrainDAO.findTrain() error");
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLTrainDAO.findTrain() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLTrainDAO.findTrain() cannot close statement");
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLTrainDAO.findTrain() cannot close connection");
				}
			}
		}

		return null;
	}

}
