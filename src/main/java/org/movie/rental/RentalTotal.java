package org.movie.rental;

import org.movie.errorHanlding.RenterPointsException.RenterPointsException;
import org.movie.errorHanlding.priceException.PriceException;
import org.movie.movieTypes.Movie;

public class RentalTotal {
    private String id;
    private double totalAmount;
    private int totalPoints;
    private Rental rental;

    private RentalTotal() {}

    public RentalTotal(String id, Rental rental) {
        this.rental = rental;
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        this.id = id;
        this.totalPoints = 0;
        this.totalAmount = 0;
    }

    public void calculateTotals() {
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        for(Movie movie : rental.getMovies()){
            try{
                totalAmount += movie.getPrice(rental.getDaysRent());
                totalPoints += movie.getPoints(rental.getCustomer());
            }catch (PriceException e){
                System.err.println("Error in pricing: " + e.getMessage());
            }catch (RenterPointsException e){
                System.err.println("Error in renter points: " + e.getMessage());
            }catch (ArithmeticException e){
                System.err.println("Error calculating: " + e);
            }
        }
    }
    public String getId(){return this.id;}
    public Rental getRental(){return this.rental;}
    public double getTotalAmount(){ return totalAmount; }
    public int getTotalPoints(){ return totalPoints; }
}
