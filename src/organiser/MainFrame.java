package organiser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The MainFrame will show names of contracts depending on the group option.
 * It enables users to click any name to view one person's info and edit it.
 * @author Zhenge Jia 2015
 *
 */
public class MainFrame extends JPanel {


	private static final long serialVersionUID = 1L;
	static JComboBox<String> group;
	static final String FILENAME = "group.txt";
	ArrayList<String> names;

	public MainFrame() {

		this.setLayout(new FlowLayout());
		this.setSize(425, 500);
  
		JScrollPane scrollPane = new JScrollPane();  
		// set the border to be white.
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		final JList<String> list = new JList<String>();
		list.setFont(new Font("Dialog", 0, 20));
		DefaultListModel<String> defaultListModel = new DefaultListModel<String>();

		names = new ArrayList<String>();

		String content = null;
		File file = new File(FILENAME);
		// It enables the app store preferences of viewing different groups.
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//System.out.println(content);		

		group = new JComboBox<String>();
		group.setBackground(Color.white);
		group.addItem("Group");
		group.addItem("All");
		group.addItem("None");
		group.addItem("Relatives");
		group.addItem("Schoolmates");
		group.addItem("Colleague");
		group.addItem("Friends");
		group.addItem("Lover");
		group.setPreferredSize(new Dimension(30, 10));
		// Show various group of names.
		if (content.equals("1")) {
			names = SimpleQuery.findAllNames();
			group.setSelectedIndex(1);
		} else {
			int x = Integer.parseInt(content);
			names =  SimpleQuery.findNames((String) group.getItemAt(x));
			group.setSelectedIndex(x);
		}
		
		// Generate the file and store flag to show preferences of users.
		group.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (((String) group.getSelectedItem()).equals("All")) {
					
					File f_txt =new File(FILENAME);
					try {
						FileWriter out_txt = new FileWriter(f_txt);
						out_txt.write("1");
						out_txt.close();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				} else {
					
					if (((String) group.getSelectedItem()).equals("None")){
						File f_txt =new File(FILENAME);
						try {
							FileWriter out_txt = new FileWriter(f_txt);
							out_txt.write("2");
							out_txt.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					} else if (((String) group.getSelectedItem()).equals("Relatives")){
						File f_txt =new File(FILENAME);
						try {
							FileWriter out_txt = new FileWriter(f_txt);
							out_txt.write("3");
							out_txt.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					} else  if (((String) group.getSelectedItem()).equals("Schoolmates")){
						File f_txt =new File(FILENAME);
						try {
							FileWriter out_txt = new FileWriter(f_txt);
							out_txt.write("4");
							out_txt.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					} else  if (((String) group.getSelectedItem()).equals("Colleague")){
						File f_txt =new File(FILENAME);
						try {
							FileWriter out_txt = new FileWriter(f_txt);
							out_txt.write("5");
							out_txt.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					} else  if (((String) group.getSelectedItem()).equals("Friends")){
						File f_txt =new File(FILENAME);
						try {
							FileWriter out_txt = new FileWriter(f_txt);
							out_txt.write("6");
							out_txt.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					} else  if (((String) group.getSelectedItem()).equals("Lover")){
						File f_txt =new File(FILENAME);
						try {
							FileWriter out_txt = new FileWriter(f_txt);
							out_txt.write("7");
							out_txt.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}
				}

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

		});


		// Sort names in alpha order.
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {              
				return o1.compareToIgnoreCase(o2);
			}
		});

		String temp = "";

		for(String name : names){
			String first = name.substring(0, 1).toUpperCase();

			if (first.equals(temp) == false) {
				defaultListModel.addElement(first.toUpperCase());

			}
			temp = first;

			defaultListModel.addElement(name);

		}

		list.setModel(defaultListModel);

		list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);  
		list.setCellRenderer(new MyRender());

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				// Enable user to edit person info.
				new EditFrame(list.getSelectedValue());

			}

		});


		scrollPane.setViewportView(list); 
		scrollPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0)); 

		
		final JLabel add = new JLabel("+");
		add.setFont(new Font("Dialog", 0, 35));
		add.setBackground(Color.white);
		// Enable users to add a person record.
		add.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					
					MyAddress.myFrame.setVisible(false);
					MyAddress.myFrame.getContentPane().removeAll();
					MyAddress.myFrame.getContentPane().add( new AddFrame());
					MyAddress.myFrame.pack();
					MyAddress.myFrame.setVisible(true);
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

			@Override
			public void mouseEntered(MouseEvent e) {
				add.setCursor(new Cursor(Cursor.HAND_CURSOR));
				add.setForeground(Color.blue);
				add.setToolTipText("Click Me!!! to add a person info :D");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				add.setCursor(new Cursor(Cursor.HAND_CURSOR));
				add.setForeground(Color.black);
			}

		});

		add.setPreferredSize(add.getMinimumSize());

		JButton search = new JButton("Search");
		search.setPreferredSize(search.getMinimumSize());		

		final JTextField search_txt = new JTextField(30);

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String info = search_txt.getText();
				new SearchFrame(info);

			}

		});

		Box p1 = Box.createHorizontalBox();
		p1.setSize(getMinimumSize());
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));

		p1.add(group);
		p1.add(Box.createHorizontalStrut(265));

		p1.add(add);
		p2.add(search_txt);
		p2.add(search);

		p1.setPreferredSize(p1.getMinimumSize());
		scrollPane.setPreferredSize(new Dimension(420, 360));

		this.add(p1);
		this.add(p2);
		this.add(scrollPane);



		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		scrollPane.setBackground(Color.white);
		this.setBackground(Color.white);

	}

	/**
	 * The class is to create gray block for letters and make names into groups by the first letter.
	 * @author Zhenge Jia 2015
	 *
	 */
	@SuppressWarnings("rawtypes")
	public class MyRender  extends JLabel implements ListCellRenderer {

		MyRender() {
			this.setOpaque(true);


		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());

			Color background = null;
			Color foreground = null;

			if (value.toString().equals("A") ||
					value.toString().equals("B")  ||
					value.toString().equals("C")  ||
					value.toString().equals("D")  ||
					value.toString().equals("E")  ||
					value.toString().equals("F")  ||
					value.toString().equals("G")  ||
					value.toString().equals("H")  ||
					value.toString().equals("I")  ||
					value.toString().equals("J")  ||
					value.toString().equals("K")  ||
					value.toString().equals("L")  ||
					value.toString().equals("M")  ||
					value.toString().equals("N")  ||
					value.toString().equals("O")  ||
					value.toString().equals("P")  ||
					value.toString().equals("Q")  ||
					value.toString().equals("R")  ||
					value.toString().equals("S")  ||
					value.toString().equals("T")  ||
					value.toString().equals("U")  ||
					value.toString().equals("V")  ||
					value.toString().equals("W")  ||
					value.toString().equals("X")  ||
					value.toString().equals("Y")  ||
					value.toString().equals("Z") ) {
				background = Color.LIGHT_GRAY;
				setFont(new Font("Dialog", 1, 15));
				this.setEnabled(false);
			} else {

				setFont(new Font("Times", 2, 20));
				setEnabled(true);
				this.setBorder(BorderFactory.createRaisedBevelBorder());
				background = Color.white;
			}

			setBackground(background);
			setForeground(foreground);

			return this;
		}

	}

}
