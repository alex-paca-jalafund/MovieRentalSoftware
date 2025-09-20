package org.movie.dataPersistence.serializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DataSerializer<T> {
    List<T> read(File file, Class<T> type) throws IOException;
    void write(File file, List<T> items) throws IOException;
}

