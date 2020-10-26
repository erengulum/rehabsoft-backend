package com.hacettepe.rehabsoft.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    //Register olurken istenen bilgilerin DTOsu

    private String nameSurname;

    private String username;

    private String password;

    private String email;
}
