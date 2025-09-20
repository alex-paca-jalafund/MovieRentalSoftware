package org.movie.dataPersistence.repository;

import org.movie.dataPersistence.serializer.SerializerContext;
import org.movie.rental.RentalTotal;

public class RentalTotalRepository extends GenericRepository<RentalTotal,String> {

    public RentalTotalRepository(String filePath, Class<RentalTotal> type, SerializerContext<RentalTotal> serializer) {
        super(filePath, type, serializer);
    }
}
