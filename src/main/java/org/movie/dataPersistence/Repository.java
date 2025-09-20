package org.movie.dataPersistence;

import java.util.List;
import java.util.Optional;

public interface Repository <T, ID>{
    void addItem(T item);
    List<T> getAllItems();
    Optional<T> getById(ID id);
    void updateItem(T item);
    void deleteItem(ID id);
}
