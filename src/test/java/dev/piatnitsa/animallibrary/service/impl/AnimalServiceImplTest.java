package dev.piatnitsa.animallibrary.service.impl;

import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;
import dev.piatnitsa.animallibrary.model.Animal;
import dev.piatnitsa.animallibrary.model.Gender;
import dev.piatnitsa.animallibrary.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {
    private static final long CORRECT_ID = 1L;
    private static final long INCORRECT_ID = -1L;
    private static final String CORRECT_NICKNAME = "Ron";
    private static final String NEW_NICKNAME = "Tony";
    private static final String INCORRECT_NICKNAME = "R";
    private static final LocalDate CORRECT_DATE = LocalDate.now().minusDays(1);

    private static final Animal NEW_ANIMAL = new Animal(0, CORRECT_NICKNAME, CORRECT_DATE, Gender.MALE);
    private static final Animal NEW_DATA_FOR_UPDATE = new Animal(0, NEW_NICKNAME, CORRECT_DATE, Gender.MALE);
    private static final Animal CORRECT_ANIMAL = new Animal(CORRECT_ID, CORRECT_NICKNAME, CORRECT_DATE, Gender.MALE);
    private static final Animal INCORRECT_ANIMAL = new Animal(INCORRECT_ID, INCORRECT_NICKNAME, CORRECT_DATE, Gender.MALE);

    @Mock AnimalRepository animalRepository;
    @InjectMocks private AnimalServiceImpl animalService;

    @Test
    void insertCorrectEntity_thenOk() {
        Mockito.when(animalRepository.save(NEW_ANIMAL)).thenReturn(CORRECT_ANIMAL);
        Animal extended = animalService.insert(NEW_ANIMAL);
        assertEquals(extended, CORRECT_ANIMAL);
    }

    @Test
    void insertIncorrectEntity_thenThrowEx() {
        assertThrows(IncorrectParameterException.class, () -> animalService.insert(INCORRECT_ANIMAL));
    }

    @Test
    void updateByCorrectData_thanOk() {

    }

    @Test
    void deleteByCorrectId_thanOk() {
        assertDoesNotThrow(() -> animalService.delete(CORRECT_ID));
    }

    @Test
    void deleteByIncorrectId_thanThrowEx() {
        assertThrows(IncorrectParameterException.class, () -> animalService.delete(INCORRECT_ID));
    }
}