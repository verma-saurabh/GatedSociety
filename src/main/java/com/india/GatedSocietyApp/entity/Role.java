package com.india.GatedSocietyApp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Getter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    /* @ManyToMany(fetch=FetchType.EAGER)
     @JoinTable(
             name = "roles_privileges",
             joinColumns = @JoinColumn(
                     name = "role_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(
                     name = "privilege_id", referencedColumnName = "id"))*/
    @ElementCollection
    private Collection<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
