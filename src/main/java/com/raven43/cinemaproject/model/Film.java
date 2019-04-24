package com.raven43.cinemaproject.model;

import com.raven43.cinemaproject.model.comment.Topic;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(max = 128)
    private String name;

    @Length(max = 128)
    private String imgName;

    @Length(max = 4096)
    private String description;

    @Nullable
    private Date premiere;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Role.class, mappedBy = "film")
    private Set<Role> roles;

    @OneToOne(targetEntity = Topic.class, cascade = CascadeType.ALL)
    private Topic topic;


    public Film() {
        this.roles = new HashSet<>();
        this.topic = new Topic();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPremiere() {
        return premiere;
    }

    public void setPremiere(Date premiere) {
        this.premiere = premiere;
    }

    public Set<Role> getRoles() {
        roles = roles==null?new HashSet<>():roles;
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Topic getTopic() {
        topic = topic==null?new Topic():topic;
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgName='" + imgName + '\'' +
                ", description='" + description + '\'' +
                ", premiere=" + premiere +
                ", roles=" + roles +
                '}';
    }
}


