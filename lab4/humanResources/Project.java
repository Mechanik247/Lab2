package humanResources;

public class Project implements EmployeeGroup
{
    private String name;
    private nodeList<Employee> employees;
    private int size;
    public Project(String name)
    {
        this.name = name;
        employees = new nodeList<Employee>();
        size = 0;
    }

    public Project(String name, Employee[] employees)
    {
        this.name = name;
        this.employees = new nodeList<Employee>(employees);
        size = this.employees.length();
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
    public void add(Employee employee)
    {
        if(hasEmployee(employee)) throw new AlreadyAddedException("Сотрудник уже есть");
        employees.add(employee);
        size++;
    }

    @Override
    public Employee getEmployee(String fName, String sName)
    {
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees.getItem(i);
            if(employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
            {
                return employees.getItem(i);
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
            employee = employees.getItem(i);
            if(employee.getFirstName().equals(fName) && employee.getSecondName().equals(sName))
            {
                employees.remove(i);
                size--;
            }
        }
    }

    @Override
    public void remove(Employee employee)
    {
        for(int i = 0; i < size; i++)
        {
            if(employee.equals(employees.getItem(i)))
            {
                employees.remove(employee);
                size--;
            }
        }
    }

    @Override
    public Employee bestEmployee()
    {
        double maxSalary = 0;
        int bestIndex = 0;
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees.getItem(i);
            if(employee.getSalary() > maxSalary)
            {
                maxSalary = employee.getSalary();
                bestIndex = i;
            }
        }
        return employees.getItem(bestIndex);
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
        Employee swapBuf;
        Employee[] tempSortedArray = getEmployees();
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size - 1 - j; k++) {
                if (tempSortedArray[k].getSalary() > tempSortedArray[k + 1].getSalary()) {
                    swapBuf = tempSortedArray[k];
                    tempSortedArray[k] = tempSortedArray[k + 1];
                    tempSortedArray[k + 1] = swapBuf;
                }
            }
        }
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
            employee = employees.getItem(i);
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
            employee = employees.getItem(i);
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
            if(employees.getItem(i).equals(employee))
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
            employee = employees.getItem(i);
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

        if(!(getClass() == obj.getClass()))
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
                    thisEmployee = employees.getItem(i);
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
            hashCode ^= employees.getItem(i).hashCode();
        }
        return hashCode;
    }

    @Override
    public int countPartTimeEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            if(employees.getItem(i) instanceof PartTimeEmployee)
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
            if(employees.getItem(i) instanceof StaffEmployee)
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
            if(employees.getItem(i) instanceof BusinessTraveller)
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
            if(this.employees.getItem(i) instanceof BusinessTraveller)
            {
                employees[index++] = this.employees.getItem(i);
            }
        }
        return employees;
    }
}
