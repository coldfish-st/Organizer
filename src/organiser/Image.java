package organiser;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image extends JPanel {
	
	/**
	 * 
	 */
        private static final long serialVersionUID = 2L;
	JLabel label = null;
	ImageIcon img = null;
	
	public Image(){
		img = new ImageIcon("./Images/Bullets.jpg");
		label = new JLabel(img);
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER);
	}

}
