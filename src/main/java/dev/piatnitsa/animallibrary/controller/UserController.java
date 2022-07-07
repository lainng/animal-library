package dev.piatnitsa.animallibrary.controller;

import dev.piatnitsa.animallibrary.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserDto registerUser(@RequestBody UserDto userCredentials) {

    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody UserDto userCredentials) {

    }

    @GetMapping("/name-availability")
    @ResponseStatus(HttpStatus.OK)
    public String isNameAvailable(@RequestBody String name) {

    }
}
