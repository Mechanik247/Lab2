package humanResources;

import java.io.*;
import java.util.Scanner;

public class Organization implements Serializable {
    private String name;
    private Department[] departments;

    public Organization() {
        this("", 3);
    }

    ;

    public Organization(String name) {
        this.name = name;
        departments = new Department[8];
    }

    public Organization(String name, int k) {
        this.name = name;
        departments = new Department[k];
    }

    public Organization(String name, Department[] departments) {
        this.name = name;
        this.departments = departments;
    }

    public void AddEmployToOrganization()
    {
        Scanner k = new Scanner(System.in);
        int check = 0;
        Employee e = new Employee();
        Department d = new Department();
        System.out.println("Введите Имя");
        e.setFirstName(k.next());
        System.out.println("Введите Фамилию");
        e.setSecondName(k.next());
        System.out.println("Введите Должность");
        e.setJobTitle(k.next());
        System.out.println("Введите ЗП сотрудника");
        e.setSalary(k.nextInt());
        System.out.println("Введите название отдела");
        String nameDep = k.next();
        for (int i = 0; i < getDepartments().length; i++) {
            if (getDepartments()[i] != null && getDepartments()[i].getName().equals(nameDep)) {
                getDepartments()[i].add(e);
                check++;
            }
        }
        if (check == 0) {
            d.setName(nameDep);
            d.add(e);
            AddDep(d);
        }
    }

    public void PrintArrayOfEmployes()
    {
        System.out.println("Организация - " + getName());
        for (int i = 0; i < getDepartments().length; i++) {
            int y = 1;
            if (getDepartments()[i] != null) {
                System.out.println("    Отдел - " + getDepartments()[i].getName());
                for (int j = 0; j < getDepartments()[i].getEmployees().length; j++) {
                    if (getDepartments()[i].getEmployees()[j] != null) {
                        System.out.println("        Сотрудник " + y + ": ");
                        System.out.println("            " + getDepartments()[i].getEmployees()[j].getFirstName());
                        System.out.println("            " + getDepartments()[i].getEmployees()[j].getSecondName());
                        System.out.println("            " + getDepartments()[i].getEmployees()[j].getJobTitle());
                        System.out.println("            " + getDepartments()[i].getEmployees()[j].getSalary());
                        y++;
                    }
                }
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void AddDep(Department dep) {
        int k = departments.length;
        boolean checkExistNull = false;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] == null) checkExistNull = true;
        }
        if (checkExistNull) {
            for (int i = 0; i < departments.length; i++) {
                if (departments[i] == null) {
                    departments[i] = dep;
                    break;
                }
            }
        } else {
            int n = 0;
            Department[] departments2 = new Department[k * 2];
            System.arraycopy(departments, 0, departments2, 0, k);
            departments2[k] = dep;
            departments = departments2;
        }
    }

    public void RemoveDep(String name, Department[] departments) {
        int k = departments.length;
        boolean checkRemove = false;
        for (int i = 0; i < departments.length; i++) {
            System.out.println(i);
            if (departments[i] != null) System.out.println(departments[i].getName());
            if (departments[i] != null && departments[i].getName().equals(name)) {
                departments[i] = null;
                System.arraycopy(departments, i + 1, departments, i, k - (i + 1));
                departments[k - 1] = null;
                checkRemove = true;
            }
        }
        if (checkRemove) System.out.println("Отдел удалён");
        else System.out.println("Отдел не найден");
    }

    public void RemoveEmploy(String fName, String sName, Department[] departments) {
        boolean checkRemove = false;
        for (int m = 0; m < departments.length; m++) {
            if (departments[m] != null) {
                int k = departments[m].getEmployees().length;
                for (int i = 0; i < departments[m].getEmployees().length; i++) {
                    if (departments[m].getEmployees()[i] != null && departments[m].getEmployees()[i].getFirstName().equals(fName) && departments[m].getEmployees()[i].getSecondName().equals(sName)) {
                        departments[m].getEmployees()[i] = null;
                        System.arraycopy(departments[m].getEmployees(), i + 1, departments[m].getEmployees(), i, k - (i + 1));
                        departments[m].getEmployees()[k - 1] = null;
                        checkRemove = true;
                    }
                }
            }
        }
        if (checkRemove) System.out.println("Сотрудник удалён");
        else System.out.println("Сотрудник не найден");
    }

    public void ChangeSalary(String fName, String sName, int salary, Department[] departments) {
        boolean checkRemove = false;
        for (int i = 0; i < sizeDepartment(); i++) {
            if (departments[i] != null) {
                for (int j = 0; j < sizeEmployeesInOrganization(departments[i]); j++) {
                    if (departments[i].getEmployees()[j] != null && departments[i].getEmployees()[j].getFirstName().equals(fName) && departments[i].getEmployees()[j].getSecondName().equals(sName)) {
                        departments[i].getEmployees()[j].setSalary(salary);
                        checkRemove = true;
                    }
                }
            }
        }
        if (checkRemove) System.out.println("ЗП изменена");
        else System.out.println("Сотрудник не найден");
    }

    public void ChangeTitle(String fName, String sName, String title, Department[] departments) {
        boolean checkRemove = false;
        for (int i = 0; i < sizeDepartment(); i++) {
            if (departments[i] != null) {
                for (int j = 0; j < sizeEmployeesInOrganization(departments[i]); j++) {
                    if (departments[i].getEmployees()[j] != null && departments[i].getEmployees()[j].getFirstName().equals(fName) && departments[i].getEmployees()[j].getSecondName().equals(sName)) {
                        departments[i].getEmployees()[j].setJobTitle(title);
                        checkRemove = true;
                    }
                }
            }
        }
        if (checkRemove) System.out.println("Должность изменена");
        else System.out.println("Сотрудник не найден");
    }

    public Department getLinkDepartment(String name, Department[] departments) {
        Department dep = new Department();
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null && departments[i].getName() == name) {
                dep = departments[i];
            }
        }
        return dep;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public int sizeDepartment() {
        return departments.length;
    }

    public int sizeEmployeesInOrganization(Department dep) {
        int k = 0;
        for (int j = 0; j < dep.getEmployees().length; j++) {
            k++;
        }
        return k;
    }

    public int sizeEmployeesInOrganizationHaveJobTitle(String jTitle) {
        int k = 0;
        for (int i = 0; i < departments.length; i++) {
            for (int j = 0; j < departments[i].getEmployees().length; j++) {
                if (departments[i].getEmployees()[j].getJobTitle() == jTitle) {
                    k++;
                }
            }
        }
        return k;
    }

    public Employee EmployHaveMaxSalary(Department[] departments) {
        int ki = 0, kj = 0, max = 0;
        for (int i = 0; i < departments.length; i++) {
            for (int j = 0; j < departments[i].getEmployees().length; j++) {
                if (departments[i].getEmployees()[j].getSalary() > max) {
                    max = departments[i].getEmployees()[j].getSalary();
                    ki = i;
                    kj = j;
                }
            }
        }
        return departments[ki].getEmployees()[kj];
    }

    public Department DepartmentHaveEmploy(String fName, String sName) {
        Department temp = new Department();
        for (int i = 0; i < departments.length; i++) {
            for (int j = 0; j < departments[i].getEmployees().length; j++) {
                if (departments[i].getEmployees()[j].getFirstName() == fName && departments[i].getEmployees()[j].getSecondName() == sName) {
                    temp = departments[i];
                }
            }
        }
        return temp;
    }
}