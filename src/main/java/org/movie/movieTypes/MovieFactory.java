package org.movie.movieTypes;

import org.movie.errorHanlding.movieException.MovieTitleException;

public interface MovieFactory {
    Movie createMovie( String id, String title) throws MovieTitleException;
}
