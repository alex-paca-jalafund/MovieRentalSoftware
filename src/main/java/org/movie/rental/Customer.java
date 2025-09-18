package org.movie.rental;

import org.movie.errorHanlding.customerException.CustomerUserNameException;

public class Customer {
    private final String userName;
    private int frequentRenterPoints = 0;

    public Customer(String userName) throws CustomerUserNameException {

        if (userName == null || userName.trim().isEmpty()) {
            throw new CustomerUserNameException("User name cannot be null or empty");
        }

        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getFrequentRenterPoints(){
        return this.frequentRenterPoints;
    }

    public void addFrequentRenterPoints(int additionalPoints){
        this.frequentRenterPoints += additionalPoints;
    }
}
