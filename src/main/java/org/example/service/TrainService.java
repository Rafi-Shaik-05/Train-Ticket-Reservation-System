package org.example.service;

import org.example.model.Train;
import org.example.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> findTrains(String fromStation, String toStation) {
        return trainRepository.findByFromStationAndToStation(fromStation, toStation);
    }
}