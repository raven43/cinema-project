package com.raven43.cinemaproject.model.domain;

import com.raven43.cinemaproject.model.comment.Topic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "roles")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 128)
    private String name;

    @Length(max = 128)
    private String imgName;

    @Length(max = 4096)
    private String description;

    private Date birth;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Role.class, mappedBy = "person")
    private Set<Role> roles = new HashSet<>();

    @OneToOne(targetEntity = Topic.class, cascade = CascadeType.ALL)
    private Topic topic = new Topic();
}
