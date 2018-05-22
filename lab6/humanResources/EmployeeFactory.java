package humanResources;

import io.GroupsManagerTextFileSource;
import io.OrdersFactoryTypesEnumeration;

public abstract class EmployeeFactory
{
    EmployeeGroup createDepartment() {
        return null;
    }
    EmployeeGroup createDepartment(String name) {
        return null;
    }
    EmployeeGroup createDepartment(String name, int size) {
        return null;
    }
    EmployeeGroup createDepartment(String name, Employee[] employees) {
        return null;
    }

    EmployeeGroup createProject()
    {
        return null;
    }
    EmployeeGroup createProject(String name)
    {
        return null;
    }
    EmployeeGroup createProject(String name, Employee[] employees)
    {
        return null;
    }

    GroupsManager createDepartmentManager()
    {
        return null;
    }
    GroupsManager createDepartmentManager(String name)
    {
        return null;
    }
    GroupsManager createDepartmentManager(String name, int size)
    {
        return null;
    }
    GroupsManager createDepartmentManager(String name, EmployeeGroup[] employeeGroups)
    {
        return null;
    }

    GroupsManager createProjectManager()
    {
        return null;
    }
    GroupsManager createProjectManager(EmployeeGroup employeeGroup)
    {
        return null;
    }

    static public EmployeeFactory getEmployeeFactory(OrdersFactoryTypesEnumeration type)
    {
        if(type.equals(OrdersFactoryTypesEnumeration.TEXT_FILE_BASED_GROUPS_FACTORY))
        {
            GroupsManagerTextFileSource managerTextFileSource = new GroupsManagerTextFileSource();
            return managerTextFileSource;
        }
        return null;
    }
}