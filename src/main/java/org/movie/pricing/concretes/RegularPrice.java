package org.movie.pricing.concretes;

import org.movie.pricing.Price;

public class RegularPrice implements Price {
    @Override
    public double calculatePrice(int daysRented) {
        double amount = 2;
        if(daysRented > 2){
            amount += (daysRented - 2) * 1.5;
        }
        return amount;
    }
}
