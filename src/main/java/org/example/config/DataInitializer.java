package org.example.config;

import org.example.model.Train;
import org.example.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public void run(String... args) throws Exception {
        if (trainRepository.count() == 0) {
            logger.info("No train data found. Initializing sample data...");

            Train train1 = new Train();
            train1.setTrainNumber("T123");
            train1.setName("Rajdhani Express");
            train1.setFromStation("New Delhi (NDLS)");
            train1.setToStation("Mumbai (CST)");
            train1.setTotalSeats(100);
            train1.setAvailableSeats(100);

            Train train2 = new Train();
            train2.setTrainNumber("T789");
            train2.setName("Shatabdi Express");
            train2.setFromStation("Kolkata (HWH)");
            train2.setToStation("New Delhi (NDLS)");
            train2.setTotalSeats(120);
            train2.setAvailableSeats(120);

            Train train3 = new Train();
            train3.setTrainNumber("T456");
            train3.setName("Duronto Express");
            train3.setFromStation("Mumbai (CST)");
            train3.setToStation("Kolkata (HWH)");
            train3.setTotalSeats(80);
            train3.setAvailableSeats(80);

            Train train4 = new Train();
            train4.setTrainNumber("T101");
            train4.setName("Coromandel Express");
            train4.setFromStation("Chennai (MAS)");
            train4.setToStation("Kolkata (HWH)");
            train4.setTotalSeats(90);
            train4.setAvailableSeats(90);

            trainRepository.saveAll(List.of(train1, train2, train3, train4));
            logger.info("Sample train data initialized successfully. Total trains: {}", trainRepository.count());
        } else {
            logger.info("Train data already exists. Skipping initialization. Total trains: {}", trainRepository.count());
        }
    }
}
