package com.raven43.cinemaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raven43.cinemaproject.model.messaging.Chat;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 32)
    private String username;

    @Length(max = 32)
    @JsonIgnore
    private String password;

    @Length(max = 128)
    private String imgName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany(targetEntity = Chat.class,fetch = FetchType.EAGER)
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Set<Chat> chats;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    @JsonIgnore
    public boolean isModer() {
        return roles.contains(Role.MODER);
    }

    public enum Role implements GrantedAuthority {
        ADMIN,
        MODER,
        USER;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("User{id=")
                .append(id)
                .append(", username='")
                .append(username)
                .append('\'')
                .append(", roles=")
                .append(roles)
                .append(", chats=[");

        for (Chat chat:chats) {
            builder.append("{id=").append(chat.getId()).append('[');

            for (User user:chat.getUsers())
                builder.append(user.getUsername()).append(' ');

            builder.append("]}");
        }
        return builder.append("]}").toString();
    }
}
