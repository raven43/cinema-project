package com.raven43.cinemaproject.model.comment;

import com.raven43.cinemaproject.model.User;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
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

    private Date date;

    public Comment(
            User user,
            Topic topic,
            @Length(max = 512) String text
    ) {
        this.user = user;
        this.topic = topic;
        this.text = text;
        this.date = new Date();
    }

    public Comment() {
        this.date = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                user.getUsername() +
                " -> topic=" + topic.getId() +
                " : " + text +
                " : " + date +
                '}';
    }
}
