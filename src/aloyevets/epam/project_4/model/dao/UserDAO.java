package aloyevets.epam.project_4.model.dao;

import aloyevets.epam.project_4.model.entity.User;

public interface UserDAO {
	int insertUser(User user);            // New user is registered.
	User findUser(User user);             // User wants to login.
	public boolean updateUser(User user); // Administrator activates/blocks user.
}
