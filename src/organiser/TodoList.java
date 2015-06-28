package organiser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The class is associated with CalendarFrame.
 * It extends JDialog and enable users to input todo things and generate file to stored it for selected day.  
 * @author Zhenge Jia 2015
 *
 */
public class TodoList extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JDialog todoFrame;

	public TodoList(String day) {

		todoFrame = new JDialog(this,"Todo List", true);
		todoFrame.setLocationRelativeTo(MyAddress.myFrame);
		todoFrame.setLayout(new BorderLayout());

		String content = null;
		// Specify the location of stored files.
		final String fileName = "./todo/"+day+".txt";
		File file = new File(fileName); 

		// If it exists, read file or just generate a file for selected day.
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(file);
				char[] chars = new char[(int) file.length()];
				reader.read(chars);
				content = new String(chars);
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				FileWriter out = new FileWriter(file);
				out.write("");
				out.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}


		final JTextArea todo = new JTextArea(10,40);
		todo.setText(content);
		JLabel note = new JLabel("Add the todo things here for:"+day);
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener(){

			// Enable users to input any todo things and stored it in the file in .txt form.
			@Override
			public void actionPerformed(ActionEvent e) {
				String update = todo.getText();

				File f =new File(fileName);
				try {
					FileWriter out = new FileWriter(f);
					out.write(update);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(todoFrame, "Save successfully");
				todoFrame.setVisible(false);

			}

		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
                        public void actionPerformed(ActionEvent e) {
	                       todoFrame.setVisible(false);
	                        
                        }
			
		});
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		p1.add(note);
		p2.add(todo);
		p3.add(save);
		p3.add(cancel);

		todoFrame.add("North", p1);
		todoFrame.add("Center", p2);
		todoFrame.add("South", p3);

		todoFrame.pack();
		todoFrame.setVisible(true);

	}
}
