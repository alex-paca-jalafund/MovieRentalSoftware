package rentalTest;

import org.junit.jupiter.api.Test;
import org.movie.errorHanlding.RentalException.RentalNegativeDayException;
import org.movie.movieTypes.Movie;
import org.movie.rental.Customer;
import org.movie.rental.Rental;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalTest {

    @Test
    void shouldCreateRentalWithValidData() throws RentalNegativeDayException {
        Queue<Movie> movies = new LinkedList<>();
        movies.add(mock(Movie.class));
        Customer customer = mock(Customer.class);

        Rental rental = new Rental(movies, customer, 3);

        assertEquals(3, rental.getDaysRent());
        assertEquals(customer, rental.getCustomer());
        assertEquals(1, rental.getMovies().size());
    }

    @Test
    void shouldThrowExceptionWhenMoviesNull() {
        Customer customer = mock(Customer.class);

        assertThrows(IllegalArgumentException.class,
                () -> new Rental(null, customer, 3));
    }

    @Test
    void shouldThrowExceptionWhenMoviesEmpty() {
        Queue<Movie> movies = new LinkedList<>();
        Customer customer = mock(Customer.class);

        assertThrows(IllegalArgumentException.class,
                () -> new Rental(movies, customer, 3));
    }

    @Test
    void shouldThrowExceptionWhenCustomerNull() {
        Queue<Movie> movies = new LinkedList<>();
        movies.add(mock(Movie.class));

        assertThrows(IllegalArgumentException.class,
                () -> new Rental(movies, null, 3));
    }

    @Test
    void shouldThrowRentalNegativeDayException() {
        Queue<Movie> movies = new LinkedList<>();
        movies.add(mock(Movie.class));
        Customer customer = mock(Customer.class);

        assertThrows(RentalNegativeDayException.class,
                () -> new Rental(movies, customer, -1));
    }
}
