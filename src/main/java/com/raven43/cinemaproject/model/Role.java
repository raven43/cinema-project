package com.raven43.cinemaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Entity
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

    public Role() {
    }


    public Role(Film film, Person person, @Length(max = 128) String description) {
        this.film = film;
        this.person = person;
        this.description = description;
    }

    public Role(Film film, Person person) {
        this(film,person,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public String getFilmName() {
        return film.getName();
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Person getPerson() {
        return person;
    }
    public String getPersonName() {
        return person.getName();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", film=" + film.getName() +
                ", person=" + person.getName() +
                ", description='" + description + '\'' +
                '}';
    }

    public String toDTOStringFilm(){
        return URLEncoder.encode("{\"itemId\": " + film.getId() +", \"description\": \"" + description + "\"}", StandardCharsets.UTF_8);
    }
    public String toDTOStringPerson(){
        return URLEncoder.encode("{\"itemId\": " + person.getId() +", \"description\": \"" + description + "\"}", StandardCharsets.UTF_8);
    }


}
