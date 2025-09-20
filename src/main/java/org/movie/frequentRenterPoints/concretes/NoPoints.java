package org.movie.frequentRenterPoints.concretes;

import org.movie.frequentRenterPoints.RenterPoints;
import org.movie.rental.Customer;

public class NoPoints implements RenterPoints {
    @Override
    public int calculatePoints(Customer customer) {
        customer.addFrequentRenterPoints(0);
        return 0;
    }
}
