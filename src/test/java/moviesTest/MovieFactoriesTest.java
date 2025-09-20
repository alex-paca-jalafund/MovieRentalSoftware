package moviesTest;

import org.junit.jupiter.api.Test;
import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.movieTypes.concreteTypes.ChildrenMovie;
import org.movie.movieTypes.concreteTypes.NewReleaseMovie;
import org.movie.movieTypes.concreteTypes.RegularMovie;
import org.movie.movieTypes.factories.ChildrenMovieFactory;
import org.movie.movieTypes.factories.NewReleaseMovieFactory;
import org.movie.movieTypes.factories.RegularMovieFactory;

import static org.junit.jupiter.api.Assertions.*;

class MovieFactoriesTest {

    @Test
    void testChildrenMovieFactoryCreatesChildrenMovie() throws MovieTitleException {
        ChildrenMovieFactory factory = new ChildrenMovieFactory();
        ChildrenMovie movie = factory.createMovie("123","The Lion King");

        assertNotNull(movie);
        assertEquals("The Lion King", movie.getTitle());
        assertTrue(movie instanceof ChildrenMovie);
    }

    @Test
    void testNewReleaseMovieFactoryCreatesNewReleaseMovie() throws MovieTitleException {
        NewReleaseMovieFactory factory = new NewReleaseMovieFactory();
        NewReleaseMovie movie = factory.createMovie("123","Avengers: Endgame");

        assertNotNull(movie);
        assertEquals("Avengers: Endgame", movie.getTitle());
        assertTrue(movie instanceof NewReleaseMovie);
    }

    @Test
    void testRegularMovieFactoryCreatesRegularMovie() throws MovieTitleException {
        RegularMovieFactory factory = new RegularMovieFactory();
        RegularMovie movie = factory.createMovie("123","The Godfather");

        assertNotNull(movie);
        assertEquals("The Godfather", movie.getTitle());
        assertTrue(movie instanceof RegularMovie);
    }

    @Test
    void testFactoryThrowsExceptionWhenTitleIsNull() {
        ChildrenMovieFactory factory = new ChildrenMovieFactory();

        assertThrows(MovieTitleException.class, () -> factory.createMovie("123",null));
    }

    @Test
    void testFactoryThrowsExceptionWhenTitleIsEmpty() {
        NewReleaseMovieFactory factory = new NewReleaseMovieFactory();

        assertThrows(MovieTitleException.class, () -> factory.createMovie("123", " "));
    }
}

