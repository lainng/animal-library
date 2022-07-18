package dev.piatnitsa.animallibrary.service;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T> implements CRUDService<T> {
    private final JpaRepository<T, Long> repository;

    protected AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public T getById(long id) {
        Optional<T> item = repository.findById(id);
        if (!item.isPresent()) {
            throw new NoSuchEntityException(new FieldError(ExceptionMessageCode.ENTITY_NOT_EXIST, id));
        }
        return item.get();
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
