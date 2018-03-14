import humanResources.*;

import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {
    public static final int Cicle = 1;

    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        Organization o = new Organization();
        for (int i = 0; i < o.getDepartments().length; i++) {
            System.out.print(o.getDepartments()[i] + " ");
        }
        System.out.println();
        System.out.println(o.getDepartments().length);
        System.out.println("Введите название организации");
        o.setName(k.next());
        do {
            System.out.println("Добавить сотрудника - 1");
            System.out.println("Вывести список - 2");
            System.out.println("Удалить отдел - 3");
            System.out.println("Удалить сотрудника - 4");
            System.out.println("Сохранить данные - 5");
            System.out.println("Загрузить данные - 6");
            System.out.println("Изменить заработную плату - 7");
            System.out.println("Изменить должность - 8");
            switch (k.nextInt()) {
                case 1: {
                    o.AddEmployToOrganization();
                    break;
                }
                case 2: {
                    o.PrintArrayOfEmployes();
                    break;
                }
                case 3: {
                    System.out.println("Введите название отдела для удаления");
                    o.RemoveDep(k.next(), o.getDepartments());
                    break;
                }
                case 4: {
                    System.out.println("Введите Имя сотрудника");
                    String fName = k.next();
                    System.out.println("Введите Фамилию сотрудника");
                    String sName = k.next();
                    o.RemoveEmploy(fName, sName, o.getDepartments());
                    break;
                }
                case 5: {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.out"))) {
                        oos.writeObject(o);
                        System.out.println("Запись произведена");
                    } catch (Exception ex) {

                        System.out.println(ex.getMessage());
                    }
                    break;
                }
                case 6: {
                    Organization p;
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.out"))) {
                        p = (Organization) ois.readObject();
                        System.out.println("Чтение произведено");
                        o = p;
                    } catch (Exception ex) {

                        System.out.println(ex.getMessage());
                    }
                    break;
                }
                case 7: {
                    System.out.println("Введите Имя сотрудника");
                    String fName = k.next();
                    System.out.println("Введите Фамилию сотрудника");
                    String sName = k.next();
                    System.out.println("Введите новую заработную плату");
                    int salary = k.nextInt();
                    o.ChangeSalary(fName, sName, salary, o.getDepartments());
                    break;
                }
                case 8: {
                    System.out.println("Введите Имя сотрудника");
                    String fName = k.next();
                    System.out.println("Введите Фамилию сотрудника");
                    String sName = k.next();
                    System.out.println("Введите новую должность");
                    String title = k.next();
                    o.ChangeTitle(fName, sName, title, o.getDepartments());
                    break;
                }
            }
        } while (Cicle != 0);
    }
}
