package organiser;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.junit.Test;

/**
 * The class is created to test GUI for AddFrame.
 * @author Zhenge Jia 2015
 *
 */
public class TestAddFrame implements Runnable{
	
	AddFrame gui;
	
	/**
	 * Test whether JTextFile will get the input correctly.
	 */
	@Test
	public void addPersonTest () {
		
		gui = new AddFrame();
		
		try {
			
			SwingUtilities.invokeAndWait(this);
			Thread.sleep(1000);
			
			String expected ;
			expected = "Kevin";
			assertEquals(gui.name.getText(), expected);
			
			
		} catch (InvocationTargetException e) {
			System.out.println(e.getStackTrace());
			System.out.println(gui.name.getText());
			fail();
		} catch (InterruptedException e) {
			 fail();	
		}
	}

	@SuppressWarnings("static-access")
        @Override
        public void run() {		
		
		gui.name.setText("Kevin");
	        
        }

}
