package com.example.autokolcsonzo.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public List<Car> findAll(){
        return carRepository.findAll(Sort.by(Sort.Direction.ASC,"licensePlate"));
    }

    public void save(MultipartFile picture, String licensePlate, int price, boolean disabled) throws IOException {

        this.carRepository.save(new Car(licensePlate,price,disabled, new String(Base64.getEncoder().encode(picture.getBytes()), "UTF-8")));
    }

    public void modifyCar(Car newCar){
        System.out.println(newCar.toString());
        this.carRepository.modifyCar(newCar.getLicensePlate(),newCar.getDailyPrice(),newCar.isDisabled());
    }

    public void modifyCarWithPic(String licensePlate, int dailyPrice, boolean disabled, MultipartFile picture) throws IOException {
        this.carRepository.modifyCarWithPicture(licensePlate,dailyPrice,disabled, new String(Base64.getEncoder().encode(picture.getBytes()), "UTF-8"));
    }
}
