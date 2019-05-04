/**
 * vehicleLL.java is a linkedlist for Vehicle
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Uses Vehicle Node
 */
package gp;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class vehicleLL {
	private VNode head;
	private final int MAX_VEHICLE = 200;
	private int size; 
	
	/**
	 * Initial
	 */
	public vehicleLL(){size = 0;}
	
	/**
	 * Check whether it's empty
	 * @return True if it's empty
	 */
	public boolean isEmpty() {if (head==null) return true; else return false; }
	
	/**
	 * Get size of the list
	 * @return Size
	 */
	public int getSize(){ return size; }
	
	/**
	 * Check if this ID is in the list
	 * @param Number of ID
	 * @return True if contains, else false
	 */
	public boolean containsID(int idNum) {
		VNode curr = head;
		String id = "V"+idNum;
		if(isEmpty()) return false;
		else {
			while(curr != null){
				if (curr.getData().getID().equals(id)) {
					return true;
				}
				curr = curr.getNext();
			}
		}
		return false;
	}
	
	/**
	 * Returns the list of vehicle IDs
	 * @return The list of vehicle IDs
	 */
	public String IDList() {
		String list = "";
		VNode curr = head;
		int counter = 0;
		while(curr != null){
			counter++;
			if(counter%5==0) {
				list+= curr.getData().getID()+"\n";
			}
			else {
				list+= curr.getData().getID() + " | ";
			}
			curr = curr.getNext();
		}
		return list;
	}
	
	/**
	 * Add vehicle to the list
	 * @param Vehicle
	 */
	public void addVehicle(VNode v) {
		if (getSize() < MAX_VEHICLE) {
			VNode curr = head; 
			VNode previous = head;
			if(head==null) { head = v; size++;}
			else if(getSize()==1) { //when the list has only one node
				if(Integer.parseInt(v.getData().getID().substring(1)) < Integer.parseInt(curr.getData().getID().substring(1))){
					v.setNext(curr);
					head = v;
					size++; 
				}
				else {
					curr.setNext(v);
					size++; 
				}
			}
			else { //When there is multiple, insert them as being sorted
				curr = curr.getNext();
				if(Integer.parseInt(v.getData().getID().substring(1)) < Integer.parseInt(head.getData().getID().substring(1))){
					v.setNext(previous);
					head = v;
					size++;
					return;
				}
				while(curr!=null){
					if(Integer.parseInt(v.getData().getID().substring(1)) < Integer.parseInt(curr.getData().getID().substring(1))) {
						previous.setNext(v);
						v.setNext(curr);
						size++;
						return;
					}
					
					previous = curr; 
					curr = curr.getNext();
					
				}
				previous.setNext(v);
				size++;
			}
		}
	}
	
	/**
	 * Remove vehicle from the list
	 * It needs Employee's info in order to increment commission and number of sold car unless it's scrapped
	 * @param Numbers of ID
	 * @param Employee File Path
	 * @param Employee linkedlist
	 */
	public void removeVehicle(int num, String employeePath, employeeLL eLL) {
		String[] options = new String[] {"Sold","Scrapped", "Cancel"};
		VNode curr = head;
		VNode previous = curr;
		String id = "V"+num;
		int reason = JOptionPane.showOptionDialog(null, "Why do you want to remove this vehicle?", "Sort Employees",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
		if (reason == 0) {
			try {
				int eNum = Integer.parseInt(JOptionPane.showInputDialog(menu.fileRead(employeePath)+"\n\nEmployee's ID (3-digit numbers only) who sold this car:"));
				if (eLL.containsID(eNum)) {
					if(!curr.hasNext()) {
						if(curr.getData().getID().equals(id)) {
							menu.vFileUpdate(curr.getData());
							double commission = Math.round((curr.getData().getPrice() * curr.getData().getCommissionRate())*100)/100;
							Employee pemp = eLL.getEmployee(eNum);
							eLL.getEmployee(eNum).incrementNumSoldCar();
							eLL.getEmployee(eNum).addCommission(commission);
							Employee nemp = eLL.getEmployee(eNum);
							head = null;
							menu.eFileUpdate(pemp, nemp);
							size--;
						}
					}
					else {
						while(curr != null) {
							if(curr.getData().getID().equals(id)) {
								menu.vFileUpdate(curr.getData());
								double comBefCurrency = Math.round(curr.getData().getPrice() * curr.getData().getCommissionRate()*100);
								double commission = comBefCurrency/100;
								Employee pemp = eLL.getEmployee(eNum);
								Employee nemp = new Employee(pemp.getYear(),pemp.getMonth(),eNum,pemp.getName(),pemp.getPhoneNumber(),(int)pemp.getSalary(),pemp.getNumSoldCar(),pemp.getMonthlySale(),pemp.getCommission());
								nemp.incrementNumSoldCar();
								nemp.addCommission(commission);
								nemp.addMonthlySale(curr.getData().getPrice());
								curr = curr.getNext();
								previous.setNext(null);
								previous.setNext(curr);
								menu.eFileUpdate(pemp, nemp);
								size--;
							}
							previous = curr;
							if(curr != null) {
								curr = curr.getNext();
							}
						}
					}

				}
					
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please input numbers only");
			}
			
			
		}
		else if (reason == 1) {
			if(!curr.hasNext()) {
				if(curr.getData().getID().equals(id)) {
					menu.vFileUpdate(curr.getData());
					head = null;
					size--;
					JOptionPane.showMessageDialog(null, id+" succeessfully removed from the database");
				}
			}
			else {
				while(curr != null) {
					if(curr.getData().getID().equals(id)) {
						menu.vFileUpdate(curr.getData());
						if(curr == head) {
							curr = curr.getNext();
							head = null;
							previous = curr;
							head = previous;
							JOptionPane.showMessageDialog(null, id+" succeessfully removed from the database");
						}else {
							curr = curr.getNext();
							previous.setNext(curr);
							JOptionPane.showMessageDialog(null, id+" succeessfully removed from the database");
						}
						size--;
					}
					previous = curr;
					if(curr != null) {
						curr = curr.getNext();
					}
				}
			}
		}
	}
	
	/**
	 * Display vehicle list
	 */
	public void displayVehicle() {
		VNode curr = head;
		ArrayList<String> output = new ArrayList<String>();
		if(isEmpty()) JOptionPane.showMessageDialog(null, "Vehicle list is empty");
		else{
			if(!curr.hasNext()) {
				JOptionPane.showMessageDialog(null, curr.getData()); 
			}
			else{
				while(curr != null){
					String s = curr.getData() + "\n";
					output.add(s);
					curr = curr.getNext();
				}
			}
		}
		//Scroll****
		JList<Object> outputList = new JList<Object>(output.toArray());
		JScrollPane scrollPane = new JScrollPane(outputList);
		JOptionPane.showMessageDialog(null, scrollPane); 
	}
}
