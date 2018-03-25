package humanResources;

public class Department {
    //todo: такая же фигня как и в емплоях
    public static final int DEFAULT_SIZE_OF_ARRAY = 8;
    public static final String DEFAULT_NAME = "";
    private String name;
    private Employee[] employees;
    private int size;

    //todo: и здесь тоже
    public Department() {
        this(DEFAULT_NAME, DEFAULT_SIZE_OF_ARRAY);
    }

    public Department(String name) {
        this(name, DEFAULT_SIZE_OF_ARRAY);
    }

    //todo: к - тоже не лучшее имя для параметра
    public Department(String name, int size) {
        this.name = name;
        employees = new Employee[size];
        this.size=0;
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

    //todo: refact
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
        int sizeEmployees = employees.length;
        for (int i = 0; i < sizeEmployees; i++) {
            if (employees[i] != null && employees[i].getFirstName().equals(fName) && employees[i].getSecondName().equals(sName)) {
                employees[i] = null;
                System.arraycopy(employees, i + 1, employees, i, sizeEmployees - (i + 1));
                employees[sizeEmployees - 1] = null;
                size--;
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
        int lengthArray = employees.length;
        int tempSize = 0;
        Employee[] temp = new Employee[lengthArray];
        for (int i = 0; i < lengthArray; i++) {
            if (employees[i].getJobTitle().equals(jTitle)) {
                temp[tempSize] = employees[i];
                tempSize++;
            }
        }
        return temp;
    }

    public Employee[] getSortedEmployeesBySalary() {
        Employee temp = new Employee();
        Employee[] tempSortedArray = new Employee[size];
        System.arraycopy(employees, 0, tempSortedArray, 0, size);
        for (int j = 0; j < tempSortedArray.length; j++) {
            for (int k = 0; k < tempSortedArray.length - 1; k++) {
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
