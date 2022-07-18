package dev.piatnitsa.animallibrary.controller;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.model.dto.UserDto;
import dev.piatnitsa.animallibrary.security.jwt.TokenProvider;
import dev.piatnitsa.animallibrary.service.UserService;
import dev.piatnitsa.animallibrary.util.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,
                          TokenProvider tokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserDto registerUser(@RequestBody UserDto userCredentials) {
        User registeredUser = userService.insert(UserDtoConverter.toEntity(userCredentials));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );
        UserDto registeredDto = UserDtoConverter.toDto(registeredUser);
        registeredDto.setToken(tokenProvider.generateToken(userCredentials.getEmail()));
        return registeredDto;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody UserDto userCredentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );
        String token = tokenProvider.generateToken(userCredentials.getEmail());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/name-availability")
    @ResponseStatus(HttpStatus.OK)
    public boolean isNameAvailable(@RequestBody String name) {
        return userService.isNameAvailable(name);
    }
}
