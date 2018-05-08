package humanResources;

import java.util.List;

public interface EmployeeGroup extends List<Employee>
{
    String getName();
    void setName(String name);
    boolean add(Employee employee);
    Employee getEmployee(String fName, String sName);
    void remove(String fName, String sName);
    boolean remove(Employee employee);
    Employee bestEmployee();
    int amountEmployees();
    int amountEmployeesByJobTitle(JobTitlesEnum jTitle);
    boolean hasEmployee(String fName, String sName);
    boolean hasEmployee(Employee employee);
    Employee[] getEmployees();
    Employee[] getSortedEmployeesBySalary();
    String toString();
    boolean equals(Object obj);
    int hashCode();
    int countPartTimeEmployees();
    int countStaffEmployees();
    int countBusinessTraveller();
    Employee[] getBusinessTravellers();
}
