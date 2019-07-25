package com.raven43.cinemaproject.model.messaging;

import com.raven43.cinemaproject.model.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Chat.class)
    private Chat chat;

    @ManyToOne(targetEntity = Chat.class)
    private User user;

    @Length(max = 512)
    private String text;

    private Date date;

    public Message() {
        this.date = new Date();
    }

    public Message(
            User user,
            Chat chat,
            @Length(max = 512) String text
    ) {
        this.user = user;
        this.chat = chat;
        this.text = text;
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

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chat=" + chat +
                " : " + text +
                " : " + date +
                '}';
    }
}
