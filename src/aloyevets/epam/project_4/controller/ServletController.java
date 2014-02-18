package aloyevets.epam.project_4.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import aloyevets.epam.project_4.model.entity.User;
import aloyevets.epam.project_4.model.logic.Command;
import aloyevets.epam.project_4.model.logic.CommandFactory;

/**
 * Servlet implementation class ServletController
 */
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private DataSource dataSource;
       private Connection connection;
       private Statement statement;
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
		System.out.println("Init");
		Context initContext;
		
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/railway_system");
		} catch (NamingException e) {
			System.out.println("Cannot find data source");
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		// delete all resources
		
		super.destroy();
	}
	
	private void testConnectionPool() {
		ResultSet resultSet = null;
        try {
            // Get Connection and Statement
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM users";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=statement)statement.close();} catch (SQLException e) 
            {e.printStackTrace();}
            try { if(null!=connection)connection.close();} catch (SQLException e) 
            {e.printStackTrace();}
        }
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
			
			//testConnectionPool();
			
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
