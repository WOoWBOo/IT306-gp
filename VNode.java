/**
 * VNode.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * Node class for Vehicle LinkedList
 */
package gp;

public class VNode {
	private Vehicle data;
	private VNode next;
	
	public VNode(Vehicle data, VNode next) {
		this.data = data;
		this.next = next;
	}
	//Below are mutator and accessor
	public void setData(Vehicle data) { this.data = data; }
	public void setNext(VNode next){ this.next = next; }
	public Vehicle getData(){return data; }
	public VNode getNext() { return next; }
	public boolean hasNext() { 
		if (next!=null) return true; 
		else return false; 
	}
}
