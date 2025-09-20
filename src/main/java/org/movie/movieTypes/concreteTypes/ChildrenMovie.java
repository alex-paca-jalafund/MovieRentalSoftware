package org.movie.movieTypes.concreteTypes;

import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.movieTypes.Movie;
import org.movie.pricing.concretes.PriceContext;

public class ChildrenMovie extends Movie {
    public ChildrenMovie() {
        super();
    }
    public ChildrenMovie(String id, String title, PriceContext price, RenterPointsContext points) throws MovieTitleException {
        super(id, title, price, points);
    }
}
