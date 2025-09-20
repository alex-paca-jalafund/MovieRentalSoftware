package org.movie.movieTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.movie.errorHanlding.movieException.MovieTitleException;
import org.movie.errorHanlding.priceException.MaxDaysPriceException;
import org.movie.errorHanlding.priceException.MinDaysPriceException;
import org.movie.errorHanlding.RenterPointsException.RenterPointsCustomerException;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.movieTypes.concreteTypes.ChildrenMovie;
import org.movie.movieTypes.concreteTypes.NewReleaseMovie;
import org.movie.movieTypes.concreteTypes.RegularMovie;
import org.movie.pricing.concretes.PriceContext;
import org.movie.rental.Customer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = RegularMovie.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegularMovie.class, name = "Regular"),
        @JsonSubTypes.Type(value = NewReleaseMovie.class, name = "New"),
        @JsonSubTypes.Type(value = ChildrenMovie.class, name = "Children")
})
public abstract class Movie {
    String title;
    String id;
    @JsonIgnore PriceContext price;
    @JsonIgnore RenterPointsContext points;
    public Movie(){}
    public Movie(String id, String title, PriceContext price, RenterPointsContext points) throws MovieTitleException {
        if (title == null || title.trim().isEmpty()) {
            throw new MovieTitleException("Title cannot be null or empty");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price context cannot be null");
        }
        if (points == null) {
            throw new IllegalArgumentException("Points context cannot be null");
        }
        this.id = id;
        this.title = title;
        this.price = price;
        this.points = points;
    }
    public String getId(){return this.id;}
    public String getTitle(){
        return title;
    }
    public double getPrice(int daysRented) throws MinDaysPriceException, MaxDaysPriceException {
        return price.calculatePrice(daysRented);
    }

    public int getPoints(Customer customer) throws RenterPointsCustomerException {
        return points.calculatePoints(customer);
    }
}
