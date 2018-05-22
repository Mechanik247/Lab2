package humanResources;

import java.util.List;

public interface GroupsManager extends List<EmployeeGroup>
{
    boolean add(EmployeeGroup employeeGroup);
    int remove(EmployeeGroup employeeGroup);
    boolean remove(String name);
    boolean hasGroup(EmployeeGroup employeeGroup);
    EmployeeGroup getGroup(String name);
    EmployeeGroup[] getEmployeeGroups();
    int amountGroups();
    int amountEmployees();
    int amountEmployeesByJobTitle(JobTitlesEnum jTitle);
    Employee bestEmployee();
    EmployeeGroup getGroupOfAnEmployee(String fName, String sName);
    int countPartTimeEmployees();
    int countStaffEmployees();
    int countBusinessTraveller();
    Employee[] getBusinessTravellers();
}
