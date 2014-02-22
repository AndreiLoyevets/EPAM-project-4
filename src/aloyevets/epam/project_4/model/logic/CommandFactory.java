package aloyevets.epam.project_4.model.logic;

import java.util.HashMap;

import aloyevets.epam.project_4.model.dao.DAOFactory;
import aloyevets.epam.project_4.model.dao.DAOFactory.Factories;

public class CommandFactory {
	private HashMap<String, Command> commands;
	private static CommandFactory instance;
	
	private CommandFactory() {
		commands = new HashMap<String, Command>();
		
		// set DAOFactory for all commands
		Command.setDAOFactory(DAOFactory.getDAOFactory(Factories.MYSQL));
		
		// create commands and set mapping
		commands.put("user_login", new UserLoginCommand());
		commands.put("admin_login", new AdminLoginCommand());
		commands.put("create_user", new CreateUserCommand());
	}
	
	public static synchronized CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		
		return instance;
	}
	
	public Command getCommand(String id) {
		return commands.get(id);
	}
}