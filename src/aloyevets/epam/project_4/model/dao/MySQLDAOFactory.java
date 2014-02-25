package aloyevets.epam.project_4.model.dao;

public class MySQLDAOFactory extends DAOFactory {    
	@Override
	public UserDAO getUserDAO() {
		return new MySQLUserDAO();
	}

	@Override
	public TrainDAO getTrainDAO() {
		return new MySQLTrainDAO();
	}

	@Override
	public RouteDAO getRouteDAO() {
		return new MySQLRouteDAO();
	}
}
