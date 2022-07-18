package dev.piatnitsa.animallibrary.service.impl;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.repository.UserRepository;
import dev.piatnitsa.animallibrary.service.AbstractService;
import dev.piatnitsa.animallibrary.service.UserService;
import dev.piatnitsa.animallibrary.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User insert(User newEntity) {
        UserValidator.validate(newEntity);
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
