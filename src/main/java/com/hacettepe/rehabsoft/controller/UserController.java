package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("/api")
public class UserController {

    @Autowired
    private UserService userService;



    //@Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDto getOne(@PathVariable(value = "id") Long id){
        return userService.getById(id);
    }


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }



}
