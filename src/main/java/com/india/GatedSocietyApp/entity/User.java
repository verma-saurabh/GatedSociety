package com.india.GatedSocietyApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private LocalDateTime createDate;

    @PrePersist
    public void setUpCreateDate() {
        createDate = LocalDateTime.now();
    }

   /* @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))*/


    @ElementCollection
    private List<Role> roles;

    public User(String firstName, String lastName, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}