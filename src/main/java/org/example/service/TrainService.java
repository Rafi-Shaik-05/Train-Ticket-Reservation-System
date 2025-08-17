package org.example.service;

import org.example.model.Train;
import org.example.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    private static final Logger logger = LoggerFactory.getLogger(TrainService.class);

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> findTrains(String fromStation, String toStation) {
        List<Train> foundTrains = trainRepository.findByFromStationAndToStation(fromStation, toStation);
        logger.info("Found {} trains for route {} -> {}", foundTrains.size(), fromStation, toStation);
        return foundTrains;
    }
}