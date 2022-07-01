package com.example.autokolcsonzo;

import com.example.autokolcsonzo.beans.ResInfo;
import com.example.autokolcsonzo.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class MainController {
    private final ReservationService reservationService;

    @Autowired
    public MainController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String viewIndex(){
        return "index";
    }

    @GetMapping("/admin")
    public String adminView(){
        return "admin";
    }

    @GetMapping("/cars")
    public String init(Model model){
        model.addAttribute("carR", new ResInfo());
        return "cars";
    }


    @GetMapping(value = "/cars",params = {"startDate","endDate"})
    public String getCars(@RequestParam("startDate") String startDate, @RequestParam("endDate")String endDate,Model model){
        if(startDate.isEmpty() || endDate.isEmpty()){
            return "redirect:/cars";
        }
        LocalDate startD,endD;
        try{
            startD = LocalDate.parse(startDate);
            endD = LocalDate.parse(endDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/cars";
        }
        if(startD.isAfter(endD) || startD.isBefore(LocalDate.now())){
            return "redirect:/cars";
        }
        model.addAttribute("cars", reservationService.listAvailableCars(startD, endD));
        model.addAttribute("carR", new ResInfo(startD,endD));
        return "cars";
    }

}
