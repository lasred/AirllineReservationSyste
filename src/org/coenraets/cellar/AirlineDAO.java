package org.coenraets.cellar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO {
	public List<Airline> findAll() {
		List<Airline> list = new ArrayList<Airline>();
	    Connection c = null;
	    String sql = "SELECT * FROM Airline ORDER BY airline_id";
	    try {
	        c = ConnectionHelper.getConnection();
	        Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        while (rs.next()) {
	        	Airline airlineInfo = processRow(rs);
	            list.add(airlineInfo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
       } finally {
           ConnectionHelper.close(c);
       }
	   return list;
	}

	 protected Airline processRow(ResultSet rs) throws SQLException {
	    Airline airline = new Airline();
	    airline.setId(rs.getInt("airline_id"));
	    airline.setName(rs.getString("name"));
	    return airline;
	}
}
