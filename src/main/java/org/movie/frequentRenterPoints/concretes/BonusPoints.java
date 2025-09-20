package org.movie.frequentRenterPoints.concretes;

import org.movie.frequentRenterPoints.RenterPoints;
import org.movie.rental.Customer;

public class BonusPoints implements RenterPoints {
    @Override
    public int calculatePoints(Customer customer) {
        customer.addFrequentRenterPoints(2);
        return 2;
    }
}
