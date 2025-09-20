package repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.movie.dataPersistence.mapper.RentalTotalMapper;
import org.movie.dataPersistence.mapper.dto.FlatRentalTotal;
import org.movie.dataPersistence.repository.CustomerRepository;
import org.movie.dataPersistence.repository.FlatRentalTotalRepository;
import org.movie.dataPersistence.repository.MovieRepository;
import org.movie.dataPersistence.serializer.CsvSerializer;
import org.movie.dataPersistence.serializer.SerializerContext;
import org.movie.errorHanlding.RentalException.RentalNegativeDayException;
import org.movie.errorHanlding.customerException.CustomerUserNameException;
import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.movieTypes.factories.NewReleaseMovieFactory;
import org.movie.movieTypes.factories.RegularMovieFactory;
import org.movie.rental.Customer;
import org.movie.movieTypes.Movie;
import org.movie.rental.Rental;
import org.movie.rental.RentalTotal;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryCsvTest {

    @TempDir
    Path tempDir;

    private CustomerRepository customerRepo;
    private MovieRepository movieRepo;
    private FlatRentalTotalRepository flatrentalTotalRepo;

    @BeforeEach
    void setUp() {
        File customerFile = tempDir.resolve("customers.csv").toFile();
        File movieFile = tempDir.resolve("movies.csv").toFile();
        File totalsFile = tempDir.resolve("totals.csv").toFile();

        customerRepo = new CustomerRepository(
                customerFile.getAbsolutePath(),
                Customer.class,
                new SerializerContext<>(new CsvSerializer<>(Customer.class))
        );

        movieRepo = new MovieRepository(
                movieFile.getAbsolutePath(),
                Movie.class,
                new SerializerContext<>(new CsvSerializer<>(Movie.class))
        );

        flatrentalTotalRepo = new FlatRentalTotalRepository(
                totalsFile.getAbsolutePath(),
                FlatRentalTotal.class,
                new SerializerContext<>(new CsvSerializer<>(FlatRentalTotal.class))
        );
    }

    @Test
    void testCustomerRepositoryCrud() throws CustomerUserNameException {
        Customer c1 = new Customer("id1", "Alex");
        Customer c2 = new Customer("id2", "Maria");

        customerRepo.addItem(c1);
        customerRepo.addItem(c2);

        List<Customer> all = customerRepo.getAllItems();
        assertEquals(2, all.size());

        Optional<Customer> found = customerRepo.getById("id1");
        assertTrue(found.isPresent());
        assertEquals("Alex", found.get().getUserName());

        Customer updated = new Customer("id1", "Alex Updated");
        customerRepo.updateItem(updated);
        assertEquals("Alex Updated", customerRepo.getById("id1").get().getUserName());

        customerRepo.deleteItem("id2");
        assertEquals(1, customerRepo.getAllItems().size());
    }

    @Test
    void testMovieRepositoryCrud() throws MovieTitleException {
        RegularMovieFactory factory = new RegularMovieFactory();
        Movie m1 = factory.createMovie("121","Inception");
        Movie m2 = factory.createMovie("122","Batman");
        Movie m3 = factory.createMovie("123","Moby");

        movieRepo.addItem(m1);
        movieRepo.addItem(m2);
        movieRepo.addItem(m3);

        assertEquals(3, movieRepo.getAllItems().size());

        Movie updated = factory.createMovie("121","Inception updated");
        movieRepo.updateItem(updated);
        assertEquals("Inception updated", movieRepo.getById("121").get().getTitle());

        movieRepo.deleteItem("122");
        assertEquals(2, movieRepo.getAllItems().size());
    }

    @Test
    void testRentalTotalRepositorySaveAndRead() throws RentalNegativeDayException, MovieTitleException, CustomerUserNameException {
        Queue<Movie> movies = new LinkedList<>();

        NewReleaseMovieFactory factory = new NewReleaseMovieFactory();
        Movie movieTest1 = factory.createMovie("222", "PennyElWise");
        Movie movieTest2 = factory.createMovie("223", "Gambito");

        movies.add(movieTest1);
        movies.add(movieTest2);

        Customer customer = new Customer("333", "Sabalo");
        Rental rental = new Rental(movies, customer, 15);
        RentalTotal totals = new RentalTotal("1",rental);
        totals.calculateTotals();

        flatrentalTotalRepo.addItem(RentalTotalMapper.RentalTotalToFlat(totals));

        List<FlatRentalTotal> readBack = flatrentalTotalRepo.getAllItems();
        assertEquals(1, readBack.size());
        assertEquals(customer.getId(), readBack.get(0).getCustomerId());
        assertEquals(customer.getUserName(), readBack.get(0).getCustomerName());
    }
}


