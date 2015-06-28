package organiser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import dao.DbConnection;

/**
 * All methods in the class obtain data from database through connection of it.
 * Most method will return data in ArrayList form.
 * 
 * @author Zhenge Jia 2015
 *
 */
public class SimpleQuery {

	/**
	 * The method is to get all names stored in database
	 * and return a ArrayList<String>.
	 * @return name
	 */
	public static ArrayList<String> findAllNames() {

		ArrayList<String> name = new ArrayList<String>();
		Connection con = DbConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "SELECT Name FROM MyAddress";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				name.add(rs.getString(1));
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}
		return name;
	}
	
	/**
	 * The method will obtain names depending on group option.
	 * It return names in ArrayList<String>.
	 * @param group
	 * @return name
	 */
	public static ArrayList<String> findNames(String group) {

		ArrayList<String> name = new ArrayList<String>();
		Connection con = DbConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "SELECT Name FROM MyAddress  WHERE GroupJia = '" + group + "'";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				name.add(rs.getString(1));
			}
			rs.close();
			st.close();
			// DbConnection.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}
		return name;
	}
	
	/**
	 * The method is used for searching through the whole database.
	 * 'LIKE' expression in SQL is suit for the purpose of searching through address book.
	 * @param info
	 * @return name
	 */
	public static ArrayList<String> searchNames(String info) {

		ArrayList<String> name = new ArrayList<String>();
		Connection con = DbConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "SELECT Name FROM MyAddress  WHERE name LIKE  '%" + info + "%'   OR "
				+ "Sex LIKE  '%" + info + "%'  OR "
				
				
					//	+ "Birthday LIKE  '%" + info + "%'   OR "
								+ "Mobile LIKE  '%" + info + "%'   OR "
										+ "Tele LIKE  '%" + info + "%'   OR "
												+ "Face_QQ LIKE  '%" + info + "%'   OR "
														+ "Home_Add LIKE  '%" + info + "%'   OR "
																+ "Com_Add LIKE  '%" + info + "%'   OR "
																		+ "Email LIKE  '%" + info + "%'   OR "
																	//			+ "Groupjia LIKE  '%" + info + "%'   OR "
																						+ "Note LIKE  '%" + info + "%'  ";
				
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				name.add(rs.getString(1));
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}
		return name;
	}

	/**
	 * The method is used to view details of specific person.
	 * It receives name and obtain the person's info.
	 * It is applied in EditFrame.
	 * @param name
	 * @return person
	 */
	public static ArrayList<String> onePerson(String name) {

		ArrayList<String> person = new ArrayList<String>();
		Connection con = DbConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MyAddress WHERE Name = '" + name + "'";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				for (int i = 0; i < 11; i++)
					person.add(rs.getString(i + 1));
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}
		return person;
	}

	/**
	 * The method will get the byte array of the image of a specific person.
	 * It returns the byte array of the image.
	 * @param name
	 * @return photo
	 */
	public static byte[] photo_byte(String name) {

		byte[] photo = null;
		Connection con = DbConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "SELECT photo FROM MyAddress WHERE Name = '"  + name + "'";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				photo = rs.getBytes(1);
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}
		return photo;
	}

	/**
	 * The method is to delete info of image of one person in database.
	 * @param name
	 */
	public static void deleteImage(String name) {

		Connection con = DbConnection.getConnection();
		Statement st = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;
		byte[] image = null;

		String sql = "UPDATE MyAddress SET photo = ? WHERE Name = '"  + name + "'";

		try {
			psInsert = con.prepareStatement(sql);
			psInsert.setBytes(1, image);
			psInsert.execute();
			psInsert.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}

	}

	/**
	 * The method is to get all info in the database.
	 * It is utilized to generate xml or json file.
	 * @return people
	 */
	public static ArrayList<Person> getAll() {

		ArrayList<Person> people = new ArrayList<Person>();
		Connection con = DbConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			String query = "SELECT * FROM MyAddress";
			rs = st.executeQuery(query);
			while (rs.next()) {
				Person person = new Person();
				person.name = rs.getString(1);
				person.sex = rs.getString(2);
				person.birthday = rs.getString(3);
				person.mobile = rs.getString(4);
				person.tele = rs.getString(5);
				person.face_qq = rs.getString(6);
				person.home_add = rs.getString(7);
				person.com_add = rs.getString(8);
				person.email = rs.getString(9);
				person.group = rs.getString(10);
				person.note = rs.getString(11);
				people.add(person);

			}
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(con);
		}
		return people;
	}

	/**
	 * The method uses xstream to generate the string in xml form of all info stored in database. 
	 * @return xml
	 */
	public static String toXML() {
		String xml;		
		ArrayList<Person> contracts = getAll();
		XStream xstream = new XStream();
		xstream.alias("person", Person.class);
	        xml =  xstream.toXML(contracts);  
	        System.out.println (xml);
		return xml;
	}
	
	/**
	 * The method is used to JUnit test.
	 * @param input
	 * @return xml
	 */
	public static String toXmlTest ( String input) {
		String xml;		
		XStream xstream = new XStream();
	        xml =  xstream.toXML(input);  
		System.out.println (xml);
		return xml;
	} 
	
	/**
	 * The method ses xstream to generate the string in json form of all info stored in database. 
	 * @return json
	 */
	public static String toJSON() {
		ArrayList<Person> contracts = getAll();
		String json;
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
	        xstream.setMode(XStream.NO_REFERENCES);
	        xstream.alias("person", Person.class);
	        json = xstream.toXML(contracts);
	        System.out.println(json);
		return json;
	}
	
	/**
	 *  The method is used to JUnit test.
	 * @param input
	 * @return json
	 */
	public static String toJsonTest( String input ) {
		
		String json;
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
	        xstream.setMode(XStream.NO_REFERENCES);
	        json = xstream.toXML(input);
	        System.out.println(json);
		return json;
	}
	
	/**
	 * The method is to generate a string which contains all info of database.
	 * @return txt
	 */
	public static String toTXT() {
		ArrayList<Person> contracts = getAll();
		String txt = "";
		for (int i = 0; i < contracts.size(); i++) {
			txt = txt + contracts.get(i).toString();
		}
		System.out.println(txt);
		return txt;
	}

}
