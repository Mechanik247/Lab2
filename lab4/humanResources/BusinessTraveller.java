package humanResources;

import java.util.Date;

public interface BusinessTraveller
{
    void addInfoOfBusinessTravel(BusinessTravel businessTravel);
    BusinessTravel[] arrayOfBusinessTravel();
    boolean hasEmployeeInBusinessTravel();
    int hasEmployeeInBusinessTravel(Date dateBegin, Date dateEnd);
}