package org.coenraets.cellar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ReservationDAO {

    public List<Reservation> findAll() {
        List<Reservation> list = new ArrayList<Reservation>();
        Connection c = null;
    	String sql = "SELECT * FROM Reservation ORDER BY reservation_id";
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

    
//    public List<Wine> findByName(String name) {
//        List<Wine> list = new ArrayList<Wine>();
//        Connection c = null;
//    	String sql = "SELECT * FROM wine as e " +
//			"WHERE UPPER(name) LIKE ? " +	
//			"ORDER BY name";
//        try {
//            c = ConnectionHelper.getConnection();
//            PreparedStatement ps = c.prepareStatement(sql);
//            ps.setString(1, "%" + name.toUpperCase() + "%");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(processRow(rs));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//		} finally {
//			ConnectionHelper.close(c);
//		}
//        return list;
//    }
    
    public Reservation findById(int id) {
    	String sql = "SELECT * FROM Reservation WHERE reservation_id = ?";
        Reservation res = null;
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

    public Reservation save(Reservation reservation)
	{
		return reservation.getId() > 0 ? update(reservation) 
				: create(reservation);
	}    
    
    public Reservation create(Reservation res) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO Reservation "
            		+ "(price_paid, is_round_trip,"
            		+ " origin, destination) VALUES (?, ?, ?, ?)",
                new String[] { "ID" });
            ps.setDouble(1, res.getPricePaid());
            ps.setBoolean(2, res.getIsRoundTrip());
            ps.setString(3, res.getOrigin());
            ps.setString(4, res.getDestination());
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

    public Reservation update(Reservation res) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE Reservation"
            		+ " SET price_paid=?, is_round_trip=?, "
            		+ "origin=?, destination=?"
            		+ "WHERE reservation_id=?");
            ps.setDouble(1, res.getPricePaid());
            ps.setBoolean(2, res.getIsRoundTrip());
            ps.setString(3, res.getOrigin());
            ps.setString(4, res.getDestination());
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
            PreparedStatement ps = c.prepareStatement("DELETE FROM Reservation WHERE reservation_id=?");
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

    protected Reservation processRow(ResultSet rs) throws SQLException {
    	Reservation res = new Reservation();
    	res.setId(rs.getInt("reservation_id"));
    	res.setOrigin(rs.getString("origin"));
    	res.setDestination(rs.getString("destination"));
    	res.setPricePaid(rs.getDouble("price_paid"));
    	res.setIsRoundTrip(rs.getBoolean("is_round_trip"));
        return res;
    }
    
}
