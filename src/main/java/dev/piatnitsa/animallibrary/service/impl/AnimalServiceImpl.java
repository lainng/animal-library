package dev.piatnitsa.animallibrary.service.impl;

import dev.piatnitsa.animallibrary.exception.EntityExistsException;
import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.model.Animal;
import dev.piatnitsa.animallibrary.repository.AnimalRepository;
import dev.piatnitsa.animallibrary.service.AbstractService;
import dev.piatnitsa.animallibrary.service.AnimalService;
import dev.piatnitsa.animallibrary.util.AnimalDataUpdater;
import dev.piatnitsa.animallibrary.validator.AnimalValidator;
import dev.piatnitsa.animallibrary.validator.IdentifiableValidator;
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
    public Animal insert(Animal newEntity) {
        AnimalValidator.validate(newEntity);
        return repository.save(newEntity);
    }

    @Override
    public Animal update(long id, Animal newDataEntity) {
        Animal currentAnimal = getById(id);
        AnimalValidator.validateForUpdate(newDataEntity);
        if (newDataEntity.getNickname() != null) {
            Optional<Animal> optionalAnimal = repository.findByNickname(newDataEntity.getNickname());
            if (optionalAnimal.isPresent()) {
                throw new EntityExistsException(ExceptionMessageCode.ANIMAL_ALREADY_EXISTS);
            }
        }
        AnimalDataUpdater.updateData(currentAnimal, newDataEntity);
        return repository.save(currentAnimal);
    }

    @Override
    public void delete(long id) {
        IdentifiableValidator.validateId(id);
        Animal deletedAnimal = getById(id);
        repository.delete(deletedAnimal);
    }
}
