package organiser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Build up a JDialog which contains the name of searching result.
 * @author Zhenge Jia 2015
 *
 */
public class SearchFrame extends JDialog {
	
	/**
	 * 
	 */
        private static final long serialVersionUID = 1L;
	JDialog seFrame;
	
	/**
	 * Enable users to search information through whole database.
	 * @param info
	 */
	public SearchFrame(String info) {
		
		seFrame = new JDialog(this,"Todo List", true);
		seFrame.setLocationRelativeTo(MyAddress.myFrame);
		seFrame.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();  
		// set the border to be white.
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		final JList<String> list = new JList<String>();
		list.setFont(new Font("Dialog", 0, 20));
		DefaultListModel<String> defaultListModel = new DefaultListModel<String>();

		// Use SQL query method implemented in SimpleQuery class to get the list of names for searching result.
		ArrayList<String> names = new ArrayList<String>();
		names = SimpleQuery.searchNames(info);
		
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

				// Enbale users to edit the contract after searching.
				new EditFrame(list.getSelectedValue());

			}

		});
		
		
		scrollPane.setViewportView(list); 
		seFrame.add(scrollPane);
		seFrame.setSize(420, 500);
		seFrame.setVisible(true);
		
		
		
	
	}
	
	// This method add the letter block for various names and divide them into the group that is with the same start letter.
	@SuppressWarnings("rawtypes")
	private class MyRender  extends JLabel implements ListCellRenderer {

		MyRender() {
			// Make it opaque.
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
