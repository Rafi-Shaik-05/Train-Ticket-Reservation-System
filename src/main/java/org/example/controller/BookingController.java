package org.example.controller;

import org.example.dto.BookingDTO;
import org.example.model.Booking;
import org.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.parseLong(payload.get("userId").toString());
            Long trainId = Long.parseLong(payload.get("trainId").toString());
            String passengerName = payload.get("passengerName").toString();
            int passengerAge = Integer.parseInt(payload.get("passengerAge").toString());

            Booking newBooking = bookingService.createBooking(userId, trainId, passengerName, passengerAge);
            // Convert to DTO before sending the response
            return ResponseEntity.ok(new BookingDTO(newBooking));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok().body("Booking cancelled successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}