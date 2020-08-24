package questionset;

import java.util.ArrayList;

import operations.Employee;
import operations.Question;

public class Q7Solution implements Question {

	// Q7 Sort two Employees using comparator by name, department, and age

	public ArrayList<Employee> compareEmployees(Employee em1, Employee em2) {
		ArrayList<Employee> empList = new ArrayList<Employee>();

		if (em1.compare(em1, em2) == 0) {
			empList.add(em1);
			empList.add(em2);
		} else {
			empList.add(em2);
			empList.add(em1);
		}

		return empList;
	}

	@Override
	public void performTask() {
		System.out.println("Comparing two employees and returning them in order (starting with last name order)...");
		Employee e1 = new Employee("Madison Hectare", "Technology", 22);
		Employee e2 = new Employee("Ziegfried Alton","Seasonal",34);
		System.out.println("Order of Employees: " + compareEmployees(e1,e2));

	}

}
