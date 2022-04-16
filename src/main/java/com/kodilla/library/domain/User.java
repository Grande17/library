package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodilla.library.AppUserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;


@Entity(name = "USERS")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "NAME")
    @NotNull(message = "User Name must not be null")
    private String name;

    @Column(name = "SURNAME")
    @NotNull(message = "User Surname must not be null")
    private String surname;

    @Column(name = "CREATED")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @Column(name = "USERNAME")
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD")
    @NotEmpty
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole = AppUserRole.USER;

    private Boolean locked = true;
    private Boolean enabled = true;


    public User(int id, String name, String surname, String username, String password,AppUserRole appUserRole) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.created = LocalDate.now();
        this.username = username;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.created = LocalDate.now();
        this.appUserRole = AppUserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
