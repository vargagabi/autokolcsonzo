package com.example.autokolcsonzo.reservation;

import com.example.autokolcsonzo.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Elérhető adatok lekérdezéséhez.
     * @param start
     * @param end
     * @return
     */
    public List<Car> listAvailableCars(LocalDate start, LocalDate end){
        return reservationRepository.getAvailableCars(start,end);
    }

    public void saveRes(Reservation reservation){
        this.reservationRepository.save(reservation);
    }

    public List<Reservation> findAll(){return reservationRepository.findAll();}

    public void deleteDeactivated() {
        this.reservationRepository.deleteDeactivated();
    }
}
