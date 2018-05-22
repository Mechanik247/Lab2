package humanResources;
public class PartTimeEmployee extends Employee
{
    String firstName;
    String secondName;
    JobTitlesEnum jobTitle;
    double salary;
    double premy;
    public static final double DEFAULT_PREMY = 0;
    public PartTimeEmployee() {
        this(DEFAULT_NAME, DEFAULT_NAME, DEFAULT_TITLE, DEFAULT_SALARY, DEFAULT_PREMY);
    }

    public PartTimeEmployee(String firstName, String secondName) {
        this(firstName, secondName, DEFAULT_TITLE, DEFAULT_SALARY, DEFAULT_PREMY);
    }

    public PartTimeEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, double salary, double premy) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.premy = premy;
    }

    @Override
    public double getPremy()
    {
        return DEFAULT_PREMY;
    }

    @Override
    void setPremy() {

    }

    @Override
    public String toString()
    {
        String strOut = "", str;
        if (!(secondName.equals(DEFAULT_NAME)))
        {
            str = String.format("%s ", secondName);
            strOut += str;
        }
        if (!(firstName.equals(DEFAULT_NAME)))
        {
            str = String.format("%s, ", firstName);
            strOut += str;
        }
        if (!(jobTitle.equals(DEFAULT_TITLE)))
        {
            str = String.format("%s, ", jobTitle);
            strOut += str;
        }
        if (salary != DEFAULT_SALARY)
        {
            str = String.format("%fp. ", salary);
            strOut += str;
        }
        return strOut;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(this instanceof PartTimeEmployee && obj instanceof PartTimeEmployee))
            return false;
        else
        {
            PartTimeEmployee temp = (PartTimeEmployee) obj;
            if(temp.firstName.equals(this.firstName) && temp.secondName.equals(this.secondName) && temp.jobTitle == this.jobTitle && temp.salary == this.salary)
                return true;
            else
                return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17, resultfN = 17, resultsN = 17, resultsal = 17, resultjT = 17;
        resultfN = 37 * result + (this.firstName.equals("") ? 0 : this.firstName.hashCode());
        resultsN = 37 * result + (this.secondName.equals("") ? 0 : this.secondName.hashCode());
        resultjT = 37 * result + (this.jobTitle == JobTitlesEnum.NONE ? 0 : this.jobTitle.hashCode());
        long longBits = Double.doubleToLongBits(this.salary);
        resultsal = 37 * result + (int)(longBits - (longBits >>> 32));
        result = resultfN ^ resultsN;
        result ^= resultjT;
        result ^= resultsal;

        return result;
    }

}