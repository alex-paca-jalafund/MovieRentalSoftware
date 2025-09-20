package org.movie.rental;

import org.movie.errorHanlding.RentalException.RentalNegativeDayException;
import org.movie.movieTypes.Movie;

import java.util.Queue;

public class Rental {
    private Queue<Movie> movies;
    private Customer customer;
    private int daysRent;

    public Rental(){}
    public Rental(Queue<Movie> movies, Customer customer, int days) throws RentalNegativeDayException {
        if (movies == null || movies.isEmpty()) {
            throw new IllegalArgumentException("Movies queue cannot be null or empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (days < 0) {
            throw new RentalNegativeDayException("Days cannot be negative");
        }
        this.movies = movies;
        this.customer = customer;
        this.daysRent = days;
    }

    public Queue<Movie> getMovies(){
        return this.movies;
    }

    public int getDaysRent(){
        return this.daysRent;
    }

    public Customer getCustomer(){
        return customer;
    }
}
