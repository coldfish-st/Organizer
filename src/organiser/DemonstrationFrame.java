package organiser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A JDialog contains a JTable to demonstrate info of all contracts.
 * @author Zhenge Jia 2015
 *
 */
public class DemonstrationFrame {

	static JDialog demFrame;
	JButton  demCancel;
	JLabel demLabel;
	JTable t;
	DefaultTableModel  tableModel= null; 

	@SuppressWarnings("unchecked")
	public DemonstrationFrame(){

		demFrame = new JDialog(MyAddress.myFrame, "Demonstration", true);
		demFrame.setLocationRelativeTo(null);
		demFrame.setLayout(new BorderLayout(3,0));

		demLabel = new JLabel("This is the address book");
		demCancel = new JButton("Cancel");
		
		
		demCancel.addActionListener(new ActionListener() {

			@Override
                        public void actionPerformed(ActionEvent e) {
				DemonstrationFrame.demFrame.setVisible(false);
	                        
                        }
			
		});

		ArrayList<Person> scan = new ArrayList<Person>();
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("Sex");
		columnNames.add("Birthday");
		columnNames.add("Mobile");
		columnNames.add("Tele");
		columnNames.add("Face/QQ");
		columnNames.add("Home Add");
		columnNames.add("Com Add");
		columnNames.add("Email");
		columnNames.add("Group");
		columnNames.add("Note");
		
		// Build up a tableModel contains all contracts.

		tableModel = new DefaultTableModel (columnNames, 0);
		
		tableModel.addRow(columnNames);
		
		scan = SimpleQuery.getAll();
		for (int i = 0; i < scan.size(); i++) {
			@SuppressWarnings("rawtypes")
			Vector v = new Vector();
			Person person = (Person) scan.get(i);
			v.add(person.getName());
			v.add(person.getSex());
			v.add(person.getBirth());
			v.add(person.getMobile());
			v.add(person.getTele());
			v.add(person.getFace());
			v.add(person.getHome());
			v.add(person.getCompany());
			v.add(person.getEmail());
			v.add(person.getGroup());
			v.add(person.getNote());
			v.add(person.getSex());
			tableModel.addRow(v);
		}
		
		
		
		t = new JTable (tableModel);
		t.setVisible(true);

		t.setFillsViewportHeight(true);  

		JPanel panelDem1 = new JPanel();
		JPanel panelDem2 = new JPanel();
		JPanel panelDem3 = new JPanel();

		panelDem1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDem1.add(demLabel);

		panelDem2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelDem2.add(t);

		panelDem3.add(demCancel);

		demFrame.add("North", panelDem1);
		demFrame.add("Center", panelDem2);
		demFrame.add("South", panelDem3);
		demFrame.pack();
		demFrame.setVisible(true);

	}
}
