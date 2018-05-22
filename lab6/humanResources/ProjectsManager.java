package humanResources;

import java.util.*;

public class ProjectsManager implements GroupsManager
{
    private String name;
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
        size = groupNodeList.size();
    }

    public void addLast(EmployeeGroup group) throws AlreadyAddedException {

        if (groupNodeList.contains(group)) {
            throw new AlreadyAddedException("Project already added");
        }

        groupNodeList.add((Project) group);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return groupNodeList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return groupNodeList.contains(o);
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return new Iterator<EmployeeGroup>() {
            @Override
            public boolean hasNext() {
                return groupNodeList.iterator().hasNext();
            }

            @Override
            public EmployeeGroup next() {
                return groupNodeList.iterator().next();
            }
        };
    }

    @Override
    public Object[] toArray() {
        return groupNodeList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return groupNodeList.toArray(a);
    }

    @Override
    public boolean add(EmployeeGroup employeeGroup)
    {
        if(hasGroup(employeeGroup)) throw new AlreadyAddedException("Группа уже есть");
        groupNodeList.add(employeeGroup);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return groupNodeList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return groupNodeList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        return groupNodeList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        return groupNodeList.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return groupNodeList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return groupNodeList.retainAll(c);
    }

    @Override
    public void clear() {
        groupNodeList.clear();
    }

    @Override
    public EmployeeGroup get(int index) {
        return groupNodeList.get(index);
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        return groupNodeList.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        groupNodeList.add(index, element);
    }

    @Override
    public EmployeeGroup remove(int index) {
        return groupNodeList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return groupNodeList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return groupNodeList.lastIndexOf(o);
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return new ListIterator<EmployeeGroup>() {
            @Override
            public boolean hasNext() {
                return groupNodeList.listIterator(index).hasNext();
            }

            @Override
            public EmployeeGroup next() {
                return groupNodeList.listIterator(index).next();
            }

            @Override
            public boolean hasPrevious() {
                return groupNodeList.listIterator(index).hasPrevious();
            }

            @Override
            public EmployeeGroup previous() {
                return groupNodeList.listIterator(index).previous();
            }

            @Override
            public int nextIndex() {
                return groupNodeList.listIterator(index).nextIndex();
            }

            @Override
            public int previousIndex() {
                return groupNodeList.listIterator(index).previousIndex();
            }

            @Override
            public void remove() {
                groupNodeList.listIterator(index).remove();
            }

            @Override
            public void set(EmployeeGroup employees) {
                groupNodeList.listIterator(index).set((Project) employees);
            }

            @Override
            public void add(EmployeeGroup employees) {
                groupNodeList.listIterator(index).add((Project) employees);
            }
        };
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        List<EmployeeGroup> list = groupNodeList.subList(fromIndex, toIndex);
        List<EmployeeGroup> result = new LinkedList<>();

        for (EmployeeGroup p : list) {
            result.add(p);
        }

        return result;
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
            group = groupNodeList.get(i);
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
            group = groupNodeList.get(i);
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
            if(groupNodeList.get(i).equals(employeeGroup))
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
            group = groupNodeList.get(i);
            if(group.getName().equals(name))
            {
                return groupNodeList.get(i);
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
            employeeGroup = groupNodeList.get(i);
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
            employeeGroup = groupNodeList.get(i);
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
            employeeGroup = groupNodeList.get(i);
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
            employeeGroup = groupNodeList.get(i);
            if(employeeGroup.hasEmployee(fName,sName))
            {
                return groupNodeList.get(i);
            }
        }
        return null;
    }

    @Override
    public int countPartTimeEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += groupNodeList.get(i).countPartTimeEmployees();
        }
        return count;
    }

    @Override
    public int countStaffEmployees() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += groupNodeList.get(i).countStaffEmployees();
        }
        return count;
    }

    @Override
    public int countBusinessTraveller() {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            count += groupNodeList.get(i).countBusinessTraveller();
        }
        return count;
    }

    @Override
    public Employee[] getBusinessTravellers() {
        int index = 0;
        Employee[] businessTravellers = new Employee[countBusinessTraveller()];
        for(int i = 0; i < size; i++)
        {
            Employee[] employees = groupNodeList.get(i).getBusinessTravellers();
            System.arraycopy(employees, 0, businessTravellers, index, employees.length);
            index += employees.length;
        }
        return businessTravellers;
    }
}
