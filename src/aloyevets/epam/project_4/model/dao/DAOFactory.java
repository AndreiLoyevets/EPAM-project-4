package aloyevets.epam.project_4.model.dao;

public abstract class DAOFactory {
	
	// The list of supported databases.
	public enum Factories {
		MYSQL
	}
	
	public abstract UserDAO getUserDAO();
	public abstract TrainDAO getTrainDAO();
	// TODO add more DAO objects
	
	public static DAOFactory getDAOFactory(Factories factoryName) {
		switch (factoryName) {
		case MYSQL: return new MySQLDAOFactory();
		default: return null;
		}
	}
	
}
