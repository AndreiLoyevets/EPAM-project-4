package aloyevets.epam.project_4.model.entity;

import java.sql.Date;

public class Train {
	private int id;
	private String routeID;
	private Date date;
	private int suiteReserved;
	private int coupeReserved;
	private int berthReserved;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRouteID() {
		return routeID;
	}
	
	public void setRouteID(String routeID) {
		this.routeID = routeID;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getSuiteReserved() {
		return suiteReserved;
	}
	
	public void setSuiteReserved(int suiteReserved) {
		this.suiteReserved = suiteReserved;
	}
	
	public int getCoupeReserved() {
		return coupeReserved;
	}
	
	public void setCoupeReserved(int coupeReserved) {
		this.coupeReserved = coupeReserved;
	}
	
	public int getBerthReserved() {
		return berthReserved;
	}
	
	public void setBerthReserved(int berthReserved) {
		this.berthReserved = berthReserved;
	}
}
