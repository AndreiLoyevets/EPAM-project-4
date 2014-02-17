package aloyevets.epam.project_4.model.logic;

import aloyevets.epam.project_4.model.dao.DAOFactory;

public abstract class Command {
	protected static DAOFactory daoFactory;
	
	public static void setDAOFactory(DAOFactory factory) {
		daoFactory = factory;
	}
	public void setAttributes(Object ... attributes) {}
	public abstract void execute();
	public Object getResult() {
		return null;
	}
}