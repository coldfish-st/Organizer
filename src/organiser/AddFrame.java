package organiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The GUI for AddFrame. Enable users to add a person contract record from main interface.
 * @author Zhenge Jia 2015
 *
 */
public class AddFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	static JTextField name, mobilePhone, telePhone, face_QQ, add_company, add_home, email;
	static JTextArea note;
	static JComboBox<String> sex, group;
	static JComboBox<Integer> birthYear, birthMonth, birthDay;
	static byte[] imageData;
	JButton newAdd, newCancel;

	public AddFrame() {  

		this.setPreferredSize(new Dimension(555, 600));
		this.setLayout(new GridLayout(2, 0));
		this.setBackground(Color.white);

		sex = new JComboBox<String>();
		sex.addItem("Male");
		sex.addItem("Female");
		sex.addItem("X");

		group = new JComboBox<String>();
		group.addItem("None");
		group.addItem("Relatives");
		group.addItem("Schoolmates");
		group.addItem("Colleague");
		group.addItem("Friends");
		group.addItem("Lover");

		birthYear = new JComboBox<Integer>();
		for (int i = 0; i < 200; i++) {
			birthYear.addItem(2015 - i);
		}
		birthYear.addItem(0);

		birthMonth = new JComboBox<Integer>();
		for (int i = 0; i < 12; i++) {
			birthMonth.addItem(1 + i);
		}
		birthMonth.addItem(0);

		birthDay = new JComboBox<Integer>();
		for (int i = 0; i < 31; i++) {
			birthDay.addItem(1 + i);
		}
		birthDay.addItem(0);

		name = new JTextField(20);
		name.setText("");
		mobilePhone = new JTextField(20);
		mobilePhone.setText("");
		telePhone = new JTextField(20);
		telePhone.setText("");
		face_QQ = new JTextField(36);
		face_QQ.setText("");
		add_company = new JTextField(36);
		add_company.setText("");
		add_home = new JTextField(36);
		add_home.setText("");
		email = new JTextField(36);
		email.setText("");
		note = new JTextArea(5, 40);
		note.setText("note");

		JLabel labelName = new JLabel("Name:            ");// 17
		JLabel labelBirth = new JLabel("Birthday: ");
		JLabel labelSex = new JLabel("Gender: ");
		JLabel labelAdd_home = new JLabel("Home address:      ");
		JLabel labelAdd_company = new JLabel("Company address: ");
		JLabel labelMobile = new JLabel("Mobilephone: ");
		JLabel labelTele = new JLabel("Telephone:     ");// 15
		JLabel labelFace_QQ = new JLabel("MSN/QQ:                 ");
		JLabel labelEmail = new JLabel("Email:                     ");
		JLabel labelGroup = new JLabel("Group: ");


		newAdd = new JButton("Save");
		newCancel = new JButton("Give up");

		newAdd.setEnabled(true);
		newCancel.setEnabled(true);
		newAdd.addActionListener(new AddEditFrameAction());
		newCancel.addActionListener(new AddEditFrameAction());

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

		panelToOne.setLayout(new GridLayout(2, 0));

		oneLine.setLayout(new FlowLayout(FlowLayout.CENTER));
		oneLine.add(newAdd);
		oneLine.add(new JLabel("                                                                               "));
		oneLine.add(newCancel);
		panelToOne.add(oneLine);
		JPanel foradd = new JPanel();
		foradd.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel add = new JLabel("Add a Person");
		add.setFont(new Font("Dialog", 0, 20));
		foradd.add(add);
		panelToOne.add(foradd);

		panelToTwo.setLayout(new FlowLayout(FlowLayout.CENTER));

		twoLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel toTwo1 = new JPanel();
		JPanel toTwo2 = new JPanel();

		final JLabel image = new JLabel("");
		// Show the border of the image.
		image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		image.setPreferredSize(new Dimension(90, 90));

		// Add MouseListener to implement adding and deleting image function.
		image.addMouseListener(new MouseListener() {
			File file = null;

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					JFileChooser chooser = new JFileChooser();
					// Only allow to add image in jpg, png and gif form.
					FileFilter filefiter = new FileNameExtensionFilter(
							"jpg, png, gif image",
							"gif", "png", "jpg");
					chooser.setFileFilter(filefiter);
					
					if (JFileChooser.APPROVE_OPTION == chooser	.showOpenDialog(AddFrame.this)) {
						file = chooser.getSelectedFile();
						FileInputStream inputStream = null;
						try {
							
							inputStream = new FileInputStream(file);
						} catch (FileNotFoundException e3) {
							
							e3.printStackTrace();
						}
						// Initialize a byte array to store information of image in binary form which is suit to upload to database.
						byte[] b = null;
						try {
							b = new byte[inputStream.available()];

						} catch (IOException e2) {
							// TODO Auto-generated
							// catch block
							e2.printStackTrace();
						}
						// Write byte array into stream.
						try {
							inputStream.read(b);
							inputStream.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
						// Get the ImageIcon and show it on JLabel.
						ImageIcon icon = new ImageIcon(b);
						Image photo = icon.getImage();
						int height = photo.getHeight(image);
						int weight = photo.getWidth(image);
						
						// Scale the size of image to fit JLabel size.
						if (height > 90) {
							photo = photo.getScaledInstance(90,90,	Image.SCALE_SMOOTH);
						}else  if (weight > 90) {
							photo =	 photo.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
						}

						image.setIcon(new ImageIcon(	photo));

						imageData = b;
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
			
			// Two methods blow will set the border of this JLabel to change from black to blue when mouse enter this area.
			// And will show a tip at the same time.
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
		oneAndtwo.setLayout(new GridLayout(2, 0));
		oneAndtwo.add(panelToOne);
		oneAndtwo.add(panelToTwo);

		JPanel threeAndfour = new JPanel();
		threeAndfour.setLayout(new GridLayout(2, 0));
		threeAndfour.add(panelToThree);
		threeAndfour.add(panelToFour);


		this.add(oneAndtwo);
		this.add(threeAndfour);

		this.setVisible(true);

	}



}
