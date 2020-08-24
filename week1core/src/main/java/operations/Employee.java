package operations;

import java.util.Comparator;

public class Employee implements Comparator {
	private String name, department;
	private int age;

	public Employee() {
		name = "Ashen One";
		department = "Firelink";
		age = 461;
	}

	public Employee(String name, String dept, int age) {
		this.name = name;
		this.department = dept;
		this.age = age;
	}

	public int compare(Object arg0, Object arg1) {
		Employee e1 = (Employee) arg0;
		Employee e2 = (Employee) arg1;

		if (e1.getName().equals(e2.getName())) {
			if (e1.getDepartment().equals(e2.getDepartment())) {
				if (e1.getAge() == e2.getAge()) {
					return 0;
				} else {
					if (e1.getAge() < e2.getAge()) {
						return 0;
					} else {
						return 1;
					}
				}
			} else {
				for (int i = 0; i < e1.getDepartment().length(); i++) {
					if (e1.getDepartment().charAt(i) < e2.getDepartment().charAt(i))
						return 0;
				}
				return 1;
			}
		} else {
			String[] name1 = e1.getName().split(" ");
			String[] name2 = e2.getName().split(" ");

			for (int i = 0; i < name1[0].length(); i++) {
				if(name1[1].charAt(i) == name2[1].charAt(i)) continue;
				if (name1[1].charAt(i) < name2[1].charAt(i)) {
					return 0;
				} else {
					return 1;
				}
			}
			if (name1[0].equals(name2[0])) {
				for (int i = 0; i < name1[0].length(); i++) {
					if(name1[0].charAt(i) == name2[0].charAt(i)) continue;
					if (name1[0].charAt(i) < name2[0].charAt(i)) {
						return 0;
					} else {
						return 1;
					}
				}
			} else {
				for (int i = 0; i < name1[0].length(); i++) {
					if(name1[i].charAt(i) == name2[i].charAt(i)) continue;
					if (name1[i].charAt(i) < name2[i].charAt(i)) {
						return 0;
					} else {
						return 1;
					}
				}
			}
		}

		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		return "Name: " + name + "\nDepartment: " + department + "\nAge: " + age;
		
	}
}