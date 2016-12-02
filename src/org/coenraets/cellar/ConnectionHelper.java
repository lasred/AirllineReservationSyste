package org.coenraets.cellar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionHelper{

	public static Connection getConnection() throws SQLException {
		//from observing drivers in the past, essential to the working
		//relationship between mysql and jdbc
		String driver = null;
		String url = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
            //ResourceBundle bundle = ResourceBundle.getBundle("cellar");
            url =  "jdbc:mysql://vergil.u.washington.edu:1060/AirlineReservationSystem";
           // driver = bundle.getString("jdbc.driver");
           // Class.forName(driver);
           //url=bundle.getString("jdbc.url");    	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", "root");
		connectionProperties.put("password", "0021832712");
		return DriverManager.getConnection(
                url,
                 connectionProperties);

	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}