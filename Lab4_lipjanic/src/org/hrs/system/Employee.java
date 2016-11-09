/*
 * subclass of Person, special Person that can only take one class at a time
 * added to a Course's student roster
 */

package org.hrs.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee extends Person implements Serializable
{
	
	private  double payRate;
	private  String id;
    
	public Employee()
	{
		super();
		payRate=0;
		id="NoID";
	}
	
	protected void setPayRate(double payRate)
	{
		this.payRate=payRate;
	}
	
	protected void setID(String iD)
	{
		this.id=iD;	
	}

	
	protected double getPayRate()
	{
		return payRate;
	}
	
	protected String getID()
	{
		return id;	
	}
	
	
	
	public void raise (double percent)
	{	
		this.payRate=this.payRate*(1+ percent/100);	
	}

	
	protected double earns() 
	{
	 	double earned ;
	 	earned = 100*payRate;
	 	return earned;	
	}
	
	public static Employee loadData() {
		FileInputStream inFile = null;
		ObjectInputStream inObj = null;
		Employee e = null;
		
		try {
			inFile = new FileInputStream("Employee.ser");
			inObj = new ObjectInputStream(inFile);
			e = (Employee) inObj.readObject();
			inObj.close();
			inFile.close();
		}
		
		catch (IOException i) {
			i.printStackTrace();
		}
		
		catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
		
		return e;
	}
	
	public static void saveData(Employee e) {
		FileOutputStream outFile = null;
		ObjectOutputStream outObj = null;
		
		try {
			outFile = new FileOutputStream("Employee.ser");
			outObj = new ObjectOutputStream(outFile);
			outObj.writeObject(e);
			outObj.close();
			outFile.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		
		
		
		
		
	}

	public void Print()
	{	  
	    System.out.println("\n\nName: " + this.getName());
	    System.out.println("Pay Rate ($ per hour) : " +this.getPayRate());
	    System.out.println("Employee ID : " + this.getID()); 
	    System.out.println("Employee Earns : " + this.earns());	
	}
		

}
