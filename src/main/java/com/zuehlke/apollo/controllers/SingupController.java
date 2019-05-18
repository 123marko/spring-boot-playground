package com.zuehlke.apollo.controllers;

import com.zuehlke.apollo.domain.Astronaut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SingupController {

    @RequestMapping("/signup")
    public String index(Model model){
        model.addAttribute("astronaut", new Astronaut());
        return "astronaut";
    }
}
