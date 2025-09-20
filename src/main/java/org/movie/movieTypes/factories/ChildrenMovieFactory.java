package org.movie.movieTypes.factories;

import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.frequentRenterPoints.concretes.RegularPoints;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.movieTypes.MovieFactory;
import org.movie.movieTypes.concreteTypes.ChildrenMovie;
import org.movie.pricing.concretes.ChildrenPrice;
import org.movie.pricing.concretes.PriceContext;

public class ChildrenMovieFactory implements MovieFactory {
    @Override
    public ChildrenMovie createMovie(String id, String title) throws MovieTitleException {
        PriceContext priceContext = new PriceContext(new ChildrenPrice());
        RenterPointsContext pointsContext = new RenterPointsContext(new RegularPoints());
        return new ChildrenMovie(id, title, priceContext, pointsContext);
    }
}
