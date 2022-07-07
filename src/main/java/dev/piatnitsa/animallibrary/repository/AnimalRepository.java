package dev.piatnitsa.animallibrary.repository;

import dev.piatnitsa.animallibrary.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNickname(String nickname);
}
