package humanResources;

public class Organization {
    private static final int DEFAULT_SIZE_OF_ARRAY = 8;
    public static final int DEFAULT_SIZE = 0;
    private static final String DEFAULT_NAME = "";
    private String name;
    private Department[] departments;
    private int size;

    public Organization() {
        this(DEFAULT_NAME, DEFAULT_SIZE_OF_ARRAY);
    }



    public Organization(String name) {
        this(name, DEFAULT_SIZE_OF_ARRAY);
    }

    public Organization(String name, int size) {
        this.name = name;
        departments = new Department[size];
        this.size = DEFAULT_SIZE;
    }

    public Organization(String name, Department[] departments) {
        this.name = name;
        this.departments = departments;
        this.size = departments.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Department dep) {
        if(size == departments.length)
        {
            Department[] departmentsTemp = new Department[size * 2];
            System.arraycopy(departments, 0, departmentsTemp, 0, size);
            departments = departmentsTemp;
            departments[size] = dep;
            size++;
        }
        else {
            departments[size] = dep;
            size++;
        }
    }

    public void removeDep(String name) {
            for (int i = 0; i < size; i++) {
                if (departments[i].getName().equals(name)) {
                    departments[i] = null;
                    System.arraycopy(departments, i + 1, departments, i, size - (i + 1));
                    departments[size - 1] = null;
                    size--;
                }
            }
    }

    public Department getDepartment(String name) {
        for (int i = 0; i < size; i++) {
            if (departments[i].getName().equals(name)) {
                return departments[i];
            }
        }
        return null;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public int amountDepartments() {
        return size;
    }

    public int countAllEmployees() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += departments[i].size();
        }
        return count;
    }

    public int amountEmployeesByJobTitle(String jTitle) {
        int amount = 0;
        Employee employee;
        Department department;
        for (int i = 0; i < size; i++) {
            department = departments[i];
            for (int j = 0; j < department.size(); j++) {
                employee = department.getEmployees()[j];
                if (employee.getJobTitle().equals(jTitle)) {
                    amount++;
                }
            }
        }
        return amount;
    }

    public Employee bestEmployee() {
        int maxSalary = 0;
        Employee employee, employee1 = new Employee();
        Department department;
        for (int i = 0; i < size; i++) {
            department = departments[i];
            for (int j = 0; j < department.size(); j++) {
                employee = department.getEmployees()[j];
                if (employee.getSalary() > maxSalary) {
                    maxSalary = employee.getSalary();
                    employee1 = employee;
                }
            }
        }
        return employee1;
    }

    public Department getDepartmentOfAnEmployee(String fName, String sName) {
        Employee employee;
        Department department;
        for (int i = 0; i < size; i++)
        {
            department = departments[i];
                for (int j = 0; j < department.size(); j++)
                {
                    employee = department.getEmployees()[j];
                    if (employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
                    {
                        return departments[i];
                    }
                }
        }
        return null;
    }
}
