package humanResources;

public class Department implements EmployeeGroup {
    public static final int DEFAULT_CAPACITY = 8;
    public static final String DEFAULT_NAME = "";
    private String name;
    private Employee[] employees;
    private int size;

    public Department() {
        this(DEFAULT_NAME, DEFAULT_CAPACITY);
    }

    public Department(String name) {
        this(name, DEFAULT_CAPACITY);
    }

    public Department(String name, int size) {
        if(size < 0) throw new NegativeSizeException();
        this.name = name;
        employees = new Employee[size];
        this.size = 0;
    }

    public Department(String name, Employee[] employeesNew) {
        this.name = name;
        Employee[] employees = new Employee[employeesNew.length];
        System.arraycopy(employeesNew, 0, employees, 0, employeesNew.length);
        this.employees = employees;
        this.size = employees.length;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add(Employee employee) {
        if(hasEmployee(employee)) throw new AlreadyAddedException("Сотрудник уже есть");
        if(size == employees.length) {
            Employee[] employeesTemp = new Employee[size * 2];
            System.arraycopy(employees, 0, employeesTemp, 0, size);
            employees = employeesTemp;
        }
        employees[size] = employee;
        size++;
    }

    @Override
    public void remove(String fName, String sName) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getFirstName().equals(fName) && employees[i].getSecondName().equals(sName)) {
                System.arraycopy(employees, i + 1, employees, i, size - (i + 1));
                employees[size - 1] = null;
                size--;
            }
        }
    }

    public int size() {
        return this.size;
    }

    @Override
    public void remove(Employee employee)
    {
        for (int i = 0; i < size; i++) {
            if (employees[i].equals(employee))
            {
                System.arraycopy(employees, i + 1, employees, i, size - (i + 1));
                employees[size - 1] = null;
                size--;
            }
        }
    }

    public void removeByTitle(JobTitlesEnum title)
    {
        for(int i = 0; i < size; i++)
        {
            if(employees[i].getJobTitle() == title)
            {
                System.arraycopy(employees, i + 1, employees, i, size - (i + 1));
                employees[size - 1] = null;
                size--;
            }
        }
    }

    @Override
    public Employee getEmployee(String firstName, String secondName)
    {
        for(int i = 0; i < size; i++) {

            if (employees[i].getFirstName().equals(firstName) && employees[i].getSecondName().equals(secondName)) {
                return employees[i];
            }
        }
        return null;
    }

    public JobTitlesEnum[] getTitles()
    {
        int sizeTitles = 0;
        boolean check;
        JobTitlesEnum[] titles = new JobTitlesEnum[size];
        for(int i = 0; i < size; i++)
        {
            check = true;
            for(int j = 0; j < size; j++)
            {
                if(titles[j] != null && titles[j] == employees[i].getJobTitle())
                {
                    check = false;
                }
            }
            if(check)
            {
                titles[sizeTitles++] = employees[i].getJobTitle();
            }
        }
        JobTitlesEnum[] titlesReturn = new JobTitlesEnum[sizeTitles];
        System.arraycopy(titles, 0, titlesReturn, 0, sizeTitles);
        return titlesReturn;
    }

    @Override
    public Employee bestEmployee() {
        double maxSalary = 0;
        int bestIndex = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].getSalary() > maxSalary) {
                maxSalary = employees[i].getSalary();
                bestIndex = i;
            }
        }
        return employees[bestIndex];
    }



    public Employee[] getEmployyesByBusinessTravel()
    {
        Employee[] employees1 = new Employee[size];
        int size1 = 0;
        for(int i = 0; i < size; i++)
        {
            if(employees[i] instanceof BusinessTraveller)
            {
                employees1[size1++] = employees[i];
            }
        }
        Employee[] employeesBusinessTraveller = new Employee[size1];
        System.arraycopy(employees1, 0, employeesBusinessTraveller, 0, size1);
        return employeesBusinessTraveller;
    }

    @Override
    public Employee[] getEmployees() {
        Employee[] employees = new Employee[size];
        System.arraycopy(this.employees, 0, employees, 0, size);
        return employees; //TODO ВОзвращай копию - fixed
    }

    public Employee[] getEmployeesByTitle(JobTitlesEnum jTitle) {
        //todo измени имена employees1 employees2 - fixed
        int employeesCount = 0;
        Employee[] employees = new Employee[amountEmployeesByJobTitle(jTitle)];
        for (int i = 0; i < size; i++) {
            if (this.employees[i].getJobTitle().equals(jTitle)) {
                employees[employeesCount] = this.employees[i];
                employeesCount++;
            }
        }
        return employees;
    }

    @Override
    public int amountEmployeesByJobTitle(JobTitlesEnum jTitle)
    {
        int amount = 0;
        Employee employee;
        for (int i = 0; i < size; i++) {
            employee = employees[i];
            if (employee.getJobTitle().equals(jTitle)) {
                amount++;
            }
        }
        return amount;
    }

    @Override
    public int amountEmployees() {
        return size;
    }

    @Override
    public boolean hasEmployee(String fName, String sName)
    {
        Employee employee;
        for(int i = 0; i < size; i++)
        {
            employee = employees[i];
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
            if(employees[i].equals(employee))
            {
                return true;
            }
        }
        return false;
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
    public String toString()
    {
        StringBuilder str = new StringBuilder("");
        str.append("Department ").append(name).append(": ").append(size).append("\n");
        for(int i = 0; i < size; i++)
        {
            str.append(employees[i].toString()).append("\n");
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
            Department department = (Department) obj;
            Employee thisEmployee, objEmployee;
            int checkSize = 0;
            if(this.name.equals(department.getName()) && this.size == department.getSize())
            {
                for(int i = 0; i < size; i++)
                {
                    thisEmployee = employees[i];
                    for (int j = 0; j < size; j++)
                    {
                        objEmployee = department.getEmployees()[j];
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
            hashCode ^= employees[i].hashCode();
        }
        return hashCode;
    }

    @Override
    public int countPartTimeEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            if(employees[i] instanceof PartTimeEmployee)
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
            if(employees[i] instanceof StaffEmployee)
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
            if(employees[i] instanceof BusinessTraveller)
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
            if(this.employees[i] instanceof BusinessTraveller)
            {
                employees[index++] = this.employees[i];
            }
        }
        return employees;
    }
}