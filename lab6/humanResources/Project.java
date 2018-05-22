package humanResources;

import java.io.Serializable;
import java.util.*;

public class Project implements EmployeeGroup, Serializable
{
    private String name;
    private nodeList<Employee> employees;
    private int size;
    public Project(String name)
    {
        this.name = name;
        employees = new nodeList<>();
        size = 0;
    }

    public Project(String name, Employee[] employees)
    {
        this.name = name;
        this.employees = new nodeList<>(employees);
        size = this.employees.size();
    }

    public Project() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int size() {
        return employees.size();
    }

    @Override
    public boolean isEmpty() {
        return employees.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return employees.contains(o);
    }

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }

    @Override
    public Object[] toArray() {
        return employees.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return employees.toArray(a);
    }

    @Override
    public boolean add(Employee employee)
    {
        if(hasEmployee(employee)) throw new AlreadyAddedException("Сотрудник уже есть");
        employees.add(employee);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Employee employee = (Employee) o;
        for(int i = 0; i < size; i++)
        {
            if(employee.equals(employees.get(i)))
            {
                employees.remove(employee);
                size--;
                return true;
            }
        }
        return false;
    }

    public void addLast(Employee e) throws AlreadyAddedException {

        if (employees.contains(e)) {
            throw new AlreadyAddedException("Employee already added");
        }

        this.add(e);

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return employees.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        return employees.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        return addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return employees.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return employees.retainAll(c);
    }

    @Override
    public void clear() {
        employees.clear();
    }

    @Override
    public Employee getEmployee(String fName, String sName)
    {
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees.get(i);
            if(employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
            {
                return employees.get(i);
            }
        }
        return null;
    }

    @Override
    public void remove(String fName, String sName)
    {
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees.get(i);
            if(employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
            {
                employees.remove(i);
                size--;
            }
        }
    }

    @Override
    public boolean remove(Employee employee)
    {
        for(int i = 0; i < size; i++)
        {
            if(employee.equals(employees.get(i)))
            {
                employees.remove(employee);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee bestEmployee()
    {
        double maxSalary = 0;
        int bestIndex = 0;
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees.get(i);
            if(employee.getSalary() > maxSalary)
            {
                maxSalary = employee.getSalary();
                bestIndex = i;
            }
        }
        return employees.get(bestIndex);
    }

    @Override
    public Employee[] getEmployees()
    {
        Employee[] employees = new Employee[size];
        this.employees.toArray(employees);
        return employees;
    }

    @Override
    public Employee[] getSortedEmployeesBySalary() {
        Employee[] tempSortedArray = getEmployees();
        Arrays.sort(tempSortedArray, Employee::compareTo);
        return tempSortedArray;
    }

    @Override
    public int amountEmployees() {
        return size;
    }

    @Override
    public int amountEmployeesByJobTitle(JobTitlesEnum jTitle)
    {
        int amount = 0;
        Employee employee;
        for (int i = 0; i < size; i++) {
            employee = employees.get(i);
            if (employee.getJobTitle().equals(jTitle)) {
                amount++;
            }
        }
        return amount;
    }

    @Override
    public boolean hasEmployee(String fName, String sName)
    {
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees.get(i);
            if(employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasEmployee(Employee employee)
    {
        for(int i = 0; i < size; i++)
        {
            if(employees.get(i).equals(employee))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        Employee employee;
        StringBuilder str = new StringBuilder("");
        str.append("Project ").append(name).append(": ").append(size).append("\n");
        for(int i = 0; i < size; i++)
        {
            employee = employees.get(i);
            str.append(employee.toString()).append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(this instanceof Project && obj instanceof Project))
            return false;
        else
        {
            Project project = (Project) obj;
            Employee thisEmployee, objEmployee;
            int checkSize = 0;
            if(this.name.equals(project.getName()) && this.size == project.amountEmployees())
            {
                for(int i = 0; i < size; i++)
                {
                    thisEmployee = employees.get(i);
                    for (int j = 0; j < size; j++)
                    {
                        objEmployee = project.getEmployees()[j];
                        if(thisEmployee.equals(objEmployee))
                        {
                            checkSize++;
                            break;
                        }
                    }
                }
                if(checkSize == size) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else
                return false;
        }
    }

    @Override
    public int hashCode() {
        int hashCode = 17, hashName, hashSize;
        hashName = 37 * hashCode + (this.name.equals("") ? 0 : this.name.hashCode());
        hashSize = 37 * hashCode + this.size;
        hashCode = hashName ^ hashSize;
        for(int i = 0; i < size; i++)
        {
            hashCode ^= employees.get(i).hashCode();
        }
        return hashCode;
    }

    @Override
    public Employee get(int index) {
        return employees.get(index);
    }

    @Override
    public Employee set(int index, Employee element) {
        return employees.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        employees.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        return employees.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return employees.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return employees.lastIndexOf(o);
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return employees.listIterator();
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return employees.listIterator(index);
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        return employees.subList(fromIndex, toIndex);
    }

    @Override
    public int countPartTimeEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            if(employees.get(i) instanceof PartTimeEmployee)
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countStaffEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            if(employees.get(i) instanceof StaffEmployee)
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countBusinessTraveller() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            if(employees.get(i) instanceof BusinessTraveller)
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public Employee[] getBusinessTravellers() {
        int index = 0;
        Employee[] employees = new Employee[countBusinessTraveller()];
        for(int i = 0; i < size; i++)
        {
            if(this.employees.get(i) instanceof BusinessTraveller)
            {
                employees[index++] = this.employees.get(i);
            }
        }
        return employees;
    }
}
