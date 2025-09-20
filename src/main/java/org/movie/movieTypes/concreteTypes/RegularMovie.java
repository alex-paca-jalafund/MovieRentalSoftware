package org.movie.movieTypes.concreteTypes;

import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.movieTypes.Movie;
import org.movie.pricing.concretes.PriceContext;

public class RegularMovie extends Movie {
    public RegularMovie() {
        super();
    }
    public RegularMovie(String id, String title, PriceContext price, RenterPointsContext points) throws MovieTitleException {
        super(id, title, price, points);
    }
}
