package org.movie.pricing.concretes;

import org.movie.errorHanlding.priceException.MaxDaysPriceException;
import org.movie.errorHanlding.priceException.MinDaysPriceException;
import org.movie.pricing.Price;

public class PriceContext {
    private Price price;

    public PriceContext(Price price){
        this.price = price;
    }

    public void setPriceStrategy (Price newPrice){
        this.price = newPrice;
    }

    public double calculatePrice(int daysRented) throws MinDaysPriceException, MaxDaysPriceException {
        if(daysRented >= 1){
            if( daysRented <= 30 ){
                return price.calculatePrice(daysRented);
            }else{
                throw new MaxDaysPriceException("Maximum rental days is 30");
            }
        }else{
            throw new MinDaysPriceException("Minimum rental days rented it's 1");
        }
    }
}
