package org.example.repository;

import org.example.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    // Derived query to find all trains matching a specific route.
    List<Train> findByFromStationAndToStation(String fromStation, String toStation);
}