package aloyevets.epam.project_4.model.dao;

import aloyevets.epam.project_4.model.entity.Route;

public interface RouteDAO {
	Iterable<Route> findRoutes(Route route); /* Find the route by departure and
	                                          * destination stations, departure
	                                          * time.
	                                          */
}
