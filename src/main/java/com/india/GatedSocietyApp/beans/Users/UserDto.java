package com.india.GatedSocietyApp.beans.Users;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
}
