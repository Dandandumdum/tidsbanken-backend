package se.experis.tidsbankenbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.experis.tidsbankenbackend.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    
}
