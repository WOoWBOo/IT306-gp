/**
 * ENode.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Node class for Employee LinkedList
 */
package gp;

public class ENode {
	private Employee data;
	private ENode next;
	
	public ENode(Employee data, ENode next) {
		this.data = data;
		this.next = next;
	}
	//Below are mutator and accessor
	public void setData(Employee data) { this.data = data; }
	public void setNext(ENode next){ this.next = next; }
	public Employee getData(){return data; }
	public ENode getNext() { return next; }
	public boolean hasNext() { 
		if (next!=null) return true; 
		else return false; 
	}
}
