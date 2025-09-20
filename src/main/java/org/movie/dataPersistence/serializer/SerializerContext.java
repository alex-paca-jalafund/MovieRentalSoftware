package org.movie.dataPersistence.serializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializerContext<T> {
    DataSerializer<T> serializer;

    public SerializerContext(DataSerializer<T> serializer){
        this.serializer = serializer;
    }

    public List<T> read(File file, Class<T> type) throws IOException {
       return serializer.read(file,type);
    }

    public void write(File file, List<T> items) throws IOException {
       serializer.write(file,items);
    }

}
