package organiser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


/**
 * Design a calendar and associate with todo list.
 * @author Zhenge Jia 2015
 *
 */
public class CalendarFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	Calendar cal = null;
	JPanel operationPanel = null;
	JPanel dateContainerPanel = null;
	JLabel pMonth = new JLabel("<");
	JLabel nMonth = new JLabel(">");
	JLabel pYear = new JLabel("<<");
	JLabel nYear = new JLabel(">>");
	JLabel monthLabel = new JLabel();
	JLabel todayLabel = new JLabel();

	private int year;
	private int month;
	private int day;

	public CalendarFrame() {
		
		// Get the calendar instance.
		Calendar cal = Calendar.getInstance();
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.day = cal.get(Calendar.DATE);
		this.setLocationRelativeTo(null);
		buildJFrame(cal);
	}

	public void buildJFrame(Calendar cal) {
		
		Container contentPane = getContentPane();
		contentPane.add(getOprPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel(cal), BorderLayout.CENTER);
		setSize(500, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}

	/**
	 *  Set up operation buttons and show months.
	 * @return
	 */
	public JPanel getOprPanel() {
		
		if (operationPanel == null) {
			operationPanel = new JPanel();
		}
		Box hBox = Box.createHorizontalBox();
		
		monthLabel.setText("     " + year + " - " + (month + 1) + "    " );
		todayLabel.setText("Today is "+year+"-"+(month+1)+"-"+day);
		Font fBox = new Font("", 3, 20);
		Font tBox = new Font("", 0, 13);
		pYear.setFont(fBox);
		hBox.add(pYear);
		hBox.add(Box.createHorizontalStrut(13));
		pMonth.setFont(fBox);
		hBox.add(pMonth);
		hBox.add(Box.createHorizontalStrut(10));
		monthLabel.setFont(fBox);
		hBox.add(monthLabel);
		hBox.add(Box.createHorizontalStrut(13));
		nMonth.setFont(fBox);
		hBox.add(nMonth);
		hBox.add(Box.createHorizontalStrut(10));
		nYear.setFont(fBox);
		hBox.add(nYear);
		hBox.add(Box.createHorizontalStrut(35));
		todayLabel.setFont(tBox);
		hBox.add(todayLabel);
		
		// Implement the function that view previous year by clicking JLabel pYear.
		pYear.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					Calendar cal = new GregorianCalendar(year, month, 1);
					cal.add(Calendar.YEAR, -1);
					year = cal.get(Calendar.YEAR);
					month = cal.get(Calendar.MONTH);
					monthLabel.setText("        " + year + " - " + (month + 1) + "       " );
					getPanel(cal);
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
			
			// Set the label to change colors and provides user a tip.
			@Override
			public void mouseEntered(MouseEvent e) {
				pYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
				pYear.setForeground(Color.blue);
				pYear.setToolTipText("Click to view previous year");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				pYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
				pYear.setForeground(Color.black);
			}

		});

		// Enable users to view previous month by clicking this label.
		pMonth.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					Calendar cal = new GregorianCalendar(year, month, 1);
					cal.add(Calendar.MONTH, -1);
					year = cal.get(Calendar.YEAR);
					month = cal.get(Calendar.MONTH);
					monthLabel.setText("        " + year + " - " + (month + 1) + "       ");
					getPanel(cal);
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
				pMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
				pMonth.setForeground(Color.blue);
				pMonth.setToolTipText("Click to view previous month");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				pMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
				pMonth
				.setForeground(Color.black);
			}

		});

		// Enable users to view next year by clicking this label.
		nYear.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					Calendar cal = new GregorianCalendar(year, month, 1);
					cal.add(Calendar.YEAR, 1);
					year = cal.get(Calendar.YEAR);
					month = cal.get(Calendar.MONTH);
					monthLabel.setText("        " + year + " - " + (month + 1) + "       ");
					getPanel(cal);
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
			// Change the color
			@Override
			public void mouseEntered(MouseEvent e) {
				nYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
				nYear.setForeground(Color.blue);
				nYear.setToolTipText("Click to view next year");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				nYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
				nYear.setForeground(Color.black);
			}

		});

		// Enable users to view next month by clicking this label.
		nMonth.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					Calendar cal = new GregorianCalendar(year, month, 1);
					cal.add(Calendar.MONTH, 1);
					year = cal.get(Calendar.YEAR);
					month = cal.get(Calendar.MONTH);
					monthLabel.setText("        " + year + " - " + (month + 1) + "       ");
					getPanel(cal);
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
				nMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
				nMonth.setForeground(Color.blue);
				nMonth.setToolTipText("Click to view next month");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				nMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
				nMonth.setForeground(Color.black);
			}

		});

		operationPanel.add(hBox);
		return operationPanel;
	}
	
	/**
	 * Build up main panel for calendar and return Calendar
	 * @param cal
	 * @return
	 */
	public JPanel getPanel(Calendar cal) {
		
		if (dateContainerPanel == null) {
			dateContainerPanel = new JPanel();
		}
		dateContainerPanel.removeAll();
		dateContainerPanel.setPreferredSize(new Dimension(200,200));
		

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int weeks = cal.get(Calendar.WEEK_OF_MONTH);

		GridLayout grid = new GridLayout(weeks + 1, 7);
		dateContainerPanel.setLayout(grid);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_MONTH, 1 - weekday);

		String[] weekTitle = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		// Add all days for panel in viewing month.
		Font hFont = new Font("", 2, 10);
		for (int i = 0; i < weeks + 1; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 0) {
					// Add title of days at first.
					JButton day = new JButton( weekTitle[j]);
					day.setEnabled(false);
					dateContainerPanel.add(day);
				} else {

					final JLabel week = new JLabel( "     " +cal.get(Calendar.DAY_OF_MONTH));			
					// Distinguish the days not in the viewing month by seting in various font.
					if (cal.get(Calendar.MONTH) != month) {
						week.setFont(hFont);
					} else {
						if (cal.get(Calendar.DAY_OF_MONTH) == day) {
							// Set today JLabel border to be different.
							week.setBorder(BorderFactory.createRaisedBevelBorder());
						} 
						week.setFont(new Font("", 3, 13));
						// If a todo thing is added into some day, the border of this day will be in different form.
						String fileName = ""+year+"-"+(month+1)+"-"+week.getText()+"";
						File file = new File("./todo/"+fileName+".txt");
						String content = null;
						
						// Read content from corresponding file.
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
							if ( ! content.equals("") ) {

								TitledBorder title = BorderFactory.createTitledBorder("ToDo");
								title.setTitleColor(Color.BLUE);
								title.setTitleFont(new Font("", 1, 10));
								title.setTitleJustification(TitledBorder.RIGHT);
								week.setBorder(title);
							}
						}

						// Implement MouseListener to enable users to add todo thing to any day and make every JLabel be able to change color.
						week.addMouseListener(new MouseListener() {
							
							String fileName = ""+year+"-"+(month+1)+"-"+week.getText()+"";
							File file = new File("./todo/"+fileName+".txt");
							String content = null;
							
							
							@Override
							public void mouseClicked( MouseEvent e) {
								if (e.getClickCount() == 1) {
																	
									new TodoList(fileName);
									
									try {
										FileReader reader = new FileReader(file);
										char[] chars = new char[(int) file.length()];
										reader.read(chars);
										content = new String(chars);
										reader.close();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									if ( ! content.equals("") ) {

										TitledBorder title = BorderFactory.createTitledBorder("ToDo");
										title.setTitleColor(Color.BLUE);
										title.setTitleFont(new Font("", 1, 10));
										title.setTitleJustification(TitledBorder.RIGHT);
										week.setBorder(title);
									} else {
										
										if (Integer.parseInt(week.getText().substring(5, 7)) == day) {
											week.setBorder(BorderFactory.createRaisedBevelBorder());
										} else {
											week.setBorder(null);
										}
										
										
									}
									//System.out.println(fileName);
								}

							}

							@Override
							public void mousePressed( MouseEvent e) {


							}

							@Override
							public void mouseReleased( MouseEvent e) {
								week.setCursor(new Cursor(Cursor.HAND_CURSOR));
								week.setForeground(Color.blue);

							}

							@Override
							public void mouseEntered(MouseEvent e) {
								week.setCursor(new Cursor(Cursor.HAND_CURSOR));
								week.setForeground(Color.blue);
								week.setToolTipText("Click to add a todo issure :)");

							}

							@Override
							public void mouseExited(MouseEvent e) {
								week.setCursor(new Cursor(Cursor.HAND_CURSOR));
								week.setForeground(Color.black);
							}

						});
					}



					dateContainerPanel.add(week);
					cal.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		return dateContainerPanel;
	}



	public static void main(String[] args) {
		new CalendarFrame();
	}
}
