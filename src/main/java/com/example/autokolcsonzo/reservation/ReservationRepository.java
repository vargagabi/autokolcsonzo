package com.example.autokolcsonzo.reservation;

import com.example.autokolcsonzo.car.Car;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /**
     * Két dátum között elérhető autókat kérdezi le az adatbázisból
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("SELECT new com.example.autokolcsonzo.car.Car(c.licensePlate,c.dailyPrice,c.disabled,c.picture) FROM Car c WHERE " +
            "c.disabled IS false  AND c.licensePlate NOT IN " +
            "(SELECT c2.licensePlate FROM Car c2, Reservation  r WHERE c.licensePlate=r.licensePlate AND" +
            "(r.endDate >= ?1 AND r.startDate <= ?2))")
    @Transactional
    List<Car> getAvailableCars(LocalDate startDate, LocalDate endDate);

    /**
     * Deaktiválás esetén az adott autóra vonatkozó foglalásokat törli az adatbázisból
     */
    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.licensePlate IN (SELECT c.licensePlate FROM Car c WHERE c.disabled IS true)")
    @Transactional
    void deleteDeactivated();
}
