package dev.piatnitsa.animallibrary.util;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.model.dto.UserCredentialsDto;
import dev.piatnitsa.animallibrary.model.dto.UserDto;

/**
 * The class represents a converter for {@link User} DTOs.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class UserDtoConverter {

    /**
     * Converts the {@link User} entity to the DTO.
     * @param user entity to convert.
     * @return converted DTO.
     */
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    /**
     * Converts the {@link User} entity to the credentials DTO.
     * @param user entity to convert.
     * @return converted DTO.
     * @see UserCredentialsDto
     */
    public static UserCredentialsDto toCredentialsDto(User user) {
        UserCredentialsDto dto = new UserCredentialsDto();
        dto.setEmail(user.getEmail());
        return dto;
    }

    /**
     * Converts the DTO to the entity.
     * @param dto DTO to convert.
     * @return converted entity.
     */
    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
