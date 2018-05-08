package humanResources;

import java.util.Date;
import java.util.Set;

public interface BusinessTraveller extends Set<BusinessTravel>
{
    void addInfoOfBusinessTravel(BusinessTravel businessTravel);
    BusinessTravel[] arrayOfBusinessTravel();
    boolean hasEmployeeInBusinessTravel();
    int hasEmployeeInBusinessTravel(Date dateBegin, Date dateEnd);
}