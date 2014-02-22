package aloyevets.epam.project_4.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aloyevets.epam.project_4.model.logic.Command;
import aloyevets.epam.project_4.model.logic.CommandFactory;

/**
 * Servlet implementation class ServletController
 */
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {}
	
	@Override
	public void destroy() {
		// delete all resources
		
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		String url = "/WEB-INF/view/";
		
		//CommandFactory commands = CommandFactory.getInstance();
		//Command command = null;
		
		if (userPath.equals("/login")) {
			url += "login.jsp";
		} else if (userPath.equals("/registration")) {
			url += "registration.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		String url = "/WEB-INF/view/";
		
		HttpSession session = request.getSession();
		CommandFactory commands = CommandFactory.getInstance();
		Command command = null;
		
		if (userPath.equals("/check-login")) {
			
			// Get pressed button.
			String buttonUser = request.getParameter("userLogin");
			String buttonAdmin = request.getParameter("adminLogin");
			
			if (buttonUser != null) {
				
				// User login button was pressed.
				command = commands.getCommand("user_login");
				command.execute(request, response);
				
				// Check whether the user was found.
				if (session.getAttribute("user") == null) {
					url += "login.jsp";
				} else {
					url += "check.jsp";
				}
			} else if (buttonAdmin != null) {
				
				// Admin login button was pressed.
				command = commands.getCommand("admin_login");
				command.execute(request, response);
				
				if (session.getAttribute("admin") == null) {
					url += "login.jsp";
				} else {
					url += "check.jsp";
				}
			}
			
		} else if (userPath.equals("/check-registration")) {
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
