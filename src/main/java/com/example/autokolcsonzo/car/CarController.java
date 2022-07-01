package com.example.autokolcsonzo.car;

import com.example.autokolcsonzo.beans.CarInfo;
import com.example.autokolcsonzo.reservation.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CarController {

    private final CarService carService;
    private final ReservationService reservationService;

    public CarController(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    /**
     * Az autók kilistázásáért felel az admin oldalon
     * @param model
     * @return
     */
    @GetMapping("/admin/cars")
    public String carList(Model model){
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("newCar",new CarInfo());
        return "admin";
    }

    /**
     * Új autó hozzáadásáért felel.
     * @param newCar az autó adatai, a kép itt még MultiparFile típusú.
     * @return
     */
    @PostMapping("/admin/saveCar")
    public String saveCar(@ModelAttribute("newCar") CarInfo newCar){
        try {
            this.carService.save(newCar.getPicture(), newCar.getLicensePlate(),newCar.getDailyPrice(),newCar.isDisabled());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/cars";
    }

    /**
     * Az autók deaktiválásáért/aktiválásáért felel.
     * @param license
     * @param price
     * @param disabled
     * @return
     */
    @GetMapping("/admin/cars/carActivation")
    public String carActivation(@RequestParam("a1") String license, @RequestParam("a2") int price, @RequestParam("a3") boolean disabled){
        this.carService.modifyCar(new Car(license,price,!disabled));
        this.reservationService.deleteDeactivated();
        return "redirect:/admin/cars";
    }

    /**
     * A módosítandó autó adatait adja át az admin.html-nek hogí az elérhető legyen majd a form-ban.
     * @param model
     * @param license
     * @param price
     * @param disabled
     * @return
     */
    @GetMapping("/admin/cars/carModifying")
    public String carModifying(Model model, @RequestParam("a1") String license, @RequestParam("a2")  int price,@RequestParam("a3") boolean disabled){
        model.addAttribute("carToMod", new Car(license,price,disabled));
        return "admin";
    }

    /**
     * A módosított autó új adatait menti le az adatbázisba, ha kép is lett feltöltve, akkor azt lecseréli az újra.
     * Abban az esetben, ha az autót deaktiválták, akkor az arra az autóra vonatkozó foglalásokat törli.
     * @param newCar A form-ban az átadott objektum.
     * @return
     */
    @PostMapping("/admin/modifyCar")
    public String modifyCar(@ModelAttribute("carToMod") CarInfo newCar)  {
        if(!newCar.getPicture().isEmpty()){
            try {
                this.carService.modifyCarWithPic(newCar.getLicensePlate(),newCar.getDailyPrice(),newCar.isDisabled(), newCar.getPicture());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            this.carService.modifyCar(new Car (newCar.getLicensePlate(),newCar.getDailyPrice(),newCar.isDisabled()));
        }
        if(newCar.isDisabled()){
            this.reservationService.deleteDeactivated();
        }
        return "redirect:/admin/cars";
    }
}
