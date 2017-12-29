package com.endurance.emdb.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<String>("Welcome to EMDB!", HttpStatus.OK);
    }
}
