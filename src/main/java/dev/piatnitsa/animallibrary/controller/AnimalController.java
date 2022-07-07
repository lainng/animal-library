package dev.piatnitsa.animallibrary.controller;

import dev.piatnitsa.animallibrary.model.Animal;
import dev.piatnitsa.animallibrary.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> allAnimals() {
        return animalService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal animalById(@PathVariable long id) {
        return animalService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal newAnimal(@RequestBody Animal newAnimal) {
        return animalService.insert(newAnimal);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal editAnimal(@PathVariable long id,
                             @RequestBody Animal newDataAnimal) {
        return animalService.update(id, newDataAnimal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
