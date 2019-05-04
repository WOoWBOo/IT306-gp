package gp;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class JUnitTest {
	LocalDate localDate = LocalDate.now();
	/**
	 * Test employee methods
	 */
	@Test
	public void employeeTest() {
		//Year, Month, ID, Name, Phone Number, Salary, Number of sold cars, Monthly Sale, Commission
		Employee e = new Employee(1234, 5, 987, "Test",1284985621,85466,10,462100.85,59462.17);
		assertEquals("Year must be 1234", 1234, e.getYear());
		assertEquals("Month must be 5", 5, e.getMonth());
		assertEquals("ID must be E987", "E987", e.getID());
		assertEquals("Name must be Test", "Test", e.getName());
		assertEquals("Phone Number must be 1284985621", 1284985621, e.getPhoneNumber());
		assertEquals("Salary must be 85466", 85466.99, e.getSalary(),0);
		assertEquals("Number of sold cars must be 10", 10, e.getNumSoldCar());
		assertEquals("Monthly Sale must be 462100.85", 462100.85, e.getMonthlySale(),0);
		assertEquals("Commission must be 59462.17", 59462.17, e.getCommission(),0);
	}
	/**
	 * Test vehicle methods
	 */
	@Test
	public void vehicleTest() {
		//int idNum, String make, String model, String color, int price, boolean onPromotion
		Economic eV = new Economic(1111, "Make1", "Model1", "Color1", 111111, true);
		assertEquals("ID must be V1111","V1111",eV.getID());
		assertEquals("Make must be Make1","Make1",eV.getMake());
		assertEquals("Model must be Model1","Model1",eV.getModel());
		assertEquals("Color must be Color1","Color1",eV.getColor());
		assertEquals("Price must be 111111.99",111111.99,eV.getPrice(),0);
		assertEquals("On promotion must be true",true,eV.getOwnBoolean());
		//int idNum, String make, String model, String color, int price, boolean underWarranty
		Luxury lV = new Luxury(2222, "Make2", "Model2", "Color2", 222222, false);
		assertEquals("ID must be V2222","V2222",lV.getID());
		assertEquals("Make must be Make2","Make2",lV.getMake());
		assertEquals("Model must be Model2","Model2",lV.getModel());
		assertEquals("Color must be Color2","Color2",lV.getColor());
		assertEquals("Price must be 222222.99",222222.99,lV.getPrice(),0);
		assertEquals("Under Warranty must be false",false,lV.getOwnBoolean());
	}
	/**
	 * ENode Test
	 */
	@Test
	public void employeeNodeTest() {
		ENode e = new ENode(new Employee(),null);
		assertEquals("Data must be e.getData()",e.getData(),e.getData());
		assertEquals("Next must be null",null,e.getNext());
		assertEquals("Has next must be false",false,e.hasNext());
	}
	/**
	 * VNode Test
	 */
	@Test
	public void vehicleNodeTest() {
		//head is v1, v1's next is v2 and v2's next is null
		VNode v2 = new VNode(new Vehicle(),null);
		VNode v1 = new VNode(new Vehicle(),v2);
		assertEquals("v1 data must be v1.getData()",v1.getData(),v1.getData());
		assertEquals("v2 data must be v2.getData()",v2.getData(),v2.getData());
		assertEquals("Next must be v2",v2,v1.getNext());
		assertEquals("Next must be null",null,v2.getNext());
		assertEquals("Has next must be true",true,v1.hasNext());
		assertEquals("Has next must be false",false,v2.hasNext());
	}
	/**
	 * Employee LinkedList Test
	 */
	@Test
	public void employeeLLTest() {
		int y= localDate.getYear();
		int m = localDate.getMonthValue();
		employeeLL eLL = new employeeLL();
		ENode e = new ENode(new Employee(y, m, 987, "Test",1284985621,85466,10,462100.85,59462.17),null);
		assertEquals("Is empty must be true",true,eLL.isEmpty());
		eLL.addEmployee(e);
		assertEquals("Is empty must be false",false,eLL.isEmpty());
		assertEquals("Size of list must be 1",1,eLL.getSize());
		assertEquals("Size of list with current month must be 1",1,eLL.monthSize(y, m));
		assertEquals("Existence must be true",true,eLL.exist(localDate.getYear(), localDate.getMonthValue()));
		assertEquals("ID contained must be true",true,eLL.containsID(987));
		assertEquals("Outcome must be Employee's data",eLL.getEmployee(987),eLL.getEmployee(987));
		assertEquals("Outcome must be performance report",eLL.viewPerformance(y, m),eLL.viewPerformance(y, m));
	}
	/**
	 * Vehicle LinkedList Test
	 */
	@Test
	public void vehicleLLTest() {
		vehicleLL vLL = new vehicleLL();
		VNode v2 = new VNode(new Economic(1111, "Make1", "Model1", "Color1", 111111, true),null);
		VNode v1 = new VNode(new Luxury(2222, "Make2", "Model2", "Color2", 222222, false),null);
		assertEquals("Is empty must be true",true,vLL.isEmpty());
		vLL.addVehicle(v1);
		vLL.addVehicle(v2);
		assertEquals("Is empty must be false",false,vLL.isEmpty());
		assertEquals("Size of list must be 2",2,vLL.getSize());
		assertEquals("ID contained must be true",true,vLL.containsID(1111));
		assertEquals("ID contained must be false",false,vLL.containsID(3333));
		assertEquals("Outcome must be list of IDs",vLL.IDList(),vLL.IDList());
	}
}
