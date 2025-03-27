package com.springboot.mySpringCoreApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private Coach myCoach;

    @Autowired
    public AppController(Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getWorkout(){
        return myCoach.getDailyWorkout();
    }
}
