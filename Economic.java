/**
 * Economic.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Economic class extends Vehicle
 */
package gp;

import javax.swing.JOptionPane;

public class Economic extends Vehicle{
	private double commissionRate;
	private boolean onPromotion;
	public Economic(int idNum, String make, String model, String color, int price){//Constructor without promotion option
		super(idNum, make, model, color, price);
		this.commissionRate = 0.03;
		this.onPromotion = false;
	}
	public Economic(int idNum, String make, String model, String color, int price, boolean onPromotion){//Constructor with promotion option (When loaded from file)
		super(idNum, make, model, color, price);
		this.commissionRate = 0.03;
		this.onPromotion = onPromotion;
	}
	public double getCommissionRate() { return commissionRate; } //Overrides Vehicles function
	public boolean getOwnBoolean() { return onPromotion; } //Overrides Vehicles function
	public void promotionEvent() {//Ask user whether the car is on promotion or not
		int answer = JOptionPane.showConfirmDialog(null, "Do you want to promote an event on this car?","Promotion Event",JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			onPromotion = true;
		}
	}
}
