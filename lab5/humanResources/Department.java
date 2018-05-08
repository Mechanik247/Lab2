package humanResources;

import java.util.*;

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
    public boolean add(Employee employee) {
        if(hasEmployee(employee)) throw new AlreadyAddedException("Сотрудник уже есть");
        if(size == employees.length) {
            Employee[] employeesTemp = new Employee[size * 2];
            System.arraycopy(employees, 0, employeesTemp, 0, size);
            employees = employeesTemp;
        }
        employees[size] = employee;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return remove((Employee) o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Arrays.stream(getEmployees()).allMatch(c::contains);
    }

    public void addLast(Employee employee) throws AlreadyAddedException {

        if (this.contains(employee)) {
            throw new AlreadyAddedException("Employee already added");
        }

        if (size == employees.length) {
            Employee[] employeesBuffer = new Employee[size * 2];
            System.arraycopy(employees, 0, employeesBuffer, 0, size);
            employees = employeesBuffer;
        }
        employees[size++] = employee;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        for (Object o : c) {
            try {
                this.addLast((Employee) o);
            } catch (AlreadyAddedException e) {
                System.out.println(e.getMessage());
            }
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        int newCapacity = employees.length;
        int i = index;

        while (size + c.size() >= newCapacity) {
            newCapacity *= 2;
        }

        Employee[] newEmps = new Employee[newCapacity];
        System.arraycopy(employees, 0, newEmps, 0, size);
        System.arraycopy(newEmps, index, newEmps, index + c.size(), size - index);
        Employee[] cArray = new Employee[c.size()];
        cArray = c.toArray(cArray);
        int j = 0;

        for (i = index; i < c.size(); i++) {
            newEmps[i] = cArray[j++];
        }

        employees = newEmps;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (Object o : c) {
            if (this.contains(o)) {
                remove(o);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (Employee e : employees) {
            if (e != null && !c.contains(e)) {
                this.remove(e);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        size = 0;
        employees = new Employee[DEFAULT_CAPACITY];
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
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    public boolean contains(Employee employee) {
        for (Employee e : getEmployees()) {
            if (e.equals(employee)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Employee> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Employee employee)
    {
        for (int i = 0; i < size; i++) {
            if (employees[i].equals(employee))
            {
                System.arraycopy(employees, i + 1, employees, i, size - (i + 1));
                employees[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
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
        for (Employee employee: employees) {
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
        for(Employee employee: employees)
        {
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
        Employee[] tempSortedArray = getEmployees();
        Arrays.sort(tempSortedArray, Employee::compareTo);
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
    public Employee get(int index) {
        return employees[index];
    }

    @Override
    public Employee set(int index, Employee element) {
        Employee e = employees[index];
        employees[index] = element;
        return e;
    }

    @Override
    public void add(int index, Employee element) {
        if (size == employees.length) {
            Employee[] newEmps = new Employee[size * 2];
            System.arraycopy(employees, 0, newEmps, 0, size);
            employees = newEmps;
        }

        System.arraycopy(employees, index, employees, index + 1, size - index);
        size++;
    }

    @Override
    public Employee remove(int index) {
        Employee e = employees[index];
        System.arraycopy(employees, index + 1, employees, index, size - index - 1);
        size--;

        return e;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (employees[i] != null) {
                if (employees[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int last = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i] != null) {
                if (employees[i].equals(o)) {
                    last = i;
                }
            }
        }

        return last;
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return new ListIterator<Employee>() {

            int i = index;

            @Override
            public boolean hasNext() {
                return i < size - 1;
            }

            @Override
            public Employee next() {
                return employees[i++];
            }

            @Override
            public boolean hasPrevious() {
                return i > 0;
            }

            @Override
            public Employee previous() {
                return employees[i--];
            }

            @Override
            public int nextIndex() {
                return i;
            }

            @Override
            public int previousIndex() {
                return i - 1;
            }

            @Override
            public void remove() {
                Department.this.remove(i);
            }


            public void set(Employee employee) {
                employees[i] = employee;
            }

            public void add(Employee employee) {
                Department.this.add(i, employee);
            }
        };
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        LinkedList<Employee> result = new LinkedList<>();
        result.addAll(Arrays.asList(employees).subList(fromIndex, toIndex));
        return result;
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