package dev.piatnitsa.animallibrary.service;

import dev.piatnitsa.animallibrary.model.User;

/**
 * This interface describes abstract behavior for working with {@link User} objects.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface UserService extends CRUDService<User> {

    /**
     * Checks if the username is available.
     * @param name the name you are looking for.
     * @return {@code true} if name is available {@code false} otherwise.
     */
    boolean isNameAvailable(String name);
}
