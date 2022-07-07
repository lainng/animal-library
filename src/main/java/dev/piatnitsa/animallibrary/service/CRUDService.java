package dev.piatnitsa.animallibrary.service;

import java.util.List;

public interface CRUDService<T> {

    T getById(long id);
    List<T> getAll();
    T insert(T newEntity);
    T update(long id, T newDataEntity);
    void delete(long id);
}
