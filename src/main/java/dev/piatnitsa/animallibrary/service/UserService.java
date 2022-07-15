package dev.piatnitsa.animallibrary.service;

import dev.piatnitsa.animallibrary.model.User;

public interface UserService extends CRUDService<User> {

    boolean isNameAvailable(String name);
}
