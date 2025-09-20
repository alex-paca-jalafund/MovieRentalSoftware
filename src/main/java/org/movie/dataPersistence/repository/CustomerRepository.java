package org.movie.dataPersistence.repository;

import org.movie.dataPersistence.serializer.SerializerContext;
import org.movie.rental.Customer;

public class CustomerRepository extends GenericRepository<Customer, String> {

    public CustomerRepository(String filePath, Class<Customer> type, SerializerContext<Customer> seralizer) {
        super(filePath, type, seralizer);
    }

}
