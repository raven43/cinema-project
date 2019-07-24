package com.raven43.cinemaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raven43.cinemaproject.model.messaging.Chat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
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
    @ManyToMany(targetEntity = Chat.class, fetch = FetchType.EAGER)
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Set<Chat> chats;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
}
