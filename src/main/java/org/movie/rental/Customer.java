package org.movie.rental;

import org.movie.errorHanlding.customerException.CustomerUserNameException;

public class Customer {
    private  String userName;
    private  String id;
    private int frequentRenterPoints = 0;

    private Customer(){}

    public Customer(String id, String userName) throws CustomerUserNameException {

        if (userName == null || userName.trim().isEmpty()) {
            throw new CustomerUserNameException("User name cannot be null or empty");
        }
        this.id = id;
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getId() { return this.id; }

    public int getFrequentRenterPoints(){
        return this.frequentRenterPoints;
    }

    public void addFrequentRenterPoints(int additionalPoints){
        this.frequentRenterPoints += additionalPoints;
    }
}
