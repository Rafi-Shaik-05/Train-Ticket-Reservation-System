package org.example.controller;

import org.example.model.Train;
import org.example.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/search")
    public ResponseEntity<List<Train>> searchTrains(@RequestParam String from, @RequestParam String to) {
        List<Train> trains = trainService.findTrains(from, to);
        return ResponseEntity.ok(trains);
    }
}
