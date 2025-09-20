package org.movie.dataPersistence.repository;

import org.movie.dataPersistence.Repository;
import org.movie.dataPersistence.serializer.SerializerContext;
import org.movie.errorHanlding.repositoryException.RepositoryException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class GenericRepository<T, ID> implements Repository<T, ID> {

    private final String filePath;
    private final Class<T> type;
    private final SerializerContext<T> serializer;

    public GenericRepository(String filePath, Class<T> type, SerializerContext<T> serializer) {
        this.filePath = filePath;
        this.type = type;
        this.serializer = serializer;
    }

    @Override
    public void addItem(T item) {
        List<T> items = getAllItems();
        items.add(item);
        try {
            writeToFile(items);
        } catch (IOException e) {
            throw new RepositoryException("Failed to add item: " + item, e);
        }
    }

    @Override
    public List<T> getAllItems() {
        try {
            return serializer.read(new File(filePath), type);
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<T> getById(ID id) {
        try {
            return getAllItems().stream()
                    .filter(item -> Objects.equals(extractId(item), id))
                    .findFirst();
        } catch (Exception e) {
            throw new RepositoryException("Failed to get item by ID: " + id, e);
        }
    }

    @Override
    public void updateItem(T item) {
        List<T> items = getAllItems();
        ID id = extractId(item);
        boolean found = false;
        for (int i = 0; i < items.size(); i++) {
            if (Objects.equals(extractId(items.get(i)), id)) {
                items.set(i, item);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RepositoryException("Item with ID " + id + " not found for update");
        }
        try {
            writeToFile(items);
        } catch (IOException e) {
            throw new RepositoryException("Failed to update item: " + item, e);
        }
    }

    @Override
    public void deleteItem(ID id) {
        List<T> items = getAllItems()
                .stream()
                .filter(item -> !Objects.equals(extractId(item), id))
                .collect(Collectors.toList());
        try {
            writeToFile(items);
        } catch (IOException e) {
            throw new RepositoryException("Failed to delete item with ID: " + id, e);
        }
    }

    private void writeToFile(List<T> items) throws IOException {
        serializer.write(new File(filePath), items);
    }

    @SuppressWarnings("unchecked")
    private ID extractId(T item) {
        try {
            var method = item.getClass().getMethod("getId");
            Object idValue = method.invoke(item);
            if (idValue == null) {
                throw new RepositoryException("getId() returned null for item: " + item);
            }
            return (ID) idValue;
        } catch (Exception e) {
            throw new RepositoryException("Failed to extract ID from: " + item.getClass().getName(), e);
        }
    }
}
