package humanResources;

import java.io.Serializable;

public class Department implements Serializable {
    public static final int CountOfElementsInArray = 8;
    private String name;
    private Employee[] employees;

    public Department() {
        this("", CountOfElementsInArray);
    }

    public Department(String name) {
        this(name, CountOfElementsInArray);
    }

    public Department(String name, int k) {
        this.name = name;
        employees = new Employee[k];
    }

    public Department(String name, Employee[] employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Employee employee) {
        int k = employees.length;
        boolean checkExistNull = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) checkExistNull = true;
        }
        if (checkExistNull) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = employee;
                    break;
                }
            }
        } else {
            int n = 0;
            Employee[] employees2 = new Employee[k * 2];
            System.arraycopy(employees, 0, employees2, 0, k);
            employees2[k] = employee;
            employees = employees2;
        }
    }

    public boolean Remove(String fName, String sName) {
        int k = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getFirstName() == fName && employees[i].getSecondName() == sName) {
                employees[i] = null;
                k++;
                for (int j = i; j < employees.length - 1; j++) {
                    if (employees[j + 1] != null) {
                        employees[j] = employees[j + 1];
                    } else {
                        employees[j] = null;
                    }
                }
            }
        }
        if (k == 1) return true;
        else return false;
    }

    public int Size() {
        return employees.length;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public Employee[] getEmployeesWithTitle(String jTitle) {
        int p = employees.length;
        int r = 0;
        Employee[] temp = new Employee[p];
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getJobTitle() == jTitle) {
                temp[r] = employees[i];
                r++;
            }
        }
        return temp;
    }

    public Employee[] ArraySortOnSalary(Employee[] employees) {
        Employee temp = new Employee();
        for (int j = 0; j < employees.length; j++) {
            for (int k = 0; k < employees.length - 1; k++) {
                if (employees[k].getSalary() > employees[k + 1].getSalary()) {
                    temp = employees[k];
                    employees[k] = employees[k + 1];
                    employees[k + 1] = temp;
                }
            }
        }
        return employees;
    }
}
