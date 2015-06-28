package organiser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.DBCreate;
import dao.DbConnection;

/**
 * The class contains the main method. 
 * It enables the app to store preferences of skin.
 * @author Zhenge Jia 2015
 *
 */
public class MyAddress implements Runnable {

	static JFrame myFrame;
	static final String FILENAME = "format.txt";


	public MyAddress() {

		// Test whether the stored preferences file exists or not.  
		String content = null;
		File file = new File(FILENAME); 
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(file);
				char[] chars = new char[(int) file.length()];
				reader.read(chars);
				content = new String(chars);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		// If there is no file exists, then it will generate a file which contain "1" representing skin of nimbus.
		} else {
			try {
				FileWriter out_txt = new FileWriter(file);
				out_txt.write("1");
				out_txt.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			try {
				FileReader reader = new FileReader(file);
				char[] chars = new char[(int) file.length()];
				reader.read(chars);
				content = new String(chars);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Read preferences flag from stored file.
		if (content.equals("1")) {
			try {
				try {

					UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					
					e1.printStackTrace();
				}
			} catch (UnsupportedLookAndFeelException e1) {
				
				e1.printStackTrace();
			}
		} else if (content.equals("2")) {
			try {
				try {

					UIManager.setLookAndFeel( "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					
					e1.printStackTrace();
				}
			} catch (UnsupportedLookAndFeelException e1) {
				
				e1.printStackTrace();
			}
		} else if (content.equals("0")){
			try {
				try {
					
					UIManager.setLookAndFeel( "javax.swing.plaf.metal.MetalLookAndFeel");			
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					
					e1.printStackTrace();
				}
			} catch (UnsupportedLookAndFeelException e1) {
				
				e1.printStackTrace();
			}
		}

		SwingUtilities.invokeLater(this);	
	}

	public static void main(String args[]){
		Connection con = DbConnection.getConnection();
		System.out.println(con);

		/*
		 * Test whether connection of database exists. 
		 *  If not, the database will be created.
		 */
		if (con == null){
			DBCreate.getCreate();
			new MyAddress();
		}else {
			new MyAddress();
		}		
		
	}



	@Override
	public void run() {

		myFrame = new JFrame("Personal Organiser");
		myFrame.setSize(420, 510);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth=screenSize.width;
		int srceenHeight=screenSize.height;
		
		myFrame.setLocation((screenWidth- 420)/2,(srceenHeight-500)/2); // set the location of the origin
		myFrame.setResizable(false); //size cannot be changed 


		// main menu code below
		JMenuBar mbr = new JMenuBar();

		JMenu menu1 = new JMenu("File");
		JMenu menu2 = new JMenu("Edit");
		JMenu menu3 = new JMenu("Preferences");
		JMenu menu4 = new JMenu("Window");

		JMenuItem file1 = new JMenuItem("Export");
		JMenuItem file2 = new JMenuItem("Exit");
		file1.addActionListener(new FrameAction());
		file2.addActionListener(new FrameAction());
		menu1.add(file1);
		menu1.add(file2);


		
		JMenuItem edit5 = new JMenuItem("Demonstration All");
		
		edit5.addActionListener(new FrameAction());
		
		menu2.add(edit5);

		JMenuItem help1 = new JMenuItem("Swing");
		JMenuItem help2 = new JMenuItem("Metal");
		JMenuItem help3 = new JMenuItem("Nimbus");
		help1.addActionListener(new FrameAction());
		help2.addActionListener(new FrameAction());
		help3.addActionListener(new FrameAction());
		menu3.add(help1);
		menu3.add(help2);
		menu3.add(help3);

		JMenuItem window1 = new JMenuItem("Calendar");
		window1.addActionListener(new FrameAction());
		menu4.add(window1);

		mbr.add(menu1);
		mbr.add(menu2);
		mbr.add(menu3);
		mbr.add(menu4);

		

		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});



		myFrame.setJMenuBar(mbr);
		myFrame.setVisible(true);
		myFrame.getContentPane().setBackground(Color.white);
		myFrame.getContentPane().add(new MainFrame());
		


	}

}
