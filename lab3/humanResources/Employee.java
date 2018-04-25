package humanResources;

abstract class Employee {
    public static final double DEFAULT_SALARY = 0;
    public static final String DEFAULT_NAME = "";
    public static final JobTitlesEnum DEFAULT_TITLE = JobTitlesEnum.NONE;
    private String firstName;
    private String secondName;
    private JobTitlesEnum jobTitle;
    private double salary;

    protected Employee() {
        this(DEFAULT_NAME, DEFAULT_NAME, DEFAULT_TITLE, DEFAULT_SALARY);
    }

    protected Employee(String firstName, String secondName) {
        this(firstName, secondName, DEFAULT_TITLE, DEFAULT_SALARY);
    }

    protected Employee(String firstName, String secondName, JobTitlesEnum jobTitle, double salary) {
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

    public JobTitlesEnum getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitlesEnum jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    abstract double getPremy();

    abstract void setPremy();

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("");
        if (!(secondName.equals(DEFAULT_NAME))) str.append(secondName).append(" ");
        if (!(firstName.equals(DEFAULT_NAME))) str.append(firstName).append(", ");
        if (!(jobTitle.equals(DEFAULT_TITLE))) str.append(jobTitle).append(", ");
        if (salary != DEFAULT_SALARY) str.append(salary).append("p. ");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            Employee temp = (Employee) obj;
            if(temp.firstName.equals(this.firstName) && temp.secondName.equals(this.secondName) && temp.jobTitle == this.jobTitle && temp.salary == this.salary)
                return true;
            else
                return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17, resultfN = 17, resultsN = 17, resultsal = 17, resultjT = 17;

        //result = 37 * result + ( booleanValue ? 1 : 0 );
        //result = 37 * result + (int) charValue;
        resultfN = 37 * result + (this.firstName.equals("") ? 0 : this.firstName.hashCode());
        resultsN = 37 * result + (this.secondName.equals("") ? 0 : this.secondName.hashCode());
        resultjT = 37 * result + (this.jobTitle == JobTitlesEnum.NONE ? 0 : this.jobTitle.hashCode());
        long longBits = Double.doubleToLongBits(this.salary);
        resultsal = 37 * result + (int)(longBits - (longBits >>> 32));
        //result = 37 * result + (int)(longValue - (longValue >>> 32));
        //result = 37 * result + Float.floatToIntBits(floatValue);
        //long longBits = Double.doubleToLongBits(doubleValue);
        //result = 37 * result + (int)(longBits - (longBits >>> 32));
        //for( byte b : arrayValue )
        //    result = 37 * result + (int) b;
        result = resultfN ^ resultsN;
        result ^= resultjT;
        result ^= resultsal;

        return result;
    }

}