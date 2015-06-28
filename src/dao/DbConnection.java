package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	/**
	 * Build up  a connection with database in jvm.
	 * @author Zhenge Jia 2015
	 * 
	 */

public class DbConnection {
	
	
	/**
	 *  The method is to connect with database
	 * @return Connection
	 */
	public static Connection getConnection(){
		Connection con = null;
		
		String url = "jdbc:derby:myaddress"; //Driver for the connection with Derby	
		try {
			//192.168.?????
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection(url);
			System.out.println("Connect successfully");
			return con;
		}catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 *  Receive param which type is Connection and close the connetion with database. 
	 * @param con
	 */
	
	public static void closeConnection(Connection con){
		if(con != null){
			try {
				con.close();
				con = null;
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Leave for test connection.
	 * @param args
	 */
	
	public static void main(String args[]){
		getConnection();
	}
	
}
