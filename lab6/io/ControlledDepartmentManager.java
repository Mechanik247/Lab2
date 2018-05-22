package io;

import humanResources.*;

import java.util.*;

public class ControlledDepartmentManager extends DepartmentsManager
{
    private static final int DEFAULT_CAPACITY = 8;
    private static final String DEFAULT_NAME = "";
    private String name;
    private ControlledDepartment[] controlledDepartments;
    private int size;
    protected Source<ControlledDepartment> groupSource;

    public ControlledDepartmentManager() {
        super();
    }

    public ControlledDepartmentManager(String name) {
        super(name);
    }

    public ControlledDepartmentManager(String name, int size) {
        super(name, size);
    }

    public ControlledDepartmentManager(String name, ControlledDepartment[] controlledDepartments) {
        super(name, controlledDepartments);
    }

    public Source<ControlledDepartment> getGroupSource() {
        return groupSource;
    }

    public void setGroupSource(Source<ControlledDepartment> groupSource) {
        this.groupSource = groupSource;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public boolean add(ControlledDepartment employeeGroup) {
        ControlledDepartmentManager controlledDepMan = new ControlledDepartmentManager();
        controlledDepMan.groupSource.create(employeeGroup);
        return super.add(employeeGroup);
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
        for (EmployeeGroup d : controlledDepartments) {
            if (d != null && !c.contains(d)) {
                this.remove(d);
                isChanged = true;
            }
        }
        return isChanged;
    }


    public void store()
    {
        for(int i = 0; i < size; i++)
        {
            if(controlledDepartments[i].isChanged())
            {
                groupSource.store(controlledDepartments[i]);
            }
        }
    }

    public void load()
    {
        for(int i = 0; i < size; i++)
        {
            groupSource.load(controlledDepartments[i]);
        }
    }
}
