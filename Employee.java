/**
 * Employee.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Employee Object
 */
package gp;

import java.time.LocalDate;

public class Employee {
	private int year;
	private int month;
	private String ID;
	private String name;
	private long phoneNumber;
	private double salary;
	private double commission;
	private int numSoldCar;
	private double monthlySale;

	public Employee() {}
	//When employee is newly created
	public Employee(int numID, String name, long phoneNumber, int salary) {
		this.year = LocalDate.now().getYear();
		this.month = LocalDate.now().getMonthValue();
		this.ID = "E"+numID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		this.commission = 0;
		this.numSoldCar = 0;
		this.monthlySale = 0;
	}
	//When employee is loaded from the file
	public Employee(int year, int month, int numID, String name, long phoneNumber, int salary, int numSoldCar, double monthlySale, double commission) {
		this.year = year;
		this.month = month;
		this.ID = "E"+numID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		this.commission = commission;
		this.numSoldCar = numSoldCar;
		this.monthlySale = monthlySale;
	}
	
	//Below are mutator and accessor of Employee
	public String getName() { return name; }
	public String getID() { return ID; }
	public long getPhoneNumber() { return phoneNumber; }
	public String getValidPhoneNumber() {
		String strPhone = Long.toString(phoneNumber);
		StringBuilder validPhone = new StringBuilder(strPhone);
		validPhone.insert(6, "-");
		validPhone.insert(3, "-");
		return validPhone.toString();
	}
	public double getSalary() { return salary+0.99; }
	public double getCommission() { return commission; }
	public int getNumSoldCar() { return numSoldCar; }
	public double getMonthlySale() { return monthlySale; }
	public int getYear() { return year; }
	public int getMonth() { return month; }
	public int getYMID() { return Integer.parseInt(String.valueOf(year)+String.valueOf(month)+ID.substring(1)); }
	public void setName(String name) { this.name = name; }
	public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }
	public void setSalary(int salary) { this.salary = salary; }
	public void incrementNumSoldCar() { this.numSoldCar++; }
	public void addCommission(double amount) { this.commission += amount; }
	public void addMonthlySale(double amount) { this.monthlySale += amount; }
	public String toString() {
		return ID + " | " + name + " | " + getValidPhoneNumber() + " | $" + salary +" | Sold "+numSoldCar+" Cars | Monthly Sale: $"+monthlySale+" | Commission: $"+commission;
	}
	
}
