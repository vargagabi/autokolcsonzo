package com.example.autokolcsonzo.car;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Egy adutó adatait tároló tábla
 */
@Entity
@Table
public class Car {

    @Id
    private String licensePlate;
    @NonNull
    private int dailyPrice;
    @NonNull
    private boolean disabled;
    @NonNull
    @Lob
    private String picture;

    public Car() {
    }

    public Car(String licensePlate, int dailyPrice, boolean disabled) {
        this.licensePlate = licensePlate;
        this.dailyPrice = dailyPrice;
        this.disabled = disabled;
    }

    public Car(String licensePlate, int dailyPrice, boolean disabled, @NonNull String picture) {
        this.licensePlate = licensePlate;
        this.dailyPrice = dailyPrice;
        this.disabled = disabled;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", dailyPrice=" + dailyPrice +
                ", disabled=" + disabled +
                ", picture='" + picture + '\'' +
                '}';
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @NonNull
    public String getPicture() {
        return picture;
    }

    public void setPicture(@NonNull String picture) {
        this.picture = picture;
    }
}
