import humanResources.*;

import java.util.Scanner;

public class Main {
    public static final int CICLE = 1;

    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        DepartmentsManager o = new DepartmentsManager();
        for (int i = 0; i < o.getEmployeeGroups().length; i++) {
            System.out.print(o.getEmployeeGroups()[i] + " ");
        }
        System.out.println();
        System.out.println(o.getEmployeeGroups().length);
        System.out.println("Введите название организации");
        o.setName(k.next());
        do {
            System.out.println("Добавить сотрудника - 1");
            System.out.println("Вывести список - 2");
            System.out.println("Удалить отдел - 3");
            System.out.println("Удалить сотрудника - 4");
            switch (k.nextInt()) {
                case 1: {
                    System.out.println("Введите название отдела");
                    Department d = new Department(k.next());
                    Employee e = new Employee();
                    System.out.println("Введите Имя");
                    e.setFirstName(k.next());
                    System.out.println("Введите Фамилию");
                    e.setSecondName(k.next());
                    System.out.println("Введите Должность");
                    e.setJobTitle(k.next());
                    System.out.println("Введите ЗП сотрудника");
                    e.setSalary(k.nextInt());
                    d.add(e);
                    o.add(d);
                    break;
                }
                case 2: {
                    System.out.println("Организация - " + o.getName());
                    Employee employee;
                    for (int i = 0; i < o.getEmployeeGroups().length; i++) {
                        int y = 1;
                        if (o.getEmployeeGroups()[i] != null) {
                            System.out.println("    Отдел - " + o.getEmployeeGroups()[i].getName());
                            for (int j = 0; j < o.getEmployeeGroups()[i].getEmployees().length; j++) {
                                employee = o.getEmployeeGroups()[i].getEmployees()[j];
                                if (o.getEmployeeGroups()[i].getEmployees()[j] != null) {
                                    System.out.println("        Сотрудник " + y + ": ");
                                    System.out.println("            " + employee.getFirstName());
                                    System.out.println("            " + employee.getSecondName());
                                    System.out.println("            " + employee.getJobTitle());
                                    System.out.println("            " + employee.getSalary());
                                    y++;
                                }
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Введите название отдела для удаления");
                    o.RemoveDep(k.next());
                    break;
                }
                case 4: {
                    System.out.println("Введите Имя сотрудника");
                    String fName = k.next();
                    System.out.println("Введите Фамилию сотрудника");
                    String sName = k.next();
                    break;
                }
            }
        } while (CICLE != 0);
    }
}
