package humanResources;

public class ProjectsManager implements GroupsManager
{
    private nodeList<EmployeeGroup> groupNodeList;
    private int size;

    public ProjectsManager()
    {
        groupNodeList = new nodeList<EmployeeGroup>();
        size = 0;
    }

    public ProjectsManager(EmployeeGroup[] employeeGroups)
    {
        groupNodeList = new nodeList<EmployeeGroup>(employeeGroups);
        size = groupNodeList.length();
    }

    @Override
    public void add(EmployeeGroup employeeGroup)
    {
        if(hasGroup(employeeGroup)) throw new AlreadyAddedException("Группа уже есть");
        groupNodeList.add(employeeGroup);
        size++;
    }

    @Override
    public int amountGroups()
    {
        return size;
    }

    @Override
    public int remove(EmployeeGroup employeeGroup)
    {
        EmployeeGroup group;
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            group = groupNodeList.getItem(i);
            if(group.equals(employeeGroup))
            {
                groupNodeList.remove(i);
                count++;
                size--;
            }
        }
        return count;
    }

    @Override
    public boolean remove(String name)
    {
        EmployeeGroup group;
        for(int i = 0; i < size; i++)
        {
            group = groupNodeList.getItem(i);
            if(group.getName().equals(name))
            {
                groupNodeList.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasGroup(EmployeeGroup employeeGroup)
    {
        for(int i = 0; i < size; i++)
        {
            if(groupNodeList.getItem(i).equals(employeeGroup))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public EmployeeGroup getGroup(String name)
    {
        EmployeeGroup group;
        for(int i = 0; i < size; i++)
        {
            group = groupNodeList.getItem(i);
            if(group.getName().equals(name))
            {
                return groupNodeList.getItem(i);
            }
        }
        return null;
    }

    @Override
    public EmployeeGroup[] getEmployeeGroups()
    {
        EmployeeGroup[] employeeGroups = new EmployeeGroup[size];
        groupNodeList.toArray(employeeGroups);
        return employeeGroups;
    }



    @Override
    public int amountEmployees()
    {
        int amount = 0;
        EmployeeGroup employeeGroup;
        for(int i = 0; i < size; i++)
        {
            employeeGroup = groupNodeList.getItem(i);
            amount += employeeGroup.amountEmployees();
        }
        return amount;
    }

    @Override
    public int amountEmployeesByJobTitle(JobTitlesEnum title)
    {
        int amount = 0;
        EmployeeGroup employeeGroup;
        for(int i = 0; i < size; i++)
        {
            employeeGroup = groupNodeList.getItem(i);
            amount += employeeGroup.amountEmployeesByJobTitle(title);
        }
        return amount;
    }

    @Override
    public Employee bestEmployee()
    {
        double maxSalary = 0;
        Employee employee, bestEmployee = null;
        EmployeeGroup employeeGroup;
        for (int i = 0; i < size; i++) {
            employeeGroup = groupNodeList.getItem(i);
            employee = employeeGroup.bestEmployee();
            if (employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
                bestEmployee = employee;
            }
        }
        return bestEmployee;
    }

    @Override
    public EmployeeGroup getGroupOfAnEmployee(String fName, String sName)
    {
        EmployeeGroup employeeGroup;
        for (int i = 0; i < size; i++) {
            employeeGroup = groupNodeList.getItem(i);
            if(employeeGroup.hasEmployee(fName,sName))
            {
                return groupNodeList.getItem(i);
            }
        }
        return null;
    }

    @Override
    public int countPartTimeEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += groupNodeList.getItem(i).countPartTimeEmployees();
        }
        return count;
    }

    @Override
    public int countStaffEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += groupNodeList.getItem(i).countStaffEmployees();
        }
        return count;
    }

    @Override
    public int countBusinessTraveller() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += groupNodeList.getItem(i).countBusinessTraveller();
        }
        return count;
    }

    @Override
    public Employee[] getBusinessTravellers() {
        int index = 0;
        Employee[] businessTravellers = new Employee[countBusinessTraveller()];
        for(int i = 0; i < size; i++)
        {
            Employee[] employees = groupNodeList.getItem(i).getBusinessTravellers();
            System.arraycopy(employees, 0, businessTravellers, index, employees.length);
            index += employees.length;
        }
        return businessTravellers;
    }
}
