package org.coenraets.cellar;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 */
@XmlRootElement
public class Reservation {

    private int id;

    private String origin;
    
    private String destination;
    
    private double pricePaid;

    private boolean isRoundTrip;
    
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

	public double getPricePaid() {
		return pricePaid;
	}
	
	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

    public boolean getIsRoundTrip() {
    	return isRoundTrip;
    }
    
    public void setIsRoundTrip(boolean isRoundTrip) {
    	this.isRoundTrip = isRoundTrip;
    }

}
