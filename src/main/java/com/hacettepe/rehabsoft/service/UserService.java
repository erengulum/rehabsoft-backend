package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserDto;

public interface UserService {

    UserDto save(UserDto user);

    UserDto getById(Long id);


    UserDto getByUsername(String username);

    Boolean register(RegistrationRequest registrationRequest);

}
