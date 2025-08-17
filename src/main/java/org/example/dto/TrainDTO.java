package org.example.dto;

import lombok.Data;
import org.example.model.Train;

@Data
public class TrainDTO {
    private Long id;
    private String trainNumber;
    private String name;
    private String fromStation;
    private String toStation;

    public TrainDTO(Train train) {
        this.id = train.getId();
        this.trainNumber = train.getTrainNumber();
        this.name = train.getName();
        this.fromStation = train.getFromStation();
        this.toStation = train.getToStation();
    }
}
