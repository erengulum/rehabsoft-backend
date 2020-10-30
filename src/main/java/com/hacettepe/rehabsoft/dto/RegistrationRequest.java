package com.hacettepe.rehabsoft.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    //Register olurken istenen bilgilerin DTOsu

    private String firstName;

    private String surname;

    private String username;

    private String password;

    private String email;
}
