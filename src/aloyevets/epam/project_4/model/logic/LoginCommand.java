package aloyevets.epam.project_4.model.logic;

import aloyevets.epam.project_4.model.entity.User;

public class LoginCommand extends Command {
	private User user;
	
	@Override
	public void setAttributes(Object... attributes) {
		// first parameter is user
		user = (User) attributes[0];
	}

	@Override
	public void execute() {
		// pass user to user DAO
		
		// get response
		
		// update user
	}
	
	@Override
	public Object getResult() {
		return user;
	}
}