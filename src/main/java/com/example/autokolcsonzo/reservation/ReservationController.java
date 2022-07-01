package com.example.autokolcsonzo.reservation;

import com.example.autokolcsonzo.beans.ResInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    /**
     * A foglaláshoz a kezdeti adatok átadásáért felel.
     * Az átadott adatok a foglalási form-ban már nem módosíthatóak.
     * @param license
     * @param startDate
     * @param endDate
     * @param price
     * @param model
     * @return
     */
    @GetMapping(value = "/cars/reservation",params = {"a1","a2","a3","a4"})
    public String reservation(@RequestParam("a1") String license, @RequestParam("a2")String startDate,
                              @RequestParam("a3")String endDate,@RequestParam("a4")int price,Model model){
        ResInfo carR = new ResInfo(license,price,LocalDate.parse(startDate),LocalDate.parse(endDate), Period.between(LocalDate.parse(startDate),LocalDate.parse(endDate)).getDays()+1);
        model.addAttribute("resObj", new Reservation());
        model.addAttribute("carR",carR);
        return "cars";
    }


    /**
     * A foglalási adatok mentéséért felel az adatbázisba.
     * @param reservation a form-ban az átadott objektum
     * @param model
     * @return
     */
    @PostMapping("/cars/saveRes")
    public String saveRes(@ModelAttribute("resObj") Reservation reservation, Model model){
        reservationService.saveRes(reservation);
        model.addAttribute("carR", new ResInfo());
        return "redirect:/cars";
    }


    /**
     * A foglalások listázásáért felel az admin oldalon.
     * @param model
     * @return
     */
    @GetMapping("/admin/reservations")
    public String resList(Model model){
        model.addAttribute("reservations",reservationService.findAll());
        return "admin";
    }
}
