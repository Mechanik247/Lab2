package humanResources;

public interface GroupsManager
{
    void add(EmployeeGroup employeeGroup);
    int remove(EmployeeGroup employeeGroup);
    boolean remove(String name);
    EmployeeGroup getGroup(String name);
    EmployeeGroup[] getEmployeeGroups();
    int amountGroups();
    int amountEmployees();
    int amountEmployeesByJobTitle(JobTitlesEnum jTitle);
    Employee bestEmployee();
    EmployeeGroup getGroupOfAnEmployee(String fName, String sName);
}
