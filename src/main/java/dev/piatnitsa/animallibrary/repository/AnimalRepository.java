package dev.piatnitsa.animallibrary.repository;

import dev.piatnitsa.animallibrary.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface describes behavior for working with <code>animal</code> table in the database.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNickname(String nickname);
}
