package com.raven43.cinemaproject.model.comment;

import com.raven43.cinemaproject.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Topic.class)
    private Topic topic;

    @Length(max = 512)
    private String text;

    private Date date = new Date();

    public Comment(
            User user,
            Topic topic,
            @Length(max = 512) String text
    ) {
        this.user = user;
        this.topic = topic;
        this.text = text;
    }
}
