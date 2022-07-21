package dev.piatnitsa.animallibrary.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nickname;

    @Column(name = "birthday")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && Objects.equals(nickname, animal.nickname)
                && Objects.equals(dateOfBirth, animal.dateOfBirth)
                && gender == animal.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, dateOfBirth, gender);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("id=").append(id);
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }
}
