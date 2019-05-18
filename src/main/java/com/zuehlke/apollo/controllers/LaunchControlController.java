package com.zuehlke.apollo.controllers;

import com.zuehlke.apollo.domain.Astronaut;
import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import com.zuehlke.apollo.services.AstronautService;
import com.zuehlke.apollo.services.RocketShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class LaunchControlController {

    private final AstronautService astronautService;
    private final RocketShipService rocketShipService;

    public LaunchControlController(AstronautService astronautService, RocketShipService rocketShipService) {
        this.astronautService = astronautService;
        this.rocketShipService = rocketShipService;
    }

    @RequestMapping("/launchControl")
    public String index(Model model) {
        model.addAttribute("rocketShips", rocketShipService.findAll());
        model.addAttribute("astronauts", astronautService.findByRocketShipNull());
        return "launchcontrol";
    }

    @PostMapping("/assignToShip/{id}")
    public String assignToShip(@PathVariable("id") String id){
        Astronaut astronaut = astronautService.findById(Long.valueOf(id));
        RocketShip shipWithSeatsLeft = rocketShipService.findAll().stream().filter(rocketShip -> {
            return rocketShip.getStatus().equals(RocketShipStatus.READY)
                    && rocketShip.getAssignedAstronauts().size() < rocketShip.getCapacity();
        }).findFirst().get();

        shipWithSeatsLeft.assignAstronaut(astronaut);
        astronautService.save(astronaut);
        return "redirect:/launchControl";
    }

    @PostMapping("/launchShip/{id}")
    public String launchShip(@PathVariable("id") String id){
        RocketShip rocketShip = rocketShipService.findById(Long.parseLong(id));
        rocketShip.setStatus(RocketShipStatus.LAUNCHED);
        rocketShipService.save(rocketShip);
        return "redirect:/launchControl";
    }
}
