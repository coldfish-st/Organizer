package organiser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.DbConnection;

/**
 * This class provides methods for operations on AddFrame and EditFrame.
 * It provides buttons the function that they could insert or update the data in database.
 * @author Zhenge Jia 2015
 *
 */

public class AddEditFrameAction implements ActionListener {

	@SuppressWarnings({ "resource" })
	@Override
	/**
	 * The method is the implementation of ActionListener. It will run various operations depending on the input from AddFrame or EditFrame.
	 */
	public void actionPerformed(ActionEvent e) {
		String selection = ((JButton) (e.getSource())).getText();  
		Connection con = DbConnection.getConnection();

		PreparedStatement psInsert=null;
		String sql;
		String sql_photo;
		
		// It is the case for button "save" in AddFrame.
		if (selection.equals("Save") ) {
			String name, sex, mobile, tele, face_qq, home_add, com_add, email, group, note;
			String birthday;
			String year, month, day;
			byte[] imageData;

			name = AddFrame.name.getText();
			sex = (String) AddFrame.sex.getSelectedItem();
			mobile = AddFrame.mobilePhone.getText();
			tele = AddFrame.telePhone.getText();
			face_qq = AddFrame.face_QQ.getText();
			home_add = AddFrame.add_home.getText();
			com_add = AddFrame.add_company.getText();
			email = AddFrame.email.getText();
			group = (String) AddFrame.group.getSelectedItem();
			note = AddFrame.note.getText();
			year = String.valueOf(AddFrame.birthYear.getSelectedItem()) ;
			month = String.valueOf(AddFrame.birthMonth.getSelectedItem());
			day = String.valueOf(AddFrame.birthDay.getSelectedItem());
			birthday = year + "-" + month + "-" + day;
			imageData = AddFrame.imageData;		

			
			//  Consider the case that if name = “” .
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "Name cannot be null");
			}else {
				try {
					sql_photo = "INSERT INTO MyAddress VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					sql = "INSERT INTO MyAddress(Name, Sex, Birthday, Mobile,Tele, Face_QQ, Home_Add,Com_Add ,Email,GroupJia,Note) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
					// Divide into two cases whether image is null or not. 
					if (imageData == null) {
						psInsert = con.prepareStatement(sql);
					} else {
						psInsert = con.prepareStatement(sql_photo);
						psInsert.setBytes(12, imageData);

					}

					psInsert.setString(1, name);
					psInsert.setString(2, sex);
					psInsert.setString(3, birthday);
					psInsert.setString(4, mobile);
					psInsert.setString(5, tele);
					psInsert.setString(6, face_qq);
					psInsert.setString(7, home_add);
					psInsert.setString(8, com_add);
					psInsert.setString(9, email);
					psInsert.setString(10, group);
					psInsert.setString(11, note);

					
					JOptionPane.showMessageDialog(null, "Added successfully!");
					psInsert.execute();
					psInsert.close();
					con.close();


				} catch (SQLException e1) {
					System.out.printf("Exception: "+ e1.getMessage());
					e1.printStackTrace();
				}
				MyAddress.myFrame.setVisible(false);
				MyAddress.myFrame.getContentPane().removeAll();		
				MyAddress.myFrame.setSize(420, 500);
				MyAddress.myFrame.getContentPane().add(new MainFrame());
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				int screenWidth=screenSize.width;
				int srceenHeight=screenSize.height;
				//Set the windows to be center of the screen.
				MyAddress.myFrame.setLocation((screenWidth- 420)/2,(srceenHeight-500)/2); 
				MyAddress.myFrame.setVisible(true);
			}

		}
		if (selection.equals("Give up")) {
			MyAddress.myFrame.setVisible(false);
			MyAddress.myFrame.getContentPane().removeAll();
			MyAddress.myFrame.setSize(420, 500);
			MyAddress.myFrame.getContentPane().add(new MainFrame());
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth=screenSize.width;
			int srceenHeight=screenSize.height;
			//Set the windows to be center of the screen.
			MyAddress.myFrame.setLocation((screenWidth- 420)/2,(srceenHeight-500)/2); 
			MyAddress.myFrame.setVisible(true);
		}
		
		// It is the case for button "update" in EditFrame. 
		if (selection.equals("Update")) {
			String name, sex, mobile, tele, face_qq, home_add, com_add, email, group, note;
			String birthday;
			String year, month, day;
			byte[] imageData;

			name = EditFrame.name.getText();
			sex = (String) EditFrame.sex.getSelectedItem();
			mobile = EditFrame.mobilePhone.getText();
			tele = EditFrame.telePhone.getText();
			face_qq = EditFrame.face_QQ.getText();
			home_add = EditFrame.add_home.getText();
			com_add = EditFrame.add_company.getText();
			email = EditFrame.email.getText();
			group = (String) EditFrame.group.getSelectedItem();
			note = EditFrame.note.getText();
			year = String.valueOf(EditFrame.birthYear.getSelectedItem()) ;
			month = String.valueOf(EditFrame.birthMonth.getSelectedItem());
			day = String.valueOf(EditFrame.birthDay.getSelectedItem());
			birthday = year + "-" + month + "-0" + day;
			imageData = EditFrame.imageData;
			
			// Consider the case that name is null.
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "Name cannot be null");
			}else {
				// Divide into 2 cases. (image = null or image != null)
				try {
					sql_photo = "UPDATE MyAddress SET Name=?, Sex=?, Birthday=?, Mobile=?,Tele=?,"
							+ " Face_QQ=?, Home_Add=?,Com_Add=? ,Email=?,GroupJia=?,Note=?, photo=? WHERE Name = '" + EditFrame.select+"'";
					sql = "UPDATE MyAddress SET Name=?, Sex=?, Birthday=?, Mobile=?,Tele=?, "
							+ "Face_QQ=?, Home_Add=?,Com_Add=? ,Email=?,GroupJia=?,Note=? WHERE Name = '" + EditFrame.select+"'";
					if (imageData == null) {
						psInsert = con.prepareStatement(sql);
					} else {
						psInsert = con.prepareStatement(sql_photo);
						psInsert.setBytes(12, imageData);

					}

					psInsert.setString(1, name);
					psInsert.setString(2, sex);
					psInsert.setString(3, birthday);
					psInsert.setString(4, mobile);
					psInsert.setString(5, tele);
					psInsert.setString(6, face_qq);
					psInsert.setString(7, home_add);
					psInsert.setString(8, com_add);
					psInsert.setString(9, email);
					psInsert.setString(10, group);
					psInsert.setString(11, note);

					JOptionPane.showMessageDialog(null, "Updated successfully!");


					psInsert.execute();
					psInsert.close();
					con.close();


				} catch (SQLException e1) {
					System.out.printf("Exception: "+ e1.getMessage());
					e1.printStackTrace();
				}
				EditFrame.editFrame.setVisible(false);
				MyAddress.myFrame.setVisible(false);
				MyAddress.myFrame.getContentPane().removeAll();		
				MyAddress.myFrame.setSize(420, 500);
				MyAddress.myFrame.getContentPane().add(new MainFrame());
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				int screenWidth=screenSize.width;
				int srceenHeight=screenSize.height;
				//Set the frame to be in the center of screen.
				MyAddress.myFrame.setLocation((screenWidth- 420)/2,(srceenHeight-500)/2); 
				MyAddress.myFrame.setVisible(true);
			}

		}

		// Operate the delete function on database when pressing "delete" button in EditFrame.
		if (selection.equals("Delete")) {	
			try {
				sql = "DELETE FROM MyAddress WHERE Name = '" + EditFrame.select+"'";
				psInsert = con.prepareStatement(sql);
				
				//JOptionPane.showConfirmDialog(null, "Confirm?");
				JOptionPane.showMessageDialog(null, "Delete successfully!");


				psInsert.execute();
				psInsert.close();
				con.close();


			} catch (SQLException e1) {
				System.out.printf("Exception: "+ e1.getMessage());
				e1.printStackTrace();
			}
			EditFrame.editFrame.setVisible(false);
			MyAddress.myFrame.setVisible(false);
			MyAddress.myFrame.getContentPane().removeAll();		
			MyAddress.myFrame.setSize(420, 500);
			MyAddress.myFrame.getContentPane().add(new MainFrame());
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth=screenSize.width;
			int srceenHeight=screenSize.height;
			MyAddress.myFrame.setLocation((screenWidth- 420)/2,(srceenHeight-500)/2); 
			MyAddress.myFrame.setVisible(true);

		}
		
		if (selection.equals("Exit")) {
			EditFrame.editFrame.setVisible(false);
			MyAddress.myFrame.setVisible(false);
			MyAddress.myFrame.getContentPane().removeAll();
			MyAddress.myFrame.setSize(420, 500);
			MyAddress.myFrame.getContentPane().add(new MainFrame());
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth=screenSize.width;
			int srceenHeight=screenSize.height;
			MyAddress.myFrame.setLocation((screenWidth- 420)/2,(srceenHeight-500)/2); 
			MyAddress.myFrame.setVisible(true);
		}
	}



}
