package humanResources;

import java.util.*;

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
        if(size < 0) throw new NegativeSizeException();
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

    public void addLast(EmployeeGroup group) throws AlreadyAddedException {
        if (this.contains(group)) {
            throw new AlreadyAddedException("Department already added");
        }

        if (size == employeeGroups.length) {
            EmployeeGroup[] temp = new EmployeeGroup[employeeGroups.length * 2];
            System.arraycopy(employeeGroups, 0, temp, 0, employeeGroups.length);
            employeeGroups = temp;
        }
        employeeGroups[size++] = group;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.stream(employeeGroups).filter(Objects::nonNull).anyMatch(e -> e.equals(o));
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return getEmployeeGroups();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) getEmployeeGroups();
    }

    @Override
    public boolean add(EmployeeGroup employeeGroup) {
        if(hasGroup(employeeGroup)) throw new AlreadyAddedException("Группа уже есть");
        if (size == employeeGroups.length) {
            EmployeeGroup[] employeeGroups = new EmployeeGroup[size * 2];
            System.arraycopy(this.employeeGroups, 0, employeeGroups, 0, size);
            this.employeeGroups = employeeGroups;
        }
        employeeGroups[size] = employeeGroup;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return remove((EmployeeGroup) o) > 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Arrays.stream(getEmployeeGroups()).allMatch(c::contains);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        for (Object o : c) {
            try {
                this.addLast((EmployeeGroup) o);
            } catch (AlreadyAddedException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        int newCapacity = employeeGroups.length;
        int i = index;

        while (size + c.size() >= newCapacity) {
            newCapacity *= 2;
        }

        EmployeeGroup[] newDeps = new EmployeeGroup[newCapacity];
        System.arraycopy(employeeGroups, 0, newDeps, 0, size);
        System.arraycopy(newDeps, index, newDeps, index + c.size(), size - index);
        EmployeeGroup[] cArray = new EmployeeGroup[c.size()];
        cArray = c.toArray(cArray);
        int j = 0;

        for (i = index; i < c.size(); i++) {
            newDeps[i] = cArray[j++];
        }

        employeeGroups = newDeps;
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
        for (EmployeeGroup d : employeeGroups) {
            if (d != null && !c.contains(d)) {
                this.remove(d);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        size = 0;
        employeeGroups = new EmployeeGroup[DEFAULT_CAPACITY];
    }

    @Override
    public EmployeeGroup get(int index) {
        return employeeGroups[index];
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        EmployeeGroup gr = employeeGroups[index];
        employeeGroups[index] = element;
        return gr;
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        if (size == employeeGroups.length) {
            EmployeeGroup[] newDeps = new EmployeeGroup[size * 2];
            System.arraycopy(employeeGroups, 0, newDeps, 0, size);
            employeeGroups = newDeps;
        }

        System.arraycopy(employeeGroups, index, employeeGroups, index + 1, size - index);
        size++;
    }

    @Override
    public EmployeeGroup remove(int index) {
        EmployeeGroup e = employeeGroups[index];
        System.arraycopy(employeeGroups, index + 1, employeeGroups, index, size - index - 1);
        size--;

        return e;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (employeeGroups[i] != null) {
                if (employeeGroups[i].equals(o)) {
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
            if (employeeGroups[i] != null) {
                if (employeeGroups[i].equals(o)) {
                    last = i;
                }
            }
        }

        return last;
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return new ListIterator<EmployeeGroup>() {
            int i = index;

            @Override
            public boolean hasNext() {
                return i < size - 1;
            }

            @Override
            public EmployeeGroup next() {
                return employeeGroups[i++];
            }

            @Override
            public boolean hasPrevious() {
                return i > 0;
            }

            @Override
            public EmployeeGroup previous() {
                return employeeGroups[i--];
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
                DepartmentsManager.this.remove(i);
            }

            @Override
            public void set(EmployeeGroup employees) {
                employeeGroups[i] = employees;
            }

            @Override
            public void add(EmployeeGroup employees) {
                DepartmentsManager.this.add(employees);
            }
        };
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        LinkedList<EmployeeGroup> result = new LinkedList<>();
        result.addAll(Arrays.asList(employeeGroups).subList(fromIndex, toIndex));
        return result;
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
    public boolean hasGroup(EmployeeGroup employeeGroup)
    {
        for(int i = 0; i < size; i++)
        {
            if(employeeGroups[i].equals(employeeGroup))
            {
                return true;
            }
        }
        return false;
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

    @Override
    public int countPartTimeEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += employeeGroups[i].countPartTimeEmployees();
        }
        return count;
    }

    @Override
    public int countStaffEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += employeeGroups[i].countStaffEmployees();
        }
        return count;
    }

    @Override
    public int countBusinessTraveller() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += employeeGroups[i].countBusinessTraveller();
        }
        return count;
    }

    @Override
    public Employee[] getBusinessTravellers() {
        int index = 0;
        Employee[] businessTravellers = new Employee[countBusinessTraveller()];
        for(int i = 0; i < size; i++)
        {
            Employee[] employees = employeeGroups[i].getBusinessTravellers();
            System.arraycopy(employees, 0, businessTravellers, index, employees.length);
            index += employees.length;
        }
        return businessTravellers;
    }


}