package humanResources;

public class Organization {
    public static final int DEFAULT_SIZE_OF_ARRAY = 8;
    public static final String DEFAULT_NAME = "";
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
        this.size = 0;
    }

    public Organization(String name, Department[] departments) {
        this.name = name;
        this.departments = departments;
        this.size = 0;
        for(int i = 0; i < departments.length; i++)
        {
            if (departments[i] != null) this.size++;
        }
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

    public void RemoveDep(String name) {
        int k = departments.length;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null && departments[i].getName().equals(name)) {
                departments[i] = null;
                System.arraycopy(departments, i + 1, departments, i, k - (i + 1));
                departments[k - 1] = null;
            }
        }
    }

    public Department getDepartment(String name) {
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null && departments[i].getName().equals(name)) {
                return departments[i];
            }
        }
        return null;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public int amountDepartments() {
        int size = 0;
        for (int i = 0; i < departments.length; i++)
        {
            if (departments[i] != null) size++;
        }
        return size;
    }

    public int amountEmployeesInOrganization() {
        int amount = 0;
        for (int i = 0; i < departments.length; i++)
        {
            if (departments[i] != null)
            {
                for (int j = 0; j < departments[i].getEmployees().length; j++)
                {
                    if (departments[i].getEmployees()[j] != null)
                    {
                        amount++;
                    }
                }
            }
        }
        return amount;
    }

    public int getAmountEmployeesByJobTitle(String jTitle) {
        int amount = 0;
        Employee employee;
        for (int i = 0; i < departments.length; i++)
        {
            if (departments[i] != null)
            {
                for (int j = 0; j < departments[i].getEmployees().length; j++)
                {
                    employee = departments[i].getEmployees()[j];
                    if (employee != null && employee.getJobTitle().equals(jTitle))
                    {
                        amount++;
                    }
                }
            }
        }
        return amount;
    }

    public Employee bestEmployee() {
        int maxSalary = 0;
        Employee employee, employee1 = new Employee();
        for (int i = 0; i < departments.length; i++)
        {
            if (departments[i] != null)
            {
                for (int j = 0; j < departments[i].getEmployees().length; j++)
                {
                    employee = departments[i].getEmployees()[j];
                    if (employee != null && employee.getSalary() > maxSalary)
                    {
                        maxSalary = employee.getSalary();
                        employee1 = employee;
                    }
                }
            }
        }
        return employee1;
    }

    public Department departmentHaveEmploy(String fName, String sName) {
        Employee employee;
        for (int i = 0; i < departments.length; i++)
        {
            if (departments[i] != null)
            {
                for (int j = 0; j < departments[i].getEmployees().length; j++)
                {
                    employee = departments[i].getEmployees()[j];
                    if (employee != null && employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
                    {
                        return departments[i];
                    }
                }
            }
        }
        return null;
    }
}
