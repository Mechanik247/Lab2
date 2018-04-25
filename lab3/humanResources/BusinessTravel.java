package humanResources;
public class BusinessTravel
{
    private final String nameCountry;
    private final int countDays;
    private final double summCompensation;
    private final String description;
    public static final String DEFAULT_NAME = "";
    public static final String DEFAULT_DESCRIPTION = "";
    public static final int DEFAULT_COUNT = 0;
    public static final int DEFAULT_SUMM = 0;

    public BusinessTravel()
    {
        this(DEFAULT_NAME, DEFAULT_COUNT, DEFAULT_SUMM, DEFAULT_DESCRIPTION);
    }

    public BusinessTravel(String nameCountry, int countDays, double summCompensation, String description)
    {
        this.nameCountry = nameCountry;
        this.countDays = countDays;
        this.summCompensation = summCompensation;
        this.description = description;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public int getCountDays() {
        return countDays;
    }

    public double getSummCompensation() {
        return summCompensation;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("");
        if (!(nameCountry.equals(""))) str.append(nameCountry).append(" ");
        if (countDays != 0) str.append(countDays).append(" ");
        if (summCompensation != 0) str.append(summCompensation).append(" ");
        if (!(description.equals(""))) str.append(description).append(" ");
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
            BusinessTravel temp = (BusinessTravel) obj;
            if(temp.nameCountry.equals(this.nameCountry) && temp.countDays == this.countDays && temp.summCompensation == this.summCompensation && temp.description.equals(this.description))
                return true;
            else
                return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17, resultNC, resultD, resultCD, resultSC;
        resultNC = 37 * result + (this.nameCountry.equals("") ? 0 : this.nameCountry.hashCode());
        resultD = 37 * result + (this.description.equals("") ? 0 : this.description.hashCode());
        long longBitsCD = Double.doubleToLongBits(this.countDays);
        resultCD = 37 * result + (int)(longBitsCD - (longBitsCD >>> 32));
        long longBitsSC = Double.doubleToLongBits(this.summCompensation);
        resultSC = 37 * result + (int)(longBitsSC - (longBitsSC >>> 32));
        result = resultNC ^ resultD;
        result ^= resultCD;
        result ^= resultSC;

        return result;
    }
}