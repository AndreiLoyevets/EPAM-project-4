package aloyevets.epam.project_4.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aloyevets.epam.project_4.model.dao.UserDAO;
import aloyevets.epam.project_4.model.entity.User;

public class UserLoginCommand extends Command {
	@Override
	public void execute(HttpServletRequest request,
			            HttpServletResponse response) {
		
		User user = new User();
		
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		
		// Pass  the user to UserDAO.
		UserDAO userDAO = daoFactory.getUserDAO();
		
		User dbUser = userDAO.findUser(user);
		
		if (dbUser != null) {
			if (dbUser.isActivated()) {
				HttpSession session = request.getSession(true);
				
				session.setAttribute("user", dbUser);
			} else {
				request.setAttribute("notActivated", true);
			}
		} else {
			
			// User doesn't exist.
			request.setAttribute("notExists", true);
		}
	}
}