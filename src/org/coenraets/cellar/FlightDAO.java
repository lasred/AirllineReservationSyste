package org.coenraets.cellar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/* Can probably be refactored into one class. Look at this later */
public class FlightDAO {
	public List<Flight> findAll() {
		List<Flight> list = new ArrayList<Flight>();
	    Connection c = null;
	    String sql = "SELECT * FROM Flight ORDER BY flight_id";
	    try {
	        c = ConnectionHelper.getConnection();
	        Statement s = c.createStatement();
	        ResultSet rs = s.executeQuery(sql);
	        while (rs.next()) {
	            list.add(processRow(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
       } finally {
           ConnectionHelper.close(c);
       }
	   return list;
	}
	
    public Flight findById(int id) {
       String sql = "SELECT * FROM Flight WHERE flight_id = ?";
	   Flight res = null;
	   Connection c = null;
	   try {
	       c = ConnectionHelper.getConnection();
	       PreparedStatement ps = c.prepareStatement(sql);
	       ps.setInt(1, id);
	       ResultSet rs = ps.executeQuery();
	       if (rs.next()) {
	           res = processRow(rs);
	       }
	   } catch (Exception e) {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	   } finally {
	       ConnectionHelper.close(c);
	   }
	   return res;
	}
    
    public Flight save(Flight flight){
        return flight.getId() > 0 ? update(flight) : create(flight);
	}    
	    
	public Flight create(Flight res) {
	    Connection c = null;
        PreparedStatement ps = null;
	    try {
	       c = ConnectionHelper.getConnection();
	       ps = c.prepareStatement("INSERT INTO Flight "
	           + "(base_price, origin, destination,"
	           + " depart_date) VALUES (?, ?, ?, ?)", new String[] { "ID" });
	       ps.setDouble(1, res.getBasePrice());
	       ps.setString(2, res.getOrigin());
	       ps.setString(3, res.getDestination());
	       ps.setDate(4, res.getDepartDate());
	       ps.executeUpdate();
	       ResultSet rs = ps.getGeneratedKeys();
	       rs.next();
	       // Update the id in the returned object. This is important as this value must be returned to the client.
	       int id = rs.getInt(1);
	       res.setId(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
	    } finally {
			ConnectionHelper.close(c);
		}
	    return res;
	 }

     public Flight update(Flight res) {
        Connection c = null;
	    try {
	       c = ConnectionHelper.getConnection();
	       PreparedStatement ps = c.prepareStatement("UPDATE Flight"
	          + " SET base_price=?, origin=?, "
	          + "destination=?, depart_date=?"
	          + "WHERE flight_id=?");
	       ps.setDouble(1, res.getBasePrice());
	       ps.setString(2, res.getOrigin());
	       ps.setString(3, res.getDestination());
	       ps.setDate(4, res.getDepartDate());
	       ps.setInt(5, res.getId());
	       ps.executeUpdate();
	    } catch (SQLException e) {
	       e.printStackTrace();
	       throw new RuntimeException(e);
		} finally {
           ConnectionHelper.close(c);
		}
        return res;
     }

     public boolean remove(int id) {
	    Connection c = null;
	    try {
	       c = ConnectionHelper.getConnection();
	       PreparedStatement ps = c.prepareStatement("DELETE FROM Flight WHERE flight_id=?");
	       ps.setInt(1, id);
	       int count = ps.executeUpdate();
	       return count == 1;
	    } catch (Exception e) {
	       e.printStackTrace();
	       throw new RuntimeException(e);
	    } finally {
		   ConnectionHelper.close(c);
		}
     }

	 protected Flight processRow(ResultSet rs) throws SQLException {
	    Flight res = new Flight();
	    res.setId(rs.getInt("flight_id"));
	    res.setOrigin(rs.getString("origin"));
	    res.setDestination(rs.getString("destination"));
	    res.setDepartDate(rs.getDate("depart_date"));
	    return res;
	}
}
