package com.example.autokolcsonzo.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * A foglalásho tárolja a kezdeti adatokat amiket nem a felhasználó ad meg.
 *
 */
public class ResInfo {
    private String licensePlate;
    private int dailyPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private int days;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public ResInfo(String licensePlate, int dailyPrice, LocalDate startDate, LocalDate endDate, int days) {
        this.licensePlate = licensePlate;
        this.dailyPrice = dailyPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public ResInfo(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public ResInfo(){

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    @Override
    public String toString() {
        return "ResInfo{" +
                "licensePlate='" + licensePlate + '\'' +
                ", dailyPrice=" + dailyPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", days=" + days +
                '}';
    }
}
