package com.raven43.cinemaproject.model.messaging;

import com.raven43.cinemaproject.model.domain.User;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users;


    public Chat() {
        users = new HashSet<>();
    }

    public Chat(User... users) {
        this.users = Arrays.stream(users).collect(Collectors.toSet());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder("Chat{id: ")
                .append(id)
                .append(", users: {");
        for (User user : users)
            buffer
                    .append(user.getId())
                    .append(": ")
                    .append(user.getUsername())
                    .append(",");

        return buffer.append("}}").toString();
    }
}
