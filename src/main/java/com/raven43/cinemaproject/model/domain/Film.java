package com.raven43.cinemaproject.model.domain;

import com.raven43.cinemaproject.model.comment.Topic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "roles")
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
    private Set<Role> roles = new HashSet<>();

    @OneToOne(targetEntity = Topic.class, cascade = CascadeType.ALL)
    private Topic topic = new Topic();
}


