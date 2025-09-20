package org.movie.frequentRenterPoints.concretes;

import org.movie.frequentRenterPoints.RenterPoints;
import org.movie.rental.Customer;

public class RegularPoints implements RenterPoints {
    @Override
    public int calculatePoints(Customer customer) {
        customer.addFrequentRenterPoints(1);
        return 1;
    }
}
