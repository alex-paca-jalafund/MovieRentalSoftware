package org.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.movie.dataPersistence.mapper.RentalTotalMapper;
import org.movie.dataPersistence.mapper.dto.FlatRentalTotal;
import org.movie.dataPersistence.repository.FlatRentalTotalRepository;
import org.movie.dataPersistence.repository.RentalTotalRepository;
import org.movie.dataPersistence.serializer.ObjectMapperSerializer;
import org.movie.dataPersistence.serializer.SerializerContext;
import org.movie.dataPersistence.serializer.CsvSerializer;
import org.movie.movieTypes.concreteTypes.NewReleaseMovie;
import org.movie.movieTypes.factories.NewReleaseMovieFactory;
import org.movie.rental.Customer;
import org.movie.rental.Rental;
import org.movie.rental.RentalTotal;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("=== DEMO CSV ===");
        demoCsv();

        System.out.println("\n=== DEMO JSON ===");
        demoJson();

        System.out.println("\n=== DEMO XML ===");
        demoXml();
    }

    private static void demoCsv() throws Exception {
        File file = new File("demo/csv_demo.csv");
        file.getParentFile().mkdirs();

        FlatRentalTotalRepository repoCsv = new FlatRentalTotalRepository(file.getAbsolutePath(),
                FlatRentalTotal.class,
                new SerializerContext<>(new CsvSerializer<>(FlatRentalTotal.class)));

        RentalTotal sample = createSampleRentalTotals();
        repoCsv.addItem(RentalTotalMapper.RentalTotalToFlat(sample));

        List<FlatRentalTotal> read = repoCsv.getAllItems();
        read.forEach(rt -> System.out.println("CSV Read: ID=" + rt.getId() + ", Total=" + rt.getTotalAmount()));
    }

    private static void demoJson() throws Exception {
        File file = new File("demo/json_demo.json");
        file.getParentFile().mkdirs();

        RentalTotalRepository repoJson = new RentalTotalRepository(
                file.getAbsolutePath(),
                RentalTotal.class,
                new SerializerContext<>(new ObjectMapperSerializer<>(new ObjectMapper()))
        ) {};

        RentalTotal sample = createSampleRentalTotals();
        repoJson.addItem(sample);

        List<RentalTotal> read = repoJson.getAllItems();
        read.forEach(rt -> System.out.println("JSON Read: ID=" + rt.getId() + ", Total=" + rt.getTotalAmount()));
    }

    private static void demoXml() throws Exception {
        File file = new File("demo/xml_demo.xml");
        file.getParentFile().mkdirs();

        RentalTotalRepository repoXml = new RentalTotalRepository(
                file.getAbsolutePath(),
                RentalTotal.class,
                new SerializerContext<>(new ObjectMapperSerializer<>(new XmlMapper()))
        ) {};

        RentalTotal sample = createSampleRentalTotals();
        repoXml.addItem(sample);

        List<RentalTotal> read = repoXml.getAllItems();
        read.forEach(rt -> System.out.println("XML Read: ID=" + rt.getId() + ", Total=" + rt.getTotalAmount()));
    }

    private static RentalTotal createSampleRentalTotals() throws Exception {
        Queue<NewReleaseMovie> movies = new LinkedList<>();
        NewReleaseMovieFactory factory = new NewReleaseMovieFactory();

        movies.add(factory.createMovie("1", "Inception"));
        movies.add(factory.createMovie("2", "Interestelar"));

        Customer customer = new Customer("123", "Alex");
        Rental rental = new Rental((Queue) movies, customer, 5);

        RentalTotal totals = new RentalTotal("totals-1", rental);
        totals.calculateTotals();
        return totals;
    }
}
