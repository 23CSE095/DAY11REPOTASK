package com.example.internship.Controller;

import com.example.internship.Service.Authservice;
import com.example.internship.models.JwtResponse;
import com.example.internship.models.Userdetaildto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class Authcontroller {

    @Autowired
    private Authservice authService;

    // Register a new user
    @PostMapping("/register")
    public String addNewUser(@RequestBody Userdetaildto registerDetails) {
        return authService.addNewEmployee(registerDetails);
    }


MONISHA.U
    @PostMapping("/login")
    public JwtResponse login(@RequestBody Userdetaildto login) {
        return authService.authenticate(login);
    }

}
