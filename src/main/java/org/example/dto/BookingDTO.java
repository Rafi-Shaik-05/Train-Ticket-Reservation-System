package org.example.dto;

import lombok.Data;
import org.example.model.Booking;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private String passengerName;
    private int passengerAge;
    private LocalDateTime bookingDate;
    private String status;
    private TrainDTO train;
    private UserDTO user;

    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.passengerName = booking.getPassengerName();
        this.passengerAge = booking.getPassengerAge();
        this.bookingDate = booking.getBookingDate();
        this.status = booking.getStatus();
        this.train = new TrainDTO(booking.getTrain());
        this.user = new UserDTO(booking.getUser());
    }
}