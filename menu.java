/**
 * menu.java
 * @author Group WMI341 (Woosuk Kim)
 * @GNumber G01030946
 * @email wkim19@masonlive.gmu.edu
 * This is the main menu
 * IMPORTANT NOTE: You must configure the file path
 */
package gp;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import javax.swing.JOptionPane;

public class menu {
	/**
	 * YOU MUST CONFIGURE THE FILE PATH FIRST!!!
	 */
	private static String employeePath;
	private static String vehiclePath;
	private static LocalDate localDate = LocalDate.now();
	private static int display = 0;
	private static Scanner scanner = null;
	private static employeeLL eLL = new employeeLL();
	private static vehicleLL vLL = new vehicleLL();
	
	/**
	 * Check file. If file not exists, it creates one.
	 * If it already exists, it reads that text file and loads all values into linkedlist
	 * @throws IOException
	 */
	public static void fileCheck() throws IOException {
		try {//Read employee file
			 scanner = new Scanner(new FileInputStream(employeePath));
			 while (scanner.hasNextLine()) { //Read file and insert into list
				 String line = scanner.nextLine();
				 String[] eachEle = line.split(";");
				 int year = Integer.parseInt(eachEle[0]);
				 int month = Integer.parseInt(eachEle[1]);
				 int idNum = Integer.parseInt(eachEle[2].substring(1));
				 String name = eachEle[3];
				 long phone = Long.parseLong(eachEle[4]);
				 int salary = (int)Double.parseDouble(eachEle[5]);
				 int numSoldCar = Integer.parseInt(eachEle[6]);
				 double monthSale = Double.parseDouble(eachEle[7]);
				 double commission = Double.parseDouble(eachEle[8]);
				 eLL.addEmployee(new ENode(new Employee(year,month,idNum,name,phone,salary,numSoldCar,monthSale,commission), null));
			 }
			 scanner.close();
			 if(!eLL.exist(localDate.getYear(), localDate.getMonthValue())) { //When month is changed; creates new data for month
				 scanner = new Scanner(new FileInputStream(employeePath));
				 while (scanner.hasNextLine()) {
					 String line = scanner.nextLine();
					 String[] eachEle = line.split(";");
					 int year = Integer.parseInt(eachEle[0]);
					 int month = Integer.parseInt(eachEle[1]);
					 int idNum = Integer.parseInt(eachEle[2].substring(1));
					 String name = eachEle[3];
					 long phone = Long.parseLong(eachEle[4]);
					 int salary = (int)Double.parseDouble(eachEle[5]);
					 if(localDate.minusMonths(1).getYear() == year && localDate.minusMonths(1).getMonthValue() == month) {
						 Employee emp = new Employee(idNum,name,phone,salary);
						 eLL.addEmployee(new ENode(emp, null));
						 eFileAppend(emp);
					 }
				 }
				 scanner.close();
			 }
		} catch (FileNotFoundException e) { //When file is not exists
			File file = new File(employeePath);
			try {
				System.out.println("First time using system. Creating employee.txt");
				file.createNewFile();
			} catch (Exception e1) {
				System.out.println("Unable to launch system: File Error");
			}
		}
		try { //Read Vehicle file
			 scanner = new Scanner(new FileInputStream(vehiclePath));
			 while (scanner.hasNextLine()) { //Read vehicle data from file and insert into list
				 String line = scanner.nextLine();
				 String[] eachEle = line.split(";");
				 int idNum = Integer.parseInt(eachEle[0].substring(1));
				 String make = eachEle[1];
				 String model = eachEle[2];
				 String color = eachEle[3];
				 int price = (int)Double.parseDouble(eachEle[4]);
				 boolean ownBoolean = Boolean.parseBoolean(eachEle[5]);
				 if(price <= 50000) {
						vLL.addVehicle(new VNode(new Economic(idNum, make, model, color, price, ownBoolean), null));
					}
					else {
						vLL.addVehicle(new VNode(new Luxury(idNum, make, model, color, price, ownBoolean), null));
					}
			 }
			 scanner.close();
		} catch (FileNotFoundException e) { //When file is not exists
			File file = new File(vehiclePath);
			try {
				System.out.println("First time using system. Creating vehicle.txt");
				file.createNewFile();
			} catch (Exception e1) {
				System.out.println("Unable to launch system: File Error");
			}
		}
	}
	
	/**
	 * Check whether employee linkedlist contains given ID
	 * This method is to avoid duplicated ID numbers
	 * @param ID number
	 * @return ID value if it doesn't contains, else -1
	 */
	public static int empIDCheck(int num) {
		int idNum = num;
		if(eLL.containsID(num)) {
			idNum = -1;
		}
		return idNum;
	}
	
	/**
	 * Check whether vehicle linkedlist contains given ID
	 * This method is to avoid duplicated ID numbers
	 * @param ID number
	 * @return ID value if it doesn't contains, else -1
	 */
	public static int vehIDCheck(int num) {
		int idNum = num;
		if(vLL.containsID(num)) {
			idNum = -1;
		}
		return idNum;
	}
	
	/**
	 * Read the file and returns String value of data in file
	 * @param File path
	 * @return It returns whatever written on file
	 */
	public static String fileRead(String path) {
		String textFile = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String lines = br.readLine();
			while (lines != null) {
				String[] eachEle = lines.split(";");
				int year = Integer.parseInt(eachEle[0]);
				int month = Integer.parseInt(eachEle[1]);
				if(localDate.getYear() == year && localDate.getMonthValue() == month) {
					textFile += lines + "\n";
				}
				lines = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return textFile;
	}
	
	/**
	 * Append employee into file
	 * @param Employee
	 */
	public static void eFileAppend(Employee emp) {	
		try {
			String line = emp.getYear()+";"+emp.getMonth()+";"+emp.getID()+";"+emp.getName()+";"+emp.getPhoneNumber()+";"+emp.getSalary()+";"+emp.getNumSoldCar()+";"+emp.getMonthlySale()+";"+emp.getCommission()+"\n";
			BufferedWriter bw = new BufferedWriter(new FileWriter(employeePath, true));
			bw.append(line);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update employee on the file
	 * @param Employee that you want to update
	 * @param Updated employee
	 */
	public static void eFileUpdate(Employee PEmp, Employee NEmp) {
		String oldLines = "";
		try {
			String beforeChange = PEmp.getYear()+";"+PEmp.getMonth()+";"+PEmp.getID()+";"+PEmp.getName()+";"+PEmp.getPhoneNumber()+";"+PEmp.getSalary()+";"+PEmp.getNumSoldCar()+";"+PEmp.getMonthlySale()+";"+PEmp.getCommission()+"\n";
			String changeToThis = NEmp.getYear()+";"+NEmp.getMonth()+";"+NEmp.getID()+";"+NEmp.getName()+";"+NEmp.getPhoneNumber()+";"+NEmp.getSalary()+";"+NEmp.getNumSoldCar()+";"+NEmp.getMonthlySale()+";"+NEmp.getCommission()+"\n";
			BufferedReader br = new BufferedReader(new FileReader(employeePath));
			String line = br.readLine();
			while (line != null) {
				oldLines += line + "\n";
				line = br.readLine();
			}
			String newLines = oldLines.replaceAll(beforeChange, changeToThis);
			FileWriter writer = new FileWriter(employeePath);
			writer.write(newLines);
			br.close();
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Update vehicle on the file
	 * @param Vehicle that you want to remove
	 */
	public static void vFileUpdate(Vehicle PVeh) {
		String oldLines = "";
		try {
			String removeMe = PVeh.getID()+";"+PVeh.getMake()+";"+PVeh.getModel()+";"+PVeh.getColor()+";"+PVeh.getPrice()+";"+PVeh.getOwnBoolean()+"\n";
			BufferedReader br = new BufferedReader(new FileReader(vehiclePath));
			String line = br.readLine();;
			while (line != null) {
				if(!(line.trim()).contentEquals(removeMe.trim())) {
					oldLines += line + "\n";
					line = br.readLine();
				}else {line = br.readLine();}
			}
			FileWriter writer = new FileWriter(vehiclePath);
			writer.write(oldLines);
			br.close();
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Append vehicle into file
	 * @param vehicle
	 */
	public static void vFileAppend(Vehicle veh, boolean ownBoolean) {	
		try {
			String line = veh.getID()+";"+veh.getMake()+";"+veh.getModel()+";"+veh.getColor()+";"+veh.getPrice()+";"+ownBoolean+"\n";
			BufferedWriter bw = new BufferedWriter(new FileWriter(vehiclePath, true));
			bw.append(line);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * It checks whether user provided number with correct digits
	 * @param Phrase that asks to user
	 * @param Digit value that you want to set
	 * @return Number that fits given digit
	 * Can be used for ID digit check and phone number
	 */
	public static long digitCare(String s, int d) {
		long digit = Long.parseLong(JOptionPane.showInputDialog(s));
		boolean success = false;
		while (!success) {
			int dLength = String.valueOf(digit).length();
			if (dLength != d) {
				JOptionPane.showMessageDialog(null, "Please input "+d+" digit of number only");
				digit = Integer.parseInt(JOptionPane.showInputDialog(s));
			}
			else {
				success = true;
			}
		}
		return digit;
	}
	
	/**
	 * It checks whether user provided number with correct digits
	 * (This is only for updating phone number)
	 * @param Phrase that asks to user
	 * @param Digit value that you want to set
	 * @param Previous Phone Number
	 * @return Number that fits given digit
	 */
	public static long digitCare(String s, int d, long phoneNum) {
		long digit = Long.parseLong((String) JOptionPane.showInputDialog(null, s, "Input", JOptionPane.QUESTION_MESSAGE,null,null,phoneNum));
		boolean success = false;
		while (!success) {
			int dLength = String.valueOf(digit).length();
			if (dLength != d) {
				JOptionPane.showMessageDialog(null, "Please input "+d+" digit of number only");
				digit = Long.parseLong((String) JOptionPane.showInputDialog(null, s, "Input", JOptionPane.QUESTION_MESSAGE,null,null,phoneNum));
			}
			else {
				success = true;
			}
		}
		return digit;
	}
	
	/**
	 * It gets current year and month, and then shows current performance report
	 * @param Year y
	 * @param Month m
	 * It has option to see previous or next months performance.
	 * If no records found, it will show N/A
	 */
	public static void viewPerformance(int y, int m) {
		String[] ask = {"Previous Month","Exit","Next Month"};
		int action = JOptionPane.showOptionDialog(null, eLL.viewPerformance(y, m), "Performance Report of"+y+"-"+m,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, ask, ask[1]);
		    if (action == 0) {
		    	if(m == 1) {
		    		viewPerformance(y-1,12);
		    	}
		    	else {
		    		viewPerformance(y,m-1);
		    	}
		    }
		    else if (action == 2) {
		    	if(m == 12) {
		    		viewPerformance(y+1,1);
		    	}
		    	else {
		    		viewPerformance(y,m+1);
		    	}
		    }
	}
	
	/**
	 * Manage Employee menu
	 */
	public static void manageEmployee() {
		display = 1;
		String[] options = {"Add Employee","Update Employee","View Employees","Sort Employees","View Top 5 Employees","Return to Menu"};
		String ask = "What do you want to do?\n\n";
		do {
				int eMenuNum = JOptionPane.showOptionDialog(null, ask, "Manage Employee",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[5]);
				switch (eMenuNum) {
				case 0: //When User clicked Add Employee
					try {
						int idNum = -1;
						while(idNum == -1) {
							idNum = empIDCheck((int)digitCare("Employee's ID (3 digits):",3));
							if(idNum == -1) {
								JOptionPane.showMessageDialog(null, "Error! Duplicated ID");
							}
						}
						String name = JOptionPane.showInputDialog("Name of Employee:");
						try {
							long phone = digitCare("Phone Number (10 digits):",10);
							try {
								int salary = Integer.parseInt(JOptionPane.showInputDialog("Salary:"));
								Employee emp = new Employee(idNum,name,phone,salary);
								ENode newEm = new ENode(emp, null);
								eLL.addEmployee(newEm);
								eFileAppend(emp);
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Please input numbers only");
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Please input 10 digits of number only");
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please input 3 digits of number only");
					}
					break;
				case 1: //When User clicked Update Employee
					try {
						int eNum = Integer.parseInt(JOptionPane.showInputDialog(fileRead(employeePath)+"\n\nEmployee's ID (3-digit numbers only) whom you want to change:"));
						if (eLL.containsID(eNum)) {
							Employee preEmployee = eLL.getEmployee(eNum);
							String name = (String) JOptionPane.showInputDialog(null, "Name of Employee:", "Input", JOptionPane.QUESTION_MESSAGE,null,null,preEmployee.getName());
							try {
								long phone = digitCare("Phone Number (10 digits):",10,preEmployee.getPhoneNumber());
								try {
									int salary = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Salary:", "Input", JOptionPane.QUESTION_MESSAGE,null,null,(int)preEmployee.getSalary()));
									int year = preEmployee.getYear();
									int month = preEmployee.getMonth();
									int numSoldCar = preEmployee.getNumSoldCar();
									double monthSale = preEmployee.getMonthlySale();
									double commission = preEmployee.getCommission();
									Employee newEmployee = new Employee(year, month, eNum, name, phone, salary, numSoldCar, monthSale, commission);
									eLL.updateEmployee(newEmployee);
									eFileUpdate(preEmployee,newEmployee);
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Please input numbers only");
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Please input 10 digits of number only");
							}
						} 
						else {
							JOptionPane.showMessageDialog(null, "Please input 3-digit numbers only");
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please input numbers only");
					}
					
					break;
				case 2: //When User clicked Display Employee
					eLL.sortByID();
					eLL.displayEmployee(localDate.getYear(),localDate.getMonthValue());
					break;
				case 3: //When User clicked Sort Employee
					String[] sortOptions = new String[] {"Sort by Number of Sold Car","Sort by Commission", "Cancel"};
				    int sortBy = JOptionPane.showOptionDialog(null, "How do you want to sort Employees?", "Sort Employees",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, sortOptions, sortOptions[0]);
				    if (sortBy == 0) { //Sorting by number of sold car
				    	eLL.sortByNumSoldCar();
				    	eLL.displayEmployee(localDate.getYear(),localDate.getMonthValue());
				    	eLL.sortByID();
				    }
				    else if (sortBy == 1) { //Sorting by Commission
				    	eLL.sortByCommission();
				    	eLL.displayEmployee(localDate.getYear(),localDate.getMonthValue());
				    	eLL.sortByID();
				    }
					break;
				case 4: //When User clicked Display Top 5 Employee
					eLL.displayTopFive();
					break;
				default: //When User clicked Return to menu
					display = 0;
				}
		} while(display != 0);
	}
	
	/**
	 * Manage Vehicle menu
	 */
	public static void manageVehicle() {
		display = 2;
		String[] options = {"Add Vehicle","Remove Vehicle","View Vehicles","Return to Menu"};
		String ask = "What do you want to do?\n\n";
		do {
				int vMenuNum = JOptionPane.showOptionDialog(null, ask, "Manage Vehicle",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[3]);
				switch (vMenuNum) {
				case 0: //When User clicked Add Vehicle
					try {
						int idNum = -1;
						while(idNum == -1) {
							idNum = vehIDCheck((int)digitCare("Vehicle's ID (4 digits):",4));
							if(idNum == -1) {
								JOptionPane.showMessageDialog(null, "Error! Duplicated ID");
							}
						}
						String make = JOptionPane.showInputDialog("Vehicle Make:");
						String model = JOptionPane.showInputDialog("Vehicle Model:");
						String color = JOptionPane.showInputDialog("Vehicle Color:");
						try {
							int price = 0;
							price = Integer.parseInt(JOptionPane.showInputDialog("Vehicle Price ($5,000 - $150,000):"));
							while(price<5000 || price > 150000) {
								JOptionPane.showMessageDialog(null, "Price Range $5,000 - $150,000");
								price = Integer.parseInt(JOptionPane.showInputDialog("Vehicle Price ($5,000 - $150,000):"));
							}
							if(price <= 50000) { //Economic
								Economic v = new Economic(idNum, make, model, color, price);
								v.promotionEvent();
								VNode newV = new VNode(v, null);
								vLL.addVehicle(newV);
								vFileAppend(v,v.getOwnBoolean());
							}
							else { //Luxury
								Luxury v = new Luxury(idNum, make, model, color, price);
								v.purchaseWarranty();
								VNode newV = new VNode(v, null);
								vLL.addVehicle(newV);
								vFileAppend(v,v.getOwnBoolean());
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Please input numbers only");
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please input 4 digits of number only");
					}
					break;
				case 1: //When User clicked Remove Vehicle
					if(vLL.isEmpty()) JOptionPane.showMessageDialog(null, "Vehicle list is empty");
					else {
						try {
						int vNum = Integer.parseInt(JOptionPane.showInputDialog(vLL.IDList()+"\n\nWhich Vehicle you want to remove? (Only numbers)"));
						if(vLL.containsID(vNum)) {
							vLL.removeVehicle(vNum,employeePath,eLL);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "There is no Vehicle with ID: V"+vNum);
						}
						} catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Please input 4 digits of number only");
						}
					}
					break;
				case 2: //When User clicked Display Vehicle
					vLL.displayVehicle();
					break;
				default: //When User clicked Return to menu
					display = 0;
				}
		} while(display != 0);
		display = 0;
	}
	
	/**
	 * Performance menu
	 */
	public static void performance() {
		display = 3;
		String[] options = {"View monthly performance","Return to Menu"};
		do {
			int pMenuNum = JOptionPane.showOptionDialog(null, "What do you want to do?", "Performance",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[1]);
			switch (pMenuNum) {
			case 0: //When User clicked view monthly performance
				eLL.sortByID();
				viewPerformance(localDate.getYear(), localDate.getMonthValue());
				break;
			default: //When User clicked REturn to menu
				display = 0;;
			}
		} while(display != 0);
		display = 0;
	}
	/**
	 * Set employee file path
	 * @param Path
	 */
	public static void setEPath(String path) {
		employeePath = path;
	}
	/**
	 * Set vehicle file path
	 * @param Path
	 */
	public static void setVPath(String path) {
		vehiclePath = path;
	}
	/**
	 * Launch the main menu
	 * @throws IOException
	 */
	public static void launch(String ePath, String vPath) throws IOException {
		setEPath(ePath);
		setVPath(vPath);
		fileCheck(); //When it loaded, first check files
		String[] options = {"Manage Employee","Manage Vehicle","Performance","Quit"};
		String ask = "Car Dealership Managing Application                                                       Today: "+localDate + "\n\nWhat do you want to do?\n\n";
		while (display != -1) {
				int num = JOptionPane.showOptionDialog(null, ask, "Main Menu",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[3]);
				switch (num) {
				case 0: //When User clicked Manage Employee
					manageEmployee();
					break;
				case 1: //When User clicked Manage Vehicle
					manageVehicle();
					break;
				case 2: //When User clicked Performance
					performance();
					break;
				default: //When User clicked Quit
					display = -1;;
				}
		}
		System.out.println("");
	}
}
