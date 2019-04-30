/**
 * Luxury.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Luxury class extends Vehicle
 */
package gp;

import javax.swing.JOptionPane;

public class Luxury extends Vehicle{
	private double commissionRate;
	private boolean underWarranty;
	public Luxury(int idNum, String make, String model, String color, int price){//Constructor without warranty option
		super(idNum, make, model, color, price);
		this.commissionRate = 0.05;
		this.underWarranty = false;
	}
	public Luxury(int idNum, String make, String model, String color, int price, boolean underWarranty){//Constructor with warranty option  (When loaded from file)
		super(idNum, make, model, color, price);
		this.commissionRate = 0.05;
		this.underWarranty = underWarranty;
	}
	public double getCommissionRate() { return commissionRate; } //Overrides Vehicles function
	public boolean getOwnBoolean() { return underWarranty; } //Overrides Vehicles function
	public void purchaseWarranty() {//Ask user whether the car is on warranty or not
		int answer = JOptionPane.showConfirmDialog(null, "Do you want to purchase warranty on this car?","Purchase Warranty",JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			underWarranty = true;
		}
	}
}
