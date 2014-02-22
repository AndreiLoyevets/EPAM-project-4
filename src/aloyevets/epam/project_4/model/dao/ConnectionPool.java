package aloyevets.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
	private static DataSource dataSource;
	
	public static Connection getConnection() {
		if (dataSource == null) {
			try {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/railway_system");
			} catch (NamingException e) {
				System.out.println("Cannot find the data source");
			}
		}
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Cannot establish connection");
			return null;
		}
	}
}