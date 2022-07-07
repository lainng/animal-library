package dev.piatnitsa.animallibrary.util;

import dev.piatnitsa.animallibrary.model.Animal;

public class AnimalDataUpdater {

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
