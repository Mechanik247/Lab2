package io;

import humanResources.*;

import java.util.*;

public class ControlledDepartment extends Department
{
    private String name;
    private Employee[] employees;
    private int size;
    protected boolean isChanged = false;

    public ControlledDepartment() {
        super();
    }

    public ControlledDepartment(Department department) {
        name = department.getName();
        size = department.size();
        employees = department.getEmployees();
    }

    public ControlledDepartment(String name) {
        super(name);
    }

    public ControlledDepartment(String name, int size) {
        super(name, size);
    }

    public ControlledDepartment(String name, Employee[] employeesNew) {
        super(name, employeesNew);
    }

    protected boolean isChanged()
    {
        return isChanged;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        isChanged = true;
    }

    @Override
    public boolean add(Employee employee) {
        if(super.add(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(super.remove(o)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(super.containsAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    public void addLast(Employee employee) throws AlreadyAddedException {
        super.addLast(employee);
        isChanged = true;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        if(super.addAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        if(super.addAll(index, c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(super.removeAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(super.retainAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        super.clear();
        isChanged = true;
    }

    @Override
    public void remove(String fName, String sName) {
        super.remove(fName, sName);
        isChanged = true;
    }

    @Override
    public boolean remove(Employee employee)
    {
        if(super.remove(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    public void removeByTitle(JobTitlesEnum title)
    {
        super.removeByTitle(title);
        isChanged = true;
    }

    @Override
    public Employee set(int index, Employee element) {
        isChanged = true;
        return super.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        isChanged = true;
        super.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        isChanged = true;
        return super.remove(index);
    }
}
