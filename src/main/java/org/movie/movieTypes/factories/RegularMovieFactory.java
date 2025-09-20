package org.movie.movieTypes.factories;

import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.frequentRenterPoints.concretes.NoPoints;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.movieTypes.MovieFactory;
import org.movie.movieTypes.concreteTypes.RegularMovie;
import org.movie.pricing.concretes.PriceContext;
import org.movie.pricing.concretes.RegularPrice;

public class RegularMovieFactory implements MovieFactory {
    @Override
    public RegularMovie createMovie(String id, String title) throws MovieTitleException {
        PriceContext priceContext = new PriceContext(new RegularPrice());
        RenterPointsContext pointsContext = new RenterPointsContext(new NoPoints());
        return new RegularMovie(id, title, priceContext, pointsContext);
    }
}
