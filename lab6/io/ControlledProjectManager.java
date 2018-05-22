package io;

import humanResources.*;

import java.util.*;

public class ControlledProjectManager extends ProjectsManager
{
    private static final int DEFAULT_CAPACITY = 8;
    private static final String DEFAULT_NAME = "";
    private String name;
    private ControlledProject[] controlledProjects;
    private int size;
    protected Source<ControlledProject> groupSource;

    public ControlledProjectManager() {
        super();
    }

    public ControlledProjectManager(ControlledProject[] employeeGroups) {
        super(employeeGroups);
    }

    public Source<ControlledProject> getGroupSource() {
        return groupSource;
    }

    public void setGroupSource(Source<ControlledProject> groupSource) {
        this.groupSource = groupSource;
    }

    public boolean add(ControlledProject employeeGroup) {
        ControlledProjectManager controlledProjMan = new ControlledProjectManager();
        controlledProjMan.groupSource.create(employeeGroup);
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
        for (ControlledProject d : controlledProjects) {
            if (d != null && !c.contains(d)) {
                this.remove(d);
                isChanged = true;
            }
        }
        return isChanged;
    }
    public int remove(ControlledProject employeeGroup)
    {
        groupSource.delete(employeeGroup);
        return super.remove(employeeGroup);
    }

    public void store()
    {
        for(int i = 0; i < size; i++)
        {
            if(controlledProjects[i].isChanged())
            {
                groupSource.store(controlledProjects[i]);
            }
        }
    }

    public void load()
    {
        for(int i = 0; i < size; i++)
        {
            groupSource.load(controlledProjects[i]);
        }
    }
}
