package organiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Extend a JDialog which enables users to edit any contract info by clicking from JList. 
 * @author Zhenge Jia 2015
 *
 */

public class EditFrame extends JDialog{

	
	private static final long serialVersionUID = 1L;
	static JDialog editFrame;
	static JTextField name, mobilePhone, telePhone, face_QQ, add_company, add_home, email ;
	static JTextArea note;
	static JComboBox<String> sex, group; 
	static JComboBox<Integer>birthYear, birthMonth, birthDay;
	static JButton update, exit, delete;
	
	static byte[] imageData;
	static String  select;
	
	/**
	 * It receive the selected name from JList in MainFrame and
	 * enable users to view info of the selected person and edit it.
	 * @param select_name
	 */
	
	public EditFrame(final String select_name){
		
		select = select_name;
		editFrame = new JDialog(MyAddress.myFrame, "Edit", true);
		editFrame.setLayout(new GridLayout(2,0));
		editFrame.setResizable(false);
		editFrame.setSize(new Dimension(570, 600));

		// Get all info from database.
		ArrayList<String> person = SimpleQuery.onePerson(select_name);
		byte[] saved = SimpleQuery.photo_byte(select_name);

		sex = new JComboBox<String>();
		sex.addItem(person.get(1));
		sex.addItem("Male");
		sex.addItem("Female");
		sex.addItem("X");


		group = new JComboBox<String>();
		group.addItem(person.get(9));
		group.addItem("None");
		group.addItem("Relatives");
		group.addItem("Schoolmates");
		group.addItem("Colleague");
		group.addItem("Friends");
		group.addItem("Lover");


		birthYear = new JComboBox<Integer>();
		String year = person.get(2).substring(0, 4);
		birthYear.addItem(Integer.parseInt(year));
		for(int i = 0; i < 200; i++){
			birthYear.addItem(2015-i);
		}
		birthYear.addItem(0);


		birthMonth = new JComboBox<Integer>();
		String month = person.get(2).substring(5, 7);
		birthMonth.addItem(Integer.parseInt(month));
		for(int i = 0; i < 12; i++){
			birthMonth.addItem(1+i);
		}
		birthMonth.addItem(0);


		birthDay = new JComboBox<Integer>();
		String day = person.get(2).substring(8);
		birthDay.addItem(Integer.parseInt(day));
		for(int i = 0; i < 31; i++){
			birthDay.addItem(1+i);
		}
		birthDay.addItem(0);


		name = new JTextField(20);
		name.setText(person.get(0));
		mobilePhone = new JTextField(20);
		mobilePhone.setText(person.get(3));
		telePhone = new JTextField(20);
		telePhone.setText(person.get(4));
		face_QQ = new JTextField(36);
		face_QQ.setText(person.get(5));
		add_company = new JTextField(36);
		add_company.setText(person.get(6));
		add_home = new JTextField(36);
		add_home.setText(person.get(7));
		email = new JTextField(36);
		email.setText(person.get(8));
		note = new JTextArea(5, 40);
		note.setText(person.get(10)); 

		JLabel labelName = new JLabel("Name:            ");//17
		JLabel labelBirth = new JLabel("Birthday: ");
		JLabel labelSex = new JLabel("Gender: ");
		JLabel labelAdd_home = new JLabel("Home address:      ");
		JLabel labelAdd_company = new JLabel("Company address: ");
		JLabel labelMobile = new JLabel("Mobilephone: ");
		JLabel labelTele = new JLabel("Telephone:     ");//15
		JLabel labelFace_QQ = new JLabel("MSN/QQ:                 ");
		JLabel labelEmail = new JLabel("Email:                     ");
		JLabel labelGroup = new JLabel("Group: ");
		

		update = new JButton("Update");
		delete= new JButton("Delete");
		exit= new JButton("Exit");

		// Implement the function of three buttons in AddEditFrameAction.
		// They are the connection methods. 
		update.addActionListener(new AddEditFrameAction());
		delete.addActionListener(new AddEditFrameAction());
		exit.addActionListener(new AddEditFrameAction());

		JPanel panelToOne = new JPanel();
		JPanel panelToTwo = new JPanel();
		JPanel panelToThree = new JPanel();
		JPanel panelToFour = new JPanel();
		JPanel oneLine = new JPanel();
		JPanel twoLine = new JPanel();
		JPanel threeLine = new JPanel();
		JPanel fourLine = new JPanel();
		JPanel fiveLine = new JPanel();
		JPanel sixLine = new JPanel();
		JPanel sevenLine = new JPanel();



		panelToOne.setLayout(new GridLayout(2,0));

		oneLine.setLayout(new FlowLayout(FlowLayout.CENTER));
		oneLine.add(update);
		oneLine.add(new JLabel("                                "));
		oneLine.add(delete); 
		oneLine.add(new JLabel("                                "));
		oneLine.add(exit);
		panelToOne.add(oneLine);
		JPanel foradd = new JPanel();
		foradd.setLayout(new FlowLayout(FlowLayout.CENTER) );
		JLabel add = new JLabel("Edit a Person");
		add.setFont(new Font("Dialog", 0, 20));
		foradd.add(add);
		panelToOne.add(foradd);

		panelToTwo.setLayout(new FlowLayout(FlowLayout.CENTER));

		twoLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel toTwo1 = new JPanel();
		JPanel toTwo2 = new JPanel();

		 final JLabel image = new JLabel("");
		image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		image.setPreferredSize(new Dimension(100, 100));
		
		// Set the picture got from database on label.
		if (saved != null) {
			ImageIcon imageIcon = new ImageIcon(saved);
			Image photo = imageIcon.getImage();
			int height = photo.getHeight(image);
			int weight = photo.getWidth(image);
			if (height > 100) {
				photo = photo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			} else if (weight > 100) {
				photo = photo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			}
			image.setIcon(new ImageIcon(photo));
		}


		// Enable user to add/replacing or delete image.
		image.addMouseListener(new MouseListener(){
			File file = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){

					Object[] options = {"Exit","Delete","Add"};
					// An OptionDialog provides users with three options: add, exit or delete.
					int response = JOptionPane.showOptionDialog ( null, " Add a Image?","Edit the Image",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE, null, options, options[0] ) ;
					
					// Implement the function for adding/replacing. 
					if (response == 2) {
						JFileChooser chooser = new JFileChooser();
						FileFilter filefiter = new FileNameExtensionFilter("jpg, png, gif image", "gif", "png", "jpg");
						chooser.setFileFilter(filefiter);
						if(JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(EditFrame.this)){
							file =chooser.getSelectedFile();
							FileInputStream inputStream = null;
							try {
								inputStream = new FileInputStream(file);
							} catch (FileNotFoundException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}                
							
							byte[] b = null;
							try {
								b = new byte[inputStream.available()];

							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							try {
								inputStream.read(b);
								inputStream.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							ImageIcon icon = new ImageIcon(b);
							Image photo = icon.getImage(); 
							int height = photo.getHeight(image);
							int weight = photo.getWidth(image);
							// Scale the size of image.
							if (height > 90) {
								photo = photo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
							} else if (weight > 90) {
								photo = photo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
							}
							

							image.setIcon(new ImageIcon(photo));
							// imageData is used for storing byte array of  image.
							imageData = b;
						}
					
					//  Delete the image.
					} else if (response == 1) {
						
						image.removeAll();
						SimpleQuery.deleteImage(select_name);
						editFrame.setVisible(false);
						new EditFrame(select_name);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			// Provide users a tip and a remainder.
			@Override
			public void mouseEntered(MouseEvent e) {
				image.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				image.setToolTipText("Double click to upload or delete an image");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				image.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			}



		});
		toTwo1.add(image);
		toTwo2.setLayout(new GridLayout(3, 0));
		JPanel n1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel n2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel n3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n1.add(labelName);
		n1.add(name);
		n2.add(labelMobile);
		n2.add(mobilePhone);
		n3.add(labelTele);
		n3.add(telePhone);
		toTwo2.add(n1);
		toTwo2.add(n2);
		toTwo2.add(n3);
		twoLine.add(toTwo1);
		twoLine.add(toTwo2);
		panelToTwo.add(twoLine);

		panelToThree.setLayout(new GridLayout(5, 0));

		threeLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		threeLine.add(labelSex);
		threeLine.add(sex);
		threeLine.add(labelBirth);
		threeLine.add(birthDay);
		threeLine.add(birthMonth);
		threeLine.add(birthYear);
		threeLine.add(labelGroup);
		threeLine.add(group);


		fourLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		fourLine.add(labelFace_QQ);
		fourLine.add(face_QQ);



		fiveLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		fiveLine.add(labelEmail);
		fiveLine.add(email);

		sixLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		sixLine.add(labelAdd_company);
		sixLine.add(add_company);

		sevenLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		sevenLine.add(labelAdd_home);
		sevenLine.add(add_home);

		panelToThree.add(threeLine);
		panelToThree.add(fourLine);
		panelToThree.add(fiveLine);
		panelToThree.add(sixLine);
		panelToThree.add(sevenLine);

		panelToFour.add(note);

		JPanel oneAndtwo = new JPanel();
		oneAndtwo.setLayout(new GridLayout(2,0));
		oneAndtwo.add(panelToOne);
		oneAndtwo.add(panelToTwo);
		
		JPanel threeAndfour = new JPanel();
		threeAndfour.setLayout(new GridLayout(2,0));
		threeAndfour.add(panelToThree);
		threeAndfour.add(panelToFour);

		editFrame.add(oneAndtwo);
		editFrame.add(threeAndfour);

		
		editFrame.setMaximumSize(getMaximumSize());
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth=screenSize.width;
		int srceenHeight=screenSize.height;
		
		editFrame.setLocation((screenWidth- 550)/2,(srceenHeight-800)/2); 
		editFrame.setVisible(true);


	}

}
