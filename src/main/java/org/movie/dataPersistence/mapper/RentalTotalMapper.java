package org.movie.dataPersistence.mapper;

import org.movie.dataPersistence.mapper.dto.FlatRentalTotal;
import org.movie.movieTypes.Movie;
import org.movie.rental.RentalTotal;

import java.util.stream.Collectors;

public class RentalTotalMapper {

    public static FlatRentalTotal RentalTotalToFlat(RentalTotal rentalTotal){
        FlatRentalTotal flatTotal = new FlatRentalTotal();
        flatTotal.setId(rentalTotal.getId());
        flatTotal.setTotalPoints(rentalTotal.getTotalPoints());
        flatTotal.setTotalAmount(rentalTotal.getTotalAmount());
        flatTotal.setCustomerId(rentalTotal.getRental().getCustomer().getId());
        flatTotal.setCustomerName(rentalTotal.getRental().getCustomer().getUserName());
        flatTotal.setDaysRent(rentalTotal.getRental().getDaysRent());
        String movies = rentalTotal.getRental().getMovies()
                .stream().map(Movie::getTitle).collect(Collectors.joining("|"));
        flatTotal.setMovies(movies);

        return flatTotal;
    }

}
