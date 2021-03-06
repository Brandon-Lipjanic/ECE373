package org.hrs.system;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/* Demo by Lahiru Ariyananda and Peter Hall
   Note :  this is very simple program  used as a  demo on  Serilizing and GUIs. 
   It does not test for faults or errors (  which needs to be implemented) 
 */



public class HRGUI extends JFrame 
{ 
	private Employee  emp1;
	private JMenuBar menuBar;		//the horizontal container
	private JMenu adminMenu;		//JMenu objects are added to JMenuBar objects as the "tabs"
	private JMenu fileMenu;
	private ArrayList<Employee> empList;

	// File submenus
    
	
	
	// Admin 
	
	private JMenuItem adminPrintAll; 		//JMenuItem objects are added to JMenu objects as the drop down selections.
	private JMenuItem adminSetPayRate; 
	
	private JMenuItem Save;
	private JMenuItem Load;
	
	
	public HRGUI(String windowTitle) 
	{
		super(windowTitle);

		empList = new ArrayList<Employee>();
   		emp1 = new Employee() ;
		emp1.setName("Lahiru");
		emp1.setPayRate(10);
		emp1.setID("E2023");
		empList.add(emp1);

		setSize(300, 100);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JLabel("<HTML><center>Welcome to Human Resource System" +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
	}
	
	
	public void buildGUI() 
	{
		menuBar = new JMenuBar();
     	
		//File
		
		fileMenu = new JMenu("File");
		
		Save = new JMenuItem("Save");
		Load = new JMenuItem("Load");
		
		Save.addActionListener(new MenuListener());
		Load.addActionListener(new MenuListener());
		
		fileMenu.add(Save);
		fileMenu.add(Load);
		
		menuBar.add(fileMenu);
		
		
		// Employee Student Menu
		
		adminMenu = new JMenu("Administrator");
		
		adminSetPayRate = new JMenuItem ("Set PayRate" );
		adminPrintAll = new JMenuItem("Print Employee Info");

		adminSetPayRate.addActionListener(new MenuListener());		//JMenuItem's, via AbstractButton, have a method addActionListener(ActionListener)
		adminPrintAll.addActionListener(new MenuListener());		//The innerclass is implementing ActionListener, so it can take action when the JMenuItem is clicked.
	    
	    adminMenu.add(adminSetPayRate);
		adminMenu.add(adminPrintAll);
			
	    menuBar.add(adminMenu);
	
		setJMenuBar(menuBar);
		

		
		
		
	}
	
	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			
			if(source.equals(adminSetPayRate))
			{
				handleAdminSetPayRate();	
			}
			else if(source.equals(adminPrintAll))
			{
				handleAdminPrint();
			}
			
			else if(source.equals(Save)){
				handleFileSave();
			}
			else if (source.equals(Load)){
				emp1 = handleFileLoad();
			}
		}
		
		private void handleAdminSetPayRate()
		{
			String empName;
			empName = JOptionPane.showInputDialog(null, "Employee Name : ", "Input Pay Rate?", JOptionPane.QUESTION_MESSAGE);
			
			if(empName != null)
			{
				if(empName.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, 
												"Please enter correct Employee name", 
												"Error Employee doesn't exist", 
												JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					if(!containsEmployee(empName))
					{
						JOptionPane.showMessageDialog(null,
													"Employee  \""+empName+"\" doesn't exist.",
													"Error ",
													JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						String rate = JOptionPane.showInputDialog("Please input PayRate");
						if(rate != null)
						{
							emp1.setPayRate(Double.parseDouble(rate));
						}
					}
				}
			}
		}
		
		private void handleAdminPrint() 
		{
			if( emp1!=null)
			{
				emp1.Print();
			}
			else
			{
				JOptionPane.showMessageDialog(null, 
						"No Employee", 
						"Error", 
						JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		public void handleFileSave() {
			Employee.saveData(emp1);
		}
		
		public Employee handleFileLoad() {
			Employee tempEmployee = new Employee();
			tempEmployee = Employee.loadData();
			return tempEmployee;
		}
		
		public boolean containsEmployee(String name)
		{		
			for(int n = 0; n < empList.size(); n++) 
			{
				if ((empList.get(n).getName().equals( name)))
				{
					return true;
				}
			}
			return false;	
	    }
	}
	
}
