package aloyevets.epam.project_4.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aloyevets.epam.project_4.model.entity.User;
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
	public void init(ServletConfig config) throws ServletException {
		// create database and all needed classes here
		System.out.println("Init");
	}
	
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
		//User user = null;
		
		if (userPath.equals("/login")) {
			url += "login.jsp";
		} else if (userPath.equals("/check")) {
			//commands.getCommand("login");
			//command.setAttributes(null);
			//command.execute();
			//user = (User) command.getResult();
			
			url += "check.jsp";
		} else if (userPath.equals("/registration")) {
			url += "registration.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
