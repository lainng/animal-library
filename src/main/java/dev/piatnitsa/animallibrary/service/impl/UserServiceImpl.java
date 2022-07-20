package dev.piatnitsa.animallibrary.service.impl;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.repository.UserRepository;
import dev.piatnitsa.animallibrary.service.AbstractService;
import dev.piatnitsa.animallibrary.service.UserService;
import dev.piatnitsa.animallibrary.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User insert(User newEntity) {
        UserValidator.validate(newEntity);
        newEntity.setPassword(passwordEncoder.encode(newEntity.getPassword()));
        return userRepository.save(newEntity);
    }

    @Override
    public User update(long id, User newDataEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNameAvailable(String name) {
        UserValidator.validateName(name);
        return !userRepository.findByName(name).isPresent();
    }
}
