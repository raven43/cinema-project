package com.raven43.cinemaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.nonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Person person;

    @Length(max = 128)
    private String description;

    public Role(Film film, Person person, @Length(max = 128) String description) {
        this.film = film;
        this.person = person;
        this.description = description;
    }

    public Role(Film film, Person person) {
        this(film, person, null);
    }

    public String getFilmName() {
        return film.getName();
    }

    public String getPersonName() {
        return nonNull(person) ? person.getName() : null;
    }

    public String toDTOStringFilm() {
        return URLEncoder.encode("{\"itemId\": " + film.getId() + ", \"description\": \"" + description + "\"}", StandardCharsets.UTF_8);
    }

    public String toDTOStringPerson() {
        return URLEncoder.encode("{\"itemId\": " + person.getId() + ", \"description\": \"" + description + "\"}", StandardCharsets.UTF_8);
    }
}
