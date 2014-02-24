package aloyevets.epam.project_4.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		request.getSession().removeAttribute("user");
	}
}
