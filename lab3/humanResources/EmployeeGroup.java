package humanResources;

public interface EmployeeGroup
{
    String getName();
    void setName(String name);
    void add(Employee employee);
    Employee getEmployee(String fName, String sName);
    void remove(String fName, String sName);
    void remove(Employee employee);
    Employee bestEmployee();
    int amountEmployees();
    int amountEmployeesByJobTitle(JobTitlesEnum jTitle);
    boolean hasEmployee(String fName, String sName);
    Employee[] getEmployees();
    Employee[] getSortedEmployeesBySalary();
    String toString();
    boolean equals(Object obj);
    int hashCode();
}
