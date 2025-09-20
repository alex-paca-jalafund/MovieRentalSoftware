package org.movie.pricing.concretes;

import org.movie.pricing.Price;

public class ChildrenPrice implements Price {

    @Override
    public double calculatePrice(int daysRented) {
        double amount = 1.5;
        if(daysRented > 3){
            amount += (daysRented - 3) * 1.5;
        }
        return amount;
    }
}
