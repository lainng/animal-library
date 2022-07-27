package dev.piatnitsa.animallibrary.service;

import java.util.List;

/**
 * This interface describes CRUD operations for working with objects.
 * @param <T> type of entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface CRUDService<T> {

    /**
     * Retrieves a {@link T} object by its ID.
     * @param id An ID of the object.
     * @return A {@link T} object.
     */
    T getById(long id);

    /**
     * Retrieves a {@link List} of {@link T} objects.
     * @return A {@link List} of {@link T} objects.
     */
    List<T> getAll();

    /**
     * Method for saving an {@link T} entity
     * @param newEntity an {@link T} entity to save.
     */
    T insert(T newEntity);

    /**
     * Updates an {@link T} entity at data source.
     * @param id an ID of specified entity.
     * @param newDataEntity an updated entity.
     */
    T update(long id, T newDataEntity);

    /**
     * Removes an {@link T} entity from data source by its ID.
     * @param id an ID of {@link T} entity.
     */
    void delete(long id);
}
