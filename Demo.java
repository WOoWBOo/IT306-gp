/**
 * Demo.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Launches the menu
 */
package gp;

import java.io.IOException;

public class Demo {
	//PATH MUST BE CONFIGURED FIRST!!!!
	private static String ePath = "/Users/kimwoosuk/eclipse-workspace/IT306/src/gp/employee.txt";
	private static String vPath = "/Users/kimwoosuk/eclipse-workspace/IT306/src/gp/vehicle.txt";
	public static void main(String[] args) {
		try {
			menu.launch(ePath, vPath);
		} catch (IOException e) {
			System.out.println("Unable to launch system: File Error");
		}
	}
}
