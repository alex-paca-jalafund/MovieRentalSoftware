package org.movie.frequentRenterPoints.concretes;

import org.movie.errorHanlding.RenterPointsException.RenterPointsCustomerException;
import org.movie.frequentRenterPoints.RenterPoints;
import org.movie.rental.Customer;

public class RenterPointsContext {
    private RenterPoints renterPoints;

    public  RenterPointsContext(RenterPoints renterPoints){
        this.renterPoints = renterPoints;
    }

    public void setRenterPointsStrategy(RenterPoints newrenterPoints){
        renterPoints = newrenterPoints;
    }

    public int calculatePoints(Customer customer) throws RenterPointsCustomerException {
        if(customer != null){
            return renterPoints.calculatePoints(customer);
        }else{
            throw new RenterPointsCustomerException("Customer can't be null");
        }
    }
}
