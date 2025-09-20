package org.movie.pricing.concretes;

import org.movie.pricing.Price;

public class NewReleasePrice implements Price {

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3;
    }
}
