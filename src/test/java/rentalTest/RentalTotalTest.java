package rentalTest;

import org.junit.jupiter.api.Test;
import org.movie.errorHanlding.RentalException.RentalNegativeDayException;
import org.movie.errorHanlding.RenterPointsException.RenterPointsException;
import org.movie.errorHanlding.priceException.MaxDaysPriceException;
import org.movie.errorHanlding.priceException.MinDaysPriceException;
import org.movie.errorHanlding.priceException.PriceException;
import org.movie.movieTypes.Movie;
import org.movie.rental.Customer;
import org.movie.rental.Rental;
import org.movie.rental.RentalTotals;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalTotalsTest {

    @Test
    void shouldCalculateTotalsCorrectly() throws PriceException, RenterPointsException, RentalNegativeDayException {
        Queue<Movie> movies = new LinkedList<>();
        Customer customer = mock(Customer.class);

        Movie movie1 = mock(Movie.class);
        when(movie1.getPrice(5)).thenReturn(10.0);
        when(movie1.getPoints(customer)).thenReturn(2);

        Movie movie2 = mock(Movie.class);
        when(movie2.getPrice(5)).thenReturn(15.0);
        when(movie2.getPoints(customer)).thenReturn(1);

        movies.add(movie1);
        movies.add(movie2);

        Rental rental = new Rental(movies, customer, 5);

        RentalTotals totals = new RentalTotals("1",rental);
        totals.calculateTotals();

        assertEquals(25.0, totals.getTotalAmount());
        assertEquals(3, totals.getTotalPoints());
    }

    @Test
    void shouldThrowExceptionWhenRentalIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new RentalTotals("1",null));
    }

    @Test
    void shouldHandlePriceExceptionGracefully() throws PriceException, RenterPointsException, RentalNegativeDayException {
        Queue<Movie> movies = new LinkedList<>();
        Customer customer = mock(Customer.class);

        Movie movie = mock(Movie.class);
        when(movie.getPrice(0)).thenThrow(new MinDaysPriceException("Invalid price"));
        when(movie.getPrice(100)).thenThrow(new MaxDaysPriceException("Invalid price"));

        when(movie.getPoints(customer)).thenReturn(1);

        movies.add(movie);
        Rental rental = new Rental(movies, customer, 3);

        RentalTotals totals = new RentalTotals("1",rental);
        totals.calculateTotals();
        assertEquals(0.0, totals.getTotalAmount());
        assertEquals(1, totals.getTotalPoints());
    }

    @Test
    void shouldHandleRenterPointsExceptionGracefully() throws PriceException, RenterPointsException, RentalNegativeDayException {
        Queue<Movie> movies = new LinkedList<>();
        Customer customer = mock(Customer.class);

        Movie movie = mock(Movie.class);
        when(movie.getPrice(3)).thenReturn(12.0);
        when(movie.getPoints(customer)).thenThrow(new RenterPointsException("Invalid points"));

        movies.add(movie);
        Rental rental = new Rental(movies, customer, 3);

        RentalTotals totals = new RentalTotals("1",rental);
        totals.calculateTotals();

        assertEquals(12.0, totals.getTotalAmount());
        assertEquals(0, totals.getTotalPoints());
    }
}

