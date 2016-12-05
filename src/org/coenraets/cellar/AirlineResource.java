package org.coenraets.cellar;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/airlines")
public class AirlineResource {

	AirlineDAO dao = new AirlineDAO();

	@GET
	//specify MIME type that will be sent back to the client
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Airline> findAll() {
		System.out.println("findAll");
		return dao.findAll();
	}
}
