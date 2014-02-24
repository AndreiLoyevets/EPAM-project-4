package aloyevets.epam.project_4.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aloyevets.epam.project_4.model.entity.User;

public class AdminLoginCommand extends Command {
	private static final String ADMIN_EMAIL = "admin@admin.com";
	private static final String ADMIN_PASSWORD = "admin";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
			request.removeAttribute("notExists");
			
			// Create User instance and set administrator for current session.
			User user = new User();
			user.setEmail(ADMIN_EMAIL);
			user.setPassword(ADMIN_PASSWORD);
			user.setActivated(true);
			
			request.getSession(true).setAttribute("admin", user);
			
			// Create a list of unconfirmed users.
			Iterable<User> users = daoFactory.getUserDAO().findUnconfirmed();
			
			request.setAttribute("unconfirmedUsers", users);
			
		} else {
			request.setAttribute("notExists", true);
		}
	}
}