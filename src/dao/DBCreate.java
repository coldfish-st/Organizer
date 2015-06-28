package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Create a Derby database.
 * @author Zhenge Jia 2015
 *
 */

public class DBCreate {
	
	static Connection conn;
	
	/**
	 * Use SQL query to create a Derby database in jvm.
	 */
	public static void getCreate(){
		
		String driver ="org.apache.derby.jdbc.EmbeddedDriver";
		String connectionURL ="jdbc:derby:myaddress;create = true";
		String createString ="CREATE TABLE MyAddress(Name VARCHAR(25), Sex  VARCHAR(6),  Birthday DATE,   Mobile VARCHAR(20),Tele   VARCHAR(20), Face_QQ VARCHAR(20), Home_Add VARCHAR(45),Com_Add  VARCHAR(45),Email VARCHAR(20),GroupJia VARCHAR(15),Note VARCHAR(80), photo BLOB)";
		try {
			Class.forName(driver);
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(connectionURL);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createString);
			
			System.out.println("success");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		getCreate();
	}
	

}
