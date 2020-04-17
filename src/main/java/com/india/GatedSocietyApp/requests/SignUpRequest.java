package com.india.GatedSocietyApp.requests;

import com.india.GatedSocietyApp.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SignUpRequest {
    private String firstName;
    private String lName;
    private String email;
    private String password;
    private List<Role> roleList;
}
