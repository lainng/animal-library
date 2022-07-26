package dev.piatnitsa.animallibrary.controller;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.model.dto.UserCredentialsDto;
import dev.piatnitsa.animallibrary.model.dto.UserDto;
import dev.piatnitsa.animallibrary.security.jwt.TokenProvider;
import dev.piatnitsa.animallibrary.service.UserService;
import dev.piatnitsa.animallibrary.util.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public UserCredentialsDto registerUser(@RequestBody UserDto userCredentials) {
        User registeredUser = userService.insert(UserDtoConverter.toEntity(userCredentials));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );
        UserCredentialsDto registeredDto = UserDtoConverter.toCredentialsDto(registeredUser);
        registeredDto.setToken(tokenProvider.generateToken(userCredentials.getEmail()));
        return registeredDto;
    }

    @GetMapping("/name-availability")
    @ResponseStatus(HttpStatus.OK)
    public boolean isNameAvailable(@RequestBody String name) {
        return userService.isNameAvailable(name);
    }
}
