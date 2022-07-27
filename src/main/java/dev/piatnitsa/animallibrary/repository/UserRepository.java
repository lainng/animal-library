package dev.piatnitsa.animallibrary.repository;

import dev.piatnitsa.animallibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface describes behavior for working with <code>user</code> table in the database.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
