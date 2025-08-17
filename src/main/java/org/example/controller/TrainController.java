package org.example.controller;

import org.example.model.Train;
import org.example.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    private TrainService trainService;

    @GetMapping("/search")
    public ResponseEntity<?> searchTrains(@RequestParam String from, @RequestParam String to) {
        logger.info("Received search request for trains from: {} to: {}", from, to);
        try {
            List<Train> trains = trainService.findTrains(from, to);
            return ResponseEntity.ok(trains);
        } catch (Exception e) {
            logger.error("An error occurred during train search", e);
            // Return a 500 Internal Server Error with the exception message
            return ResponseEntity.status(500).body("Server Error: " + e.getMessage());
        }
    }
}