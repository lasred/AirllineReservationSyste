package org.coenraets.cellar;

import java.sql.Date;

public class Flight {

    private int id;

    private String origin;
    
    private String destination;
    
    private Date departDate;

    private double basePrice;
    
    private Airline airline;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

    public void setDepartDate(Date departDate) {
    	this.departDate = departDate;
    }
    
    public Date getDepartDate() {
    	return departDate;
    }
    
    public void setAirline(Airline airline) {
    	this.airline = airline;
    }
    
    public Airline getAirline() {
    	return airline;
    }
}
