package org.movie.dataPersistence.repository;

import org.movie.dataPersistence.mapper.dto.FlatRentalTotal;
import org.movie.dataPersistence.serializer.SerializerContext;

public class FlatRentalTotalRepository extends GenericRepository<FlatRentalTotal, String> {

    public FlatRentalTotalRepository(String filePath, Class<FlatRentalTotal> type, SerializerContext<FlatRentalTotal> seralizer) {
        super(filePath, type, seralizer);
    }

}