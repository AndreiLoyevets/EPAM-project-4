package aloyevets.epam.project_4.model.logic;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aloyevets.epam.project_4.model.dao.UserDAO;
import aloyevets.epam.project_4.model.entity.User;

public class UpdateUsersCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> params = request.getParameterNames();
		Pattern pattern = Pattern.compile("(select)([0-9]+)");
		Matcher matcher = null;
		ArrayList<User> activateUsers = null, deleteUsers = null;
		 
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			
			matcher = pattern.matcher(param);
			
			// Filter select parameters.
			if (matcher.matches()) {
				int id = Integer.valueOf(matcher.group(2));
				String action = request.getParameter(param);
				
				if (action.equals("activate")) {
					if (activateUsers == null) {
						activateUsers = new ArrayList<User>();
					}
					User user = new User();
					user.setId(id);
					activateUsers.add(user);
				} else if (action.equals("delete")) {
					if (deleteUsers == null) {
						deleteUsers = new ArrayList<User>();
					}
					User user = new User();
					user.setId(id);
					deleteUsers.add(user);
				}
			}
		}
		
		UserDAO userDAO = daoFactory.getUserDAO();
		userDAO.activateBatch(activateUsers);
		userDAO.deleteBatch(deleteUsers);
		
		// Update unconfirmed users list for administrator.
		Iterable<User> users = daoFactory.getUserDAO().findUnconfirmed();
					
		request.setAttribute("unconfirmedUsers", users);
	}

}