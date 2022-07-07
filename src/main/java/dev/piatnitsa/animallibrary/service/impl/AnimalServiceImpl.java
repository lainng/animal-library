package dev.piatnitsa.animallibrary.service.impl;

import dev.piatnitsa.animallibrary.exception.EntityAlreadyExistedException;
import dev.piatnitsa.animallibrary.model.Animal;
import dev.piatnitsa.animallibrary.repository.AnimalRepository;
import dev.piatnitsa.animallibrary.service.AbstractService;
import dev.piatnitsa.animallibrary.service.AnimalService;
import dev.piatnitsa.animallibrary.util.AnimalDataUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalServiceImpl
        extends AbstractService<Animal>
        implements AnimalService {
    private final AnimalRepository repository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Animal update(long id, Animal newDataEntity) {
        Animal currentAnimal = getById(id);
        Optional<Animal> optionalAnimal = repository.findByNickname(currentAnimal.getNickname());
        if (optionalAnimal.isPresent()) {
            throw new EntityAlreadyExistedException();
        }
        return AnimalDataUpdater.updateData(currentAnimal, newDataEntity);
    }

    @Override
    public void delete(long id) {
        Animal deletedAnimal = getById(id);
        repository.delete(deletedAnimal);
    }
}
