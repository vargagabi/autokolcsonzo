package com.example.autokolcsonzo.reservation;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * A foglalásokat tároló tábla.
 */
@Table
@Entity
public class Reservation {
    @Id
    @SequenceGenerator(
            name ="res_seq",
            sequenceName = "res_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "res_seq"
    )
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    @NonNull
    private int fullPrice;
    @NonNull
    private String licensePlate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate endDate;

    public Reservation() {
    }

    public Reservation(int id, @NonNull String name, @NonNull String email, @NonNull String address, @NonNull String phone, int fullPrice, @NonNull String licensePlate, @NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.fullPrice = fullPrice;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fullPrice=" + fullPrice +
                ", licensePlate='" + licensePlate + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }

    @NonNull
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(@NonNull String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @NonNull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NonNull LocalDate startDate) {
        this.startDate = startDate;
    }

    @NonNull
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NonNull LocalDate endDate) {
        this.endDate = endDate;
    }
}
