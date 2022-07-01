package com.example.autokolcsonzo.api;

import com.example.autokolcsonzo.car.Car;
import com.example.autokolcsonzo.reservation.Reservation;
import com.example.autokolcsonzo.reservation.ReservationRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResApiController {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ResApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping(value = "/freeCars",params = {"startDate","endDate"})
    public ResponseEntity<List<Car>> freeCars(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return ResponseEntity
                .ok()
                .body(reservationRepository.getAvailableCars(LocalDate.parse(startDate),LocalDate.parse(endDate)));
    }

    @PostMapping(value = "/addRes")
    public ResponseEntity<Reservation> addRes(@RequestBody Reservation reservation){
        this.reservationRepository.save(reservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

}
