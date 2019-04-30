/**
 * employeeLL.java is Employee LinkedList
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Uses Employee Node
 * 
 */
package gp;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class employeeLL {
	private ENode head; 
	private ENode sorted;
	private final int MAX_EMPLOYEE = 50;
	private int size;
	private int totalSize;
	
	public employeeLL(){size = 0; }
	
	public boolean isEmpty() {if (head==null) return true; else return false; }
	
	public int getSize(){ return size; }
	
	/**
	 * Get size of list on month m
	 * @param Year y
	 * @param Month m
	 * @return Size of list
	 */
	
	public int monthSize(int y, int m) {
		int thisSize = 0;
		ENode curr = head;
		if(isEmpty()) return thisSize;
		else {
			while(curr != null){
				if (curr.getData().getYear() == y && curr.getData().getMonth() == m) {
					thisSize++;
				}
				curr = curr.getNext();
			}
		}
		return thisSize;
	}
	
	/**
	 * Check whether it exists
	 * @param Year y
	 * @param Month m
	 * @return True if node exist within given year and month, else false
	 */
	public boolean exist(int y, int m) {
		ENode curr = head;
		if(isEmpty()) return false;
		else {
			while(curr != null){
				if (curr.getData().getYear() == y && curr.getData().getMonth() == m) {
					return true;
				}
				curr = curr.getNext();
			}
		}
		return false;
	}
	
	/**
	 * Check whether list contains given ID
	 * @param Number of ID
	 * @return True if contains, false if not
	 */
	public boolean containsID(int idNum) {
		ENode curr = head;
		String id = "E"+idNum;
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
	 * Get employee if ID matches
	 * @param Number of ID
	 * @return Matched employee
	 */
	public Employee getEmployee(int idNum) {
		ENode curr = head;
		String id = "E"+idNum;
		if(isEmpty()) return null;
		else {
			while(curr != null){
				if (curr.getData().getID().equals(id)) {
					return curr.getData();
				}
				curr = curr.getNext();
			}
		}
		return null;
	}
	
	/**
	 * Add employee to the list
	 * @param Employee
	 */
	public void addEmployee(ENode e) {
		if (getSize() < MAX_EMPLOYEE) {
			if(head==null) head = e; 
			else{
				ENode curr = head; 
				if(!curr.hasNext()) {
					curr.setNext(e);
				}
			
				else{
					while(curr.hasNext()){
						curr = curr.getNext(); 
					}
					curr.setNext(e);
				}
			}
		}
		if(e.getData().getYear() == LocalDate.now().getYear() && e.getData().getMonth() == LocalDate.now().getMonthValue()){
			size++;
		}
		totalSize++;
	}
	
	/**
	 * Update employee on the list
	 * @param e
	 */
	public void updateEmployee(Employee e) {
		ENode curr = head;
		String id = e.getID();
		if(isEmpty()) JOptionPane.showMessageDialog(null, "Employee list is empty");
		else{
			//if there is only one node in the linkedlist:
			if(!curr.hasNext()) {
				if (curr.getData().getID().equals(id)) {
					curr.setData(e);
				}
			}
			
			//if there are multiple nodes inside the linkedlist
			else{
				//we need a loop
				while(curr != null){
					if (curr.getData().getID().equals(id)) {
						curr.setData(e);
					}
					curr = curr.getNext();
				}
			}
		}
	}
	
	/**
	 * Show all employees on the list within given year and month
	 * @param Year y
	 * @param Month m
	 */
	public void displayEmployee(int y, int m) {
		ENode curr = head;
		String output = "";
		if(isEmpty()) JOptionPane.showMessageDialog(null, "Employee list is empty");
		else {
			while(curr != null){
				if(curr.getData().getYear() == y) {
					if(curr.getData().getMonth() == m) {
						output+= curr.getData() + "\n";
					}
				}
				curr = curr.getNext();
			}
		}
		if(output.equals("")) {
			JOptionPane.showMessageDialog(null, "Employee list is empty");
		}else {
			JOptionPane.showMessageDialog(null, output); 
		}
	}
	
	/**
	 * Sort list by ID
	 */
	public void sortByID() {
        if (totalSize > 1) {
            boolean wasChanged;

            do {
                ENode current = head;
                ENode previous = null;
                ENode next = head.getNext();
                wasChanged = false;

                while ( next != null ) {
                    if (current.getData().getYMID() > next.getData().getYMID()) {
                        wasChanged = true;

                        if ( previous != null ) {
                        	ENode sig = next.getNext();

                            previous.setNext(next);
                            next.setNext(current);
                            current.setNext(sig);
                        } else {
                            ENode sig = next.getNext();

                            head = next;
                            next.setNext(current);
                            current.setNext(sig);
                        }

                        previous = next;
                        next = current.getNext();
                    } else { 
                        previous = current;
                        current = next;
                        next = next.getNext();
                    }
                } 
            } while( wasChanged );
        }
    }
	
	/**
	 * Sort list by Number of sold car using sortingNumSoldCar method
	 */
	public void sortByNumSoldCar() {
		sorted = null;
		ENode current = head;
		while (current != null) {
			ENode next = current.getNext();
			sortingNumSoldCar(current);
			current = next;
		}
		head = sorted;
	}
	
	/**
	 * Sort list by Commission using sortingCommission method
	 */
	public void sortByCommission() {
		sorted = null;
		ENode current = head;
		while (current != null) {
			ENode next = current.getNext();
			sortingCommission(current);
			current = next;
		}
		head = sorted;
	}
	
	/**
	 * Method that actually sorts the list by commission
	 * @param ENode
	 */
	public void sortingCommission(ENode node){
		if (sorted == null || sorted.getData().getCommission() < node.getData().getCommission()) {
			node.setNext(sorted);
			sorted = node;
		}
		else 
        { 
			ENode current = sorted; 
            /* Locate the node before the point of insertion */
            while (current.getNext() != null && current.getNext().getData().getCommission() >= node.getData().getCommission())  
            { 
                current = current.getNext();
            } 
            node.setNext(current.getNext()); 
            current.setNext(node); 
        } 
		
	}
	
	/**
	 * Method that actually sorts the list by Number of sold car
	 * @param node
	 */
	public void sortingNumSoldCar(ENode node){
		if (sorted == null || sorted.getData().getNumSoldCar() < node.getData().getNumSoldCar()) {
			node.setNext(sorted);
			sorted = node;
		}
		else 
        { 
			ENode current = sorted; 
            /* Locate the node before the point of insertion */
            while (current.getNext() != null && current.getNext().getData().getNumSoldCar() >= node.getData().getNumSoldCar())  
            { 
                current = current.getNext();
            } 
            node.setNext(current.getNext()); 
            current.setNext(node); 
        } 
		
	}
	
	/**
	 * Show Top 5 employees who has higher commissions
	 */
	public void displayTopFive() {
		ENode curr = head;
		String output = "Top 5 Highest Commission Employees\nID | Name | Commission\n\n";
		if(isEmpty()) JOptionPane.showMessageDialog(null, "Employee list is empty");
		else{
			//if there is only one node in the linkedlist:
			if(!curr.hasNext()) {
				output+= "1. " + curr.getData().getID() + " | " + curr.getData().getName() + " | $"+curr.getData().getCommission();
				JOptionPane.showMessageDialog(null, curr.getData()); 
			}
			
			//if there are multiple nodes inside the linkedlist
			else{
				sortByCommission();
				curr = head;
				int count = 0;
				if(getSize() <5) {
					while(count<getSize()) {
						if(curr.getData().getYear() == LocalDate.now().getYear() && curr.getData().getMonth() == LocalDate.now().getMonthValue()) {
							output+= (count+1)+". " + curr.getData().getID() + " | " + curr.getData().getName() + " | $"+curr.getData().getCommission()+"\n";
							count++;
						}
						curr = curr.getNext();
					}
				}
				else {
					while(count<5) {
						if(curr.getData().getYear() == LocalDate.now().getYear() && curr.getData().getMonth() == LocalDate.now().getMonthValue()) {
							output+= (count+1)+". " + curr.getData().getID() + " | " + curr.getData().getName() + " | $"+curr.getData().getCommission()+"\n";
							count++;
						}
						curr = curr.getNext();
					}
				}
				JOptionPane.showMessageDialog(null, output); 
				sortByID();
			}
		}
	}
	
	/**
	 * Prints the list of employees
	 */
	public void getList() {
		ENode curr = head;
		String output = "";
		while(curr != null){
			output+= curr.getData() + "\n";
			curr = curr.getNext();
		}
		JOptionPane.showMessageDialog(null, output); 
	}
	
	/**
	 * Gets value for year and month then returns the performance of that month/year
	 * @param Year y
	 * @param Month m
	 * @return String value of report
	 */
	public String viewPerformance(int y, int m) {
		double tms = 0;
		int totalNumSoldCar = 0;
		double tec = 0;
		boolean hasData = false;
		ENode curr = head;
		String output = "";
		output+= "_________________________________Performance Report of "+y+"-"+m+"_______________________________\n\n";
		if(isEmpty()) output+= "\n\nN/A\n\nDatabase is empty";
		else {
			output+= "Employees\n\n";
			while(curr != null){
				if(curr.getData().getYear() == y) {
					if(curr.getData().getMonth() == m) {
						hasData = true;
						tms += curr.getData().getMonthlySale();
						totalNumSoldCar += curr.getData().getNumSoldCar();
						tec += curr.getData().getCommission();
						output+= curr.getData() + "\n";
					}
				}
				curr = curr.getNext();
			}
			sortByCommission();
			curr = head;
			int count = 0;
			if(monthSize(y,m)>5) {
				output+= "_____________________________________________________________________________________________";
				output+= "\nTop 5 Highest Commission Employees\nID | Name | Commission\n\n";
				while(count<5) {
					if(curr.getData().getYear() == y && curr.getData().getMonth() == m) {
						output+= (count+1)+". " + curr.getData().getID() + " | " + curr.getData().getName() + " | $"+curr.getData().getCommission()+"\n";
						count++;
					}
					curr = curr.getNext();
				}
			}
			else if(monthSize(y,m)<5 && monthSize(y,m) > 0) {
				while(count<getSize()) {
					if(curr.getData().getYear() == y && curr.getData().getMonth() == m) {
						output+= (count+1)+". " + curr.getData().getID() + " | " + curr.getData().getName() + " | $"+curr.getData().getCommission()+"\n";
						count++;
					}
					curr = curr.getNext();
				}
			}
			if(hasData) {
				tms = Math.round(tms*100);
				double totalMonthSale = tms/100;
				tec = Math.round(tec*100);
				double totalEmployeeCommission = tec/100;
				output+= "_____________________________________________________________________________________________\n";
				output+= "\nTotal Number of Sold Cars: "+totalNumSoldCar+"\nTotal Sale: $"+totalMonthSale+"\nTotal Employee's Commissions: $"+totalEmployeeCommission;
			}
			else {
				output+= "N/A\n\nDatabase is empty";
			}
			sortByID();
		}
		return output; 
	}
}
