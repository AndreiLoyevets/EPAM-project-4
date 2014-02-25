package aloyevets.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import aloyevets.epam.project_4.model.entity.Route;

public class MySQLRouteDAO implements RouteDAO {

	@Override
	public Iterable<Route> findRoutes(Route route) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		ArrayList<Route> routes = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM routes WHERE departureStation = ?"
					+ "AND destinationStation = ? AND departureTime > ?");
			statement.setString(1, route.getDepartureStation());
			statement.setString(2, route.getDestinationStation());
			statement.setTime(3, route.getDepartureTime());

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				if (routes == null) {
					routes = new ArrayList<Route>();
				}
				Route resRoute = new Route();
				
				// Retrieve information from the result set.
				resRoute.setId(resultSet.getString("id"));
				resRoute.setDepartureStation(resultSet.getString("departureStation"));
				resRoute.setDestinationStation(resultSet.getString("destinationStation"));
				resRoute.setDepartureTime(Time.valueOf(resultSet.getString("departureTime")));
				resRoute.setDestinationTime(Time.valueOf(resultSet.getString("destinationTime")));
				resRoute.setSuitPlaces(resultSet.getInt("suitePlaces"));
				resRoute.setCoupePlaces(resultSet.getInt("coupePlaces"));
				resRoute.setBerthPlaces(resultSet.getInt("berthPlaces"));
				
				routes.add(resRoute);
			}
			
			return routes;
		} catch (SQLException e) {
			System.out.println("MySQLRouteDAO.findRoutes() error");
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLRouteDAO.findRoutes() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLRouteDAO.findRoutes() cannot close statement");
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out
							.println("MySQLRouteDAO.findRoutes() cannot close connection");
				}
			}
		}

		return null;
	}
}
