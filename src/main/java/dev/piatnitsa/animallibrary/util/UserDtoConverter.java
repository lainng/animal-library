package dev.piatnitsa.animallibrary.util;

import dev.piatnitsa.animallibrary.model.User;
import dev.piatnitsa.animallibrary.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
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
