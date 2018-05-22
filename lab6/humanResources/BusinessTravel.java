package humanResources;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class BusinessTravel
{
    private final String nameCountry;
    private final Date dateBegin;
    private final Date dateEnd;
    private final SimpleDateFormat formatForDateNow;
    private final double summCompensation;
    private final String description;
    public static final String DEFAULT_NAME = "";
    public static final String DEFAULT_DESCRIPTION = "";
    public static final int DEFAULT_SUMM = 0;
    private final Calendar instance = Calendar.getInstance();
    public static final Date DEFAULT_DATE_BEGIN = new Date();
    public static final Date DEFAULT_DATE_END = new Date(DEFAULT_DATE_BEGIN.getTime() + (2 * 24 * 60 * 60 * 1000));

    public BusinessTravel()
    {
        this(DEFAULT_NAME, DEFAULT_SUMM, DEFAULT_DESCRIPTION, DEFAULT_DATE_BEGIN, DEFAULT_DATE_END);
    }

    public BusinessTravel(String nameCountry, double summCompensation, String description, Date dateBegin, Date dateEnd)
    {
        if(dateBegin.after(dateEnd)) throw new IllegalArgumentException("Дата начала командировк не может быть позже даты конца");
        if(summCompensation < 0) throw new IllegalArgumentException("Сумма компенсации не может быть меньше нуля");
        this.nameCountry = nameCountry;
        this.summCompensation = summCompensation;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public Date getDefaultDateEnd() {
        instance.setTime(DEFAULT_DATE_BEGIN);
        instance.add(Calendar.DAY_OF_MONTH, 2);
        Date newDate = instance.getTime();
        return newDate;
    }

    public int getCountDays(Date dateBegin, Date dateEnd) {
        long difference =  dateEnd.getTime() - dateBegin.getTime();
        int days = (int)(difference / (24 * 60 * 60 * 1000));
        return days;
    }

    public int getCountDays() {
        long difference =  dateEnd.getTime() - dateBegin.getTime();
        int days = (int)(difference / (24 * 60 * 60 * 1000));
        return days;
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
        String strOut = "", str;
        if (!(nameCountry.equals("")))
        {
            str = String.format("%s ", nameCountry);
            strOut += str;
        }
        if (getCountDays() != 0)
        {
            str = String.format("%d ", getCountDays());
            strOut += str;
        }
        if (summCompensation != 0)
        {
            str = String.format("%f ", summCompensation);
            strOut += str;
        }
        if (!(description.equals("")))
        {
            str = String.format("%s ", description);
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

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            BusinessTravel temp = (BusinessTravel) obj;
            if(temp.nameCountry.equals(this.nameCountry) && temp.getCountDays() == this.getCountDays() && temp.summCompensation == this.summCompensation && temp.description.equals(this.description))
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
        long longBitsCD = Double.doubleToLongBits(this.getCountDays());
        resultCD = 37 * result + (int)(longBitsCD - (longBitsCD >>> 32));
        long longBitsSC = Double.doubleToLongBits(this.summCompensation);
        resultSC = 37 * result + (int)(longBitsSC - (longBitsSC >>> 32));
        result = resultNC ^ resultD;
        result ^= resultCD;
        result ^= resultSC;

        return result;
    }
}