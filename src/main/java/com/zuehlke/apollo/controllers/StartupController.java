package com.zuehlke.apollo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartupController {

    @Value("${startup.name}")
    private String displayName;

    @RequestMapping({"/","/index", "/start", "startup"})
    public String index(Model model){
        model.addAttribute("name", displayName);
        return "startup";
    }

    @PostMapping("/gotToSingup")
    public String goToSignup(){
        return "redirect:/signup";
    }
}
