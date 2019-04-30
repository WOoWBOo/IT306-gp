/**
 * Vehicle.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Vehicle Object
 */
package gp;

public class Vehicle {
	private String ID;
	private String make;
	private String model;
	private String color;
	private int price;

	public Vehicle() {}
	//When Vehicle is newly created
	public Vehicle(int idNum, String make, String model, String color, int price) {
		this.ID = "V"+idNum;
		this.make = make;
		this.model = model;
		this.color = color;
		this.price = price;
	}
	//Below are mutator and accessor of Vehicle
	public String getMake() { return make; }
	public String getID() { return ID; }
	public String getModel() { return model; }
	public String getColor() { return color; }
	public double getPrice() { return price +0.99; }
	public double getCommissionRate() { return getCommissionRate(); }
	public boolean getOwnBoolean() { return getOwnBoolean(); }
	public String toString() {
		return ID + " | " + make + " | " + model + " | " + color + " | Price: $"+price;
	}
}
