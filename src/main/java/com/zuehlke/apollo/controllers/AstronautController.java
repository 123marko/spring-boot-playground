package com.zuehlke.apollo.controllers;

import com.zuehlke.apollo.domain.Astronaut;
import com.zuehlke.apollo.services.AstronautService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class AstronautController {

    private final AstronautService astronautService;

    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    @PostMapping("/saveAstronaut")
    public String singMeUp(@Valid @ModelAttribute Astronaut astronaut, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.warn("CANNOT SAVE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            return "astronaut";
        }
        astronaut = astronautService.save(astronaut);
        return "redirect:/astronaut/" + astronaut.getId();
    }

    @GetMapping("/astronaut/{id}")
    public String getAstronaut(@PathVariable String id, Model model) {
        Astronaut astronaut = astronautService.findById(Long.valueOf(id));
        model.addAttribute("astronaut", astronaut);
        return "astronaut";
    }
}
