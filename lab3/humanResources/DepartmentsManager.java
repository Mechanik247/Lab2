package humanResources;

public class DepartmentsManager implements GroupsManager{
    private static final int DEFAULT_CAPACITY = 8;
    private static final String DEFAULT_NAME = "";
    private String name;
    private EmployeeGroup[] employeeGroups;
    private int size;

    public DepartmentsManager() {
        this(DEFAULT_NAME, DEFAULT_CAPACITY);
    }



    public DepartmentsManager(String name) {
        this(name, DEFAULT_CAPACITY);
    }

    public DepartmentsManager(String name, int size) {
        this.name = name;
        employeeGroups = new EmployeeGroup[size];
        this.size = 0;
    }

    public DepartmentsManager(String name, EmployeeGroup[] departmentsNew) {
        this.name = name;
        EmployeeGroup[] employeeGroups = new EmployeeGroup[departmentsNew.length];
        System.arraycopy(departmentsNew, 0, employeeGroups, 0, departmentsNew.length);
        this.employeeGroups = employeeGroups;
        this.size = employeeGroups.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add(EmployeeGroup employeeGroup) {
        if (size == employeeGroups.length) {
            EmployeeGroup[] employeeGroups = new Department[size * 2];
            System.arraycopy(this.employeeGroups, 0, employeeGroups, 0, size);
            this.employeeGroups = employeeGroups;
        }
        employeeGroups[size] = employeeGroup;
        size++;
    }

    @Override
    public boolean remove(String name) {
        for (int i = 0; i < size; i++) {
            if (employeeGroups[i].getName().equals(name)) {
                System.arraycopy(employeeGroups, i + 1, employeeGroups, i, size - (i + 1));
                employeeGroups[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int remove(EmployeeGroup employeeGroup)
    {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (employeeGroups[i].equals(employeeGroup)) {
                System.arraycopy(employeeGroups, i + 1, employeeGroups, i, size - (i + 1));
                employeeGroups[size - 1] = null;
                count++;
                size--;
            }
        }
        return count;
    }

    @Override
    public EmployeeGroup getGroup(String name) {
        for (int i = 0; i < size; i++) {
            if (employeeGroups[i].getName().equals(name)) {
                return employeeGroups[i];
            }
        }
        return null;
    }

    @Override
    public EmployeeGroup[] getEmployeeGroups()
    {
        EmployeeGroup[] employeeGroups = new EmployeeGroup[size];
        System.arraycopy(this.employeeGroups, 0, employeeGroups, 0, size);
        return employeeGroups;
    }

    @Override
    public int amountGroups() {
        return size;
    }

    @Override
    public int amountEmployees() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += employeeGroups[i].amountEmployees();
        }
        return count;
    }

    @Override
    public int amountEmployeesByJobTitle(JobTitlesEnum jTitle) {
        int amount = 0;
        EmployeeGroup employeeGroup;
        for (int i = 0; i < size; i++) {
            employeeGroup = employeeGroups[i];
            amount += employeeGroup.amountEmployeesByJobTitle(jTitle);
        }
        return amount;
    }

    @Override
    public Employee bestEmployee() {

        double maxSalary = 0;
        int bestIndex = 0;
        Employee employee;
        EmployeeGroup department;
        for (int i = 0; i < size; i++) {
            department = employeeGroups[i];
            employee = department.bestEmployee();
            if (employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
                bestIndex = i;
            }
        }
        return employeeGroups[bestIndex].bestEmployee();
    }

    @Override
    public EmployeeGroup getGroupOfAnEmployee(String fName, String sName) {
        EmployeeGroup employeeGroup;
        for (int i = 0; i < size; i++)
        {
            employeeGroup = employeeGroups[i];
            if(employeeGroup.hasEmployee(fName, sName))
            {
                return employeeGroup;
            }
        }
        return null;
    }
}