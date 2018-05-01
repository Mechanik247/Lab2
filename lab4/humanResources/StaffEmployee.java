package humanResources;

import java.util.Date;

public class StaffEmployee extends Employee implements BusinessTraveller
{
    private static final double DEFAULT_PREMY = 0;
    private String firstName;
    private String secondName;
    private JobTitlesEnum jobTitle;
    private double premy;
    private double salary;
    private int size;
    private cycleNodeList<BusinessTravel> businessTravelNodeList;
    public static final BusinessTravel[] DEFAULT_NODE = null;
    public StaffEmployee(String firstName, String secondName)
    {
        this(firstName, secondName, DEFAULT_TITLE, DEFAULT_SALARY, DEFAULT_NODE);
        this.premy = DEFAULT_PREMY;
        businessTravelNodeList = new cycleNodeList<>();
        size = 0;
    }
    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, double salary)
    {
        this(firstName, secondName, jobTitle, salary, DEFAULT_NODE);
        this.premy = DEFAULT_PREMY;
        businessTravelNodeList = new cycleNodeList<>();
        size = 0;
    }
    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, double salary, BusinessTravel[] businessTravels)
    {
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.premy = DEFAULT_PREMY;
        businessTravelNodeList = new cycleNodeList<>(businessTravels);
        size = businessTravels.length;
    }

    @Override
    double getPremy() {
        return DEFAULT_PREMY;
    }

    @Override
    void setPremy() {

    }

    public void addInfoOfBusinessTravel(BusinessTravel businessTravel)
    {
        BusinessTravel thisBusTravel;
        Date addDateBegin = businessTravel.getDateBegin();
        Date addDateEnd = businessTravel.getDateEnd();
        for(int i = 0; i < size; i++)
        {
            thisBusTravel = businessTravelNodeList.getItem(i);
            if(addDateBegin.after(thisBusTravel.getDateBegin()) && addDateBegin.before(thisBusTravel.getDateEnd()))
                throw new IllegalDatesException("Время командировки пересекатеся со временем уже имеющихся командировок");
            else if(addDateEnd.after(thisBusTravel.getDateBegin()) && addDateEnd.before(thisBusTravel.getDateEnd()))
                throw new IllegalDatesException("Время командировки пересекатеся со временем уже имеющихся командировок");
            else if(addDateBegin.before(thisBusTravel.getDateBegin()) && addDateEnd.after(thisBusTravel.getDateEnd()))
                throw new IllegalDatesException("Время командировки пересекатеся со временем уже имеющихся командировок");
        }
        businessTravelNodeList.add(businessTravel);
        size++;
    }

    @Override
    public BusinessTravel[] arrayOfBusinessTravel() {
        BusinessTravel[] businessTravels = new BusinessTravel[businessTravelNodeList.length()];
        return businessTravelNodeList.toArray(businessTravels);
    }

    @Override
    public boolean hasEmployeeInBusinessTravel() {
        Date currentDate = new Date();
        BusinessTravel businessTravel;
        for(int i = 0; i < size; i++)
        {
            businessTravel = businessTravelNodeList.getItem(i);
            if(currentDate.after(businessTravel.getDateBegin()) && currentDate.before(businessTravel.getDateEnd()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hasEmployeeInBusinessTravel(Date dateBegin, Date dateEnd) {
        BusinessTravel businessTravel;
        for(int i = 0; i < size; i++)
        {
            businessTravel = businessTravelNodeList.getItem(i);
            if(dateEnd.before(businessTravel.getDateBegin()) || dateBegin.after(businessTravel.getDateEnd()))
            {
                return 0;
            }
            else if(dateBegin.after(businessTravel.getDateBegin()) && dateEnd.before(businessTravel.getDateEnd()))
            {
                return businessTravel.getCountDays(dateBegin, dateEnd);
            }
            else if(dateBegin.before(businessTravel.getDateBegin()) && dateEnd.before(businessTravel.getDateEnd()))
            {
                return businessTravel.getCountDays(businessTravel.getDateBegin(), dateEnd);
            }
            else if(dateBegin.after(businessTravel.getDateBegin()) && dateEnd.after(businessTravel.getDateEnd()))
            {
                return businessTravel.getCountDays(dateBegin, businessTravel.getDateEnd());
            }
        }
        return 0;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("");
        if (!(secondName.equals(""))) str.append(secondName).append(" ");
        if (!(firstName.equals(""))) str.append(firstName).append(", ");
        if (jobTitle != JobTitlesEnum.NONE) str.append(jobTitle).append(", ");
        if (salary != 0) str.append(salary).append("p., ");
        str.append(premy).append("p. ");
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