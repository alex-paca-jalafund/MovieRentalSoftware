package org.movie.dataPersistence.serializer;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvSerializer<T> implements DataSerializer<T> {
    private final CsvMapper mapper = new CsvMapper();
    private final Class<T> type;

    public CsvSerializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> read(File file, Class<T> type) throws IOException {
        if (!file.exists()) return new ArrayList<>();
        CsvSchema schema = mapper.schemaFor(type).withHeader();
        try (var it = mapper.readerFor(type).with(schema).readValues(file)) {
            return (List<T>) it.readAll();
        }
    }


    @Override
    public void write(File file, List<T> items) throws IOException {
        CsvSchema schema = mapper.schemaFor(type).withHeader();
        ObjectWriter writer = mapper.writer(schema);
        try (FileWriter out = new FileWriter(file)) {
            writer.writeValue(out, items);
        }
    }


}

