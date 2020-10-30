package com.hacettepe.rehabsoft.service;

import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);

    UserDto getById(Long id);

    List<UserDto> getAll();

    UserDto getByUsername(String username);

    Boolean register(RegistrationRequest registrationRequest);

}
