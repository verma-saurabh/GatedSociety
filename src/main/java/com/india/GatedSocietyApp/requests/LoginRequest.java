package com.india.GatedSocietyApp.requests;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
