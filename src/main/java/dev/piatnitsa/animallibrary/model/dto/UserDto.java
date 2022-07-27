package dev.piatnitsa.animallibrary.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents data transfer object of {@link dev.piatnitsa.animallibrary.model.User} entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class UserDto {

    private String email;
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
