package dev.piatnitsa.animallibrary.service.impl;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private static final String CORRECT_EMAIL = "harrypotter@gmail.com";
    private static final String INCORRECT_EMAIL = "lord!voldemort@gmail.com";
    private static final String CORRECT_PASSWORD = "StrongPassword";
    private static final String CORRECT_HASHED_PASSWORD = "$2a$12$qlxvSK05ipt8CiGz5nd1r.vHbS8M/Vga2e8toxYm7Qzb1kX/xY86m";
    private static final String INCORRECT_PASSWORD = "qwerty";
    private static final String CORRECT_USERNAME = "Harry Potter";
    private static final String INCORRECT_USERNAME = "V";

    private final User CORRECT_USER = new User(0, CORRECT_EMAIL, CORRECT_PASSWORD, CORRECT_USERNAME);
    private final User CORRECT_USER_HASHED_PSWD = new User(0, CORRECT_EMAIL, CORRECT_HASHED_PASSWORD, CORRECT_USERNAME);
    private final User INCORRECT_USER = new User(0, INCORRECT_EMAIL, INCORRECT_PASSWORD, INCORRECT_USERNAME);

    @Mock UserRepository userRepository;
    @Mock BCryptPasswordEncoder passwordEncoder;
    @InjectMocks UserServiceImpl userService;

    @Test
    void insertCorrectEntity_thanOk() {
        Mockito.when(userRepository.save(CORRECT_USER_HASHED_PSWD)).thenReturn(CORRECT_USER_HASHED_PSWD);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn(CORRECT_HASHED_PASSWORD);
        User actual = userService.insert(CORRECT_USER);
        assertEquals(CORRECT_USER_HASHED_PSWD, actual);
    }

    @Test
    void isNameAvailable() {
    }
}