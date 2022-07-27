package dev.piatnitsa.animallibrary.util;

import dev.piatnitsa.animallibrary.model.Animal;

/**
 * The class is a utility for working with an {@link Animal} entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class AnimalDataUpdater {

    /**
     * Updates the base entity with new data from the passed entity.
     * @param currentEntity base entity.
     * @param newDataEntity entity that contains the new data for update.
     * @return base entity.
     */
    public static Animal updateData(Animal currentEntity, Animal newDataEntity) {
        if (newDataEntity.getNickname() != null) {
            currentEntity.setNickname(newDataEntity.getNickname());
        }

        if (newDataEntity.getDateOfBirth() != null) {
            currentEntity.setDateOfBirth(newDataEntity.getDateOfBirth());
        }

        if (newDataEntity.getGender() != null) {
            currentEntity.setGender(newDataEntity.getGender());
        }

        return currentEntity;
    }
}
