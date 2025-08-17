package org.example.service;

import org.example.dto.BookingDTO;
import org.example.model.Booking;
import org.example.model.Train;
import org.example.model.User;
import org.example.repository.BookingRepository;
import org.example.repository.TrainRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<BookingDTO> getUserBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        // Convert the list of Booking entities to a list of BookingDTOs
        return bookings.stream()
                .map(BookingDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Booking createBooking(Long userId, Long trainId, String passengerName, int passengerAge) throws Exception {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new Exception("Train not found."));

        if (train.getAvailableSeats() <= 0) {
            throw new Exception("No seats available on this train.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found."));

        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

        Booking newBooking = new Booking();
        newBooking.setUser(user);
        newBooking.setTrain(train);
        newBooking.setPassengerName(passengerName);
        newBooking.setPassengerAge(passengerAge);
        newBooking.setBookingDate(LocalDateTime.now());
        newBooking.setStatus("CONFIRMED");

        return bookingRepository.save(newBooking);
    }

    @Transactional
    public void cancelBooking(Long bookingId) throws Exception {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new Exception("Booking not found."));

        Train train = booking.getTrain();
        train.setAvailableSeats(train.getAvailableSeats() + 1);
        trainRepository.save(train);

        bookingRepository.delete(booking);
    }
}