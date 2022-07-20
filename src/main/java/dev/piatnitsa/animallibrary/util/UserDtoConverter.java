package dev.piatnitsa.animallibrary.util;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.model.dto.UserCredentialsDto;
import dev.piatnitsa.animallibrary.model.dto.UserDto;

public class UserDtoConverter {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    public static UserCredentialsDto toCredentialsDto(User user) {
        UserCredentialsDto dto = new UserCredentialsDto();
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
