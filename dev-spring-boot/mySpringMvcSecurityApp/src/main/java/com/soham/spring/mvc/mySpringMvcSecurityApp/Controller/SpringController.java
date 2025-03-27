package com.soham.spring.mvc.mySpringMvcSecurityApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
