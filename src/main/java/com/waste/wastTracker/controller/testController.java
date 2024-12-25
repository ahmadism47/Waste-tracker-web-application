package com.waste.wastTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class testController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test Successful");
    }
}
