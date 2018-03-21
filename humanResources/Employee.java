package humanResources;

import java.io.Serializable;

public class Employee implements Serializable {
    //todo: константы именуются капсом, слова разделяются подчеркиванием, имя этой константы хреновенькое.
    //todo: оно не отображает смысловое назначение константы
    public static final int SalaryZero = 0;
    private String firstName;
    private String secondName;
    private String jobTitle;
    private int salary;

    //todo: дефолтные строковые значения тоже должны выноситься в константы
    public Employee() {
        this("", "", "", SalaryZero);
    }

    public Employee(String firstName, String secondName) {
        this(firstName, secondName, "", SalaryZero);
    }

    public Employee(String firstName, String secondName, String jobTitle, int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
