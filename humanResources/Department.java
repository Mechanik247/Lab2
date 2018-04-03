package humanResources;

public class Department {
    public static final int DEFAULT_SIZE_OF_ARRAY = 8;
    public static final int DEFAULT_SIZE = 0;
    public static final String DEFAULT_NAME = "";
    private String name;
    private Employee[] employees;
    private int size;

    public Department() {
        this(DEFAULT_NAME, DEFAULT_SIZE_OF_ARRAY);
    }

    public Department(String name) {
        this(name, DEFAULT_SIZE_OF_ARRAY);
    }

    public Department(String name, int size) {
        this.name = name;
        employees = new Employee[size];
        this.size = DEFAULT_SIZE;
    }

    public Department(String name, Employee[] employees) {
        this.name = name;
        this.employees = employees;
        this.size = employees.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Employee employee) {
        if(size == employees.length)
        {
            Employee[] employeesTemp = new Employee[size * 2];
            System.arraycopy(employees, 0, employeesTemp, 0, size);
            employees = employeesTemp;
            employees[size] = employee;
            size++;
        }
        else {
            employees[size] = employee;
            size++;
        }
    }

    public boolean remove(String fName, String sName) {
        for (int i = 0; i < this.size; i++) {
            if (employees[i].getFirstName().equals(fName) && employees[i].getSecondName().equals(sName)) {
                employees[i] = null;
                System.arraycopy(employees, i + 1, employees, i, size - (i + 1));
                employees[size - 1] = null;
                this.size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public Employee[] getEmployees() {
        Employee[] employeesTemp = new Employee[size];
        System.arraycopy(employees, 0, employeesTemp, 0, size);
        return employeesTemp;
    }

    public Employee[] getEmployeesByTitle(String jTitle) {
        int size1 = 0;
        Employee[] employees1 = new Employee[size];
        for (int i = 0; i < size; i++) {
            if (employees[i].getJobTitle().equals(jTitle)) {
                employees1[size1] = employees[i];
                size1++;
            }
        }
        Employee[] employees2 = new Employee[size1];
        System.arraycopy(employees1, 0, employees2, 0, size1);
        return employees2;
    }

    public Employee[] getSortedEmployeesBySalary() {
        Employee temp;
        Employee[] tempSortedArray = new Employee[size];
        System.arraycopy(employees, 0, tempSortedArray, 0, size);
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size - 1; k++) {
                if (tempSortedArray[k].getSalary() > tempSortedArray[k + 1].getSalary()) {
                    temp = tempSortedArray[k];
                    tempSortedArray[k] = tempSortedArray[k + 1];
                    tempSortedArray[k + 1] = temp;
                }
            }
        }
        return tempSortedArray;
    }
}
