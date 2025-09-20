package org.movie.dataPersistence.repository;

import org.movie.dataPersistence.serializer.SerializerContext;
import org.movie.movieTypes.Movie;

public class MovieRepository extends GenericRepository<Movie, String> {

    public MovieRepository(String filePath, Class<Movie> type, SerializerContext<Movie> serializer) {
        super(filePath, type, serializer);
    }
}
