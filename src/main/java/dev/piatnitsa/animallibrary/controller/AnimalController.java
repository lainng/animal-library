package dev.piatnitsa.animallibrary.controller;

import dev.piatnitsa.animallibrary.model.Animal;
import dev.piatnitsa.animallibrary.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is an endpoint of the API which allows to perform CRUD operations on animals.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/animal".
 * So that {@code AnimalController} is accessed by sending request to /animal.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Returns all {@link Animal} entities from data source.
     * @return a {@link List} of {@link Animal} entities.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> allAnimals() {
        return animalService.getAll();
    }

    /**
     * Returns an {@link Animal} by its ID from data source.
     * @param id an {@link Animal} ID.
     * @return an {@link Animal} entity.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal animalById(@PathVariable long id) {
        return animalService.getById(id);
    }

    /**
     * Creates a new {@link Animal} entity in the data source.
     * @param newAnimal a new {@link Animal} entity for saving.
     * @return a new {@link Animal} entity.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal newAnimal(@RequestBody Animal newAnimal) {
        return animalService.insert(newAnimal);
    }

    /**
     * Updates an {@link Animal} by specified ID.
     * @param newDataAnimal a {@link Animal} entity that contains information for updating.
     * @param id an {@link Animal} ID.
     * @return updated {@link Animal} entity.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal editAnimal(@PathVariable long id,
                             @RequestBody Animal newDataAnimal) {
        return animalService.update(id, newDataAnimal);
    }

    /**
     * Removes from data source an {@link Animal} by specified ID.
     * @param id an {@link Animal} ID.
     * @return NO_CONTENT HttpStatus.
     * @see HttpStatus
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
