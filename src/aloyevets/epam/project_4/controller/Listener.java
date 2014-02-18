package aloyevets.epam.project_4.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import aloyevets.epam.project_4.model.logic.CommandFactory;

public class Listener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Context initialized!");
		// initialize CommandFactory, DAOFactory
		CommandFactory.getInstance();
	}

}
