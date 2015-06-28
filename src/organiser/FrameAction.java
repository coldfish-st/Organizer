package organiser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class implement the function of all menu items in MyAddress.
 * @author Zhenge Jia 2015
 *
 */
public class FrameAction implements ActionListener{

	static final String FILENAME = "format.txt";

	/**
	 * Override actionPerformed to fit all menu items and implement the function separately.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String selection = ((JMenuItem) (e.getSource())).getText();

		if ( selection.equals("Exit")){
			System.exit(0);
		}

		// Send the Exporting work to ExportFrame. 
		if ( selection.equals("Export")){
			new ExportFrame();	
		}



		if ( selection.equals("Demonstration All")){
			new DemonstrationFrame();
		}

		// Enable user to change skin of the application among swing, nimbus and metal.
		// Besides that, the app will store the preference of users in  a .txt file.
		if ( selection.equals("Nimbus")){

			// Generate a file to store the preferences for nimbus.
			File f_txt =new File(FILENAME);
			try {
				FileWriter out_txt = new FileWriter(f_txt);
				out_txt.write("1");
				out_txt.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			MyAddress.myFrame.setVisible(false);

			// Change the skin to nimbus.
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
			new MyAddress();
		}

		// Besides that, the app will store the preference of users in  a .txt file.
		// Enable user to change to skin of Swing.
		if ( selection.equals("Swing")){

			File f_txt =new File(FILENAME);
			try {
				FileWriter out_txt = new FileWriter(f_txt);
				out_txt.write("0");
				out_txt.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

			MyAddress.myFrame.setVisible(false);

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

			new MyAddress();
		}

		
		// Enable user to change to skin of Metal.
		if ( selection.equals("Metal")){

			File f_txt =new File(FILENAME);
			try {
				FileWriter out_txt = new FileWriter(f_txt);
				out_txt.write("2");
				out_txt.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

			MyAddress.myFrame.setVisible(false);

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

			new MyAddress();
		}

		if ( selection.equals("Calendar")) {	
			new CalendarFrame();
		
		}


	}

}
