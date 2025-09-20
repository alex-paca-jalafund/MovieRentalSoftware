package org.movie.movieTypes.factories;

import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.frequentRenterPoints.concretes.BonusPoints;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.movieTypes.MovieFactory;
import org.movie.movieTypes.concreteTypes.NewReleaseMovie;
import org.movie.pricing.concretes.NewReleasePrice;
import org.movie.pricing.concretes.PriceContext;

public class NewReleaseMovieFactory implements MovieFactory {
    @Override
    public NewReleaseMovie createMovie(String id, String title) throws MovieTitleException {
        PriceContext priceContext = new PriceContext(new NewReleasePrice());
        RenterPointsContext pointsContext = new RenterPointsContext(new BonusPoints());
        return new NewReleaseMovie(id, title, priceContext, pointsContext);
    }
}
